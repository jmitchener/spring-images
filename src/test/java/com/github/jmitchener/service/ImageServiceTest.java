/*
 * Copyright (c) 2011 Jim C. Mitchener <jcm@packetpan.org>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 * 3. The name of the author may not be used to endorse or promote products
 *    derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE AUTHOR "AS IS" AND ANY EXPRESS OR
 * IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 * IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT,
 * INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
 * NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
 * THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package com.github.jmitchener.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.github.jmitchener.model.Image;
import com.github.jmitchener.repository.ImageRepository;

public class ImageServiceTest {
    
    private ImageService service;
    private ImageRepository repo;
    
    @Before
    public void setUp() {
        repo = mock(ImageRepository.class);
        service = new ImageServiceImpl(repo);
    }

    @Test
    public void testFindImageById() {
        Image img = new Image();
        
        when(repo.find(1L)).thenReturn(img);
        
        Image ret = service.find(1L);
        
        verify(repo).find(1L);
        
        assertEquals(img, ret);
    }
    
    @Test
    public void testFindRecent() {
        List<Image> images = new ArrayList<Image>();
        
        for (int i = 0; i < 5; i++)
            images.add(new Image());
        
        when(repo.getRecent(5)).thenReturn(images);
        
        List<Image> ret = service.getRecent(5);
        
        verify(repo).getRecent(5);
        
        assertEquals(5, ret.size());
        assertEquals(images, ret);
    }
    
    @Test
    public void testSaveGeneratesThumbnail() throws IOException {
        byte[] data = loadSamplePNG();
        Image image = new Image();
        image.setData(data);
        
        service.save(image);
        
        assertNotNull(image.getThumbnail());
    }
    
    private byte[] loadSamplePNG() throws IOException {
        InputStream is = getClass().getResourceAsStream("/sample-image.png");
        ByteArrayOutputStream os = new ByteArrayOutputStream(1024);
        byte[] buf = new byte[512];
     
        // Read bytes from the input stream in bytes.length-sized chunks and write
        // them into the output stream
        int readBytes;
        while ((readBytes = is.read(buf)) > 0) {
            os.write(buf, 0, readBytes);
        }
     
        // Convert the contents of the output stream into a byte array
        byte[] data = os.toByteArray();
        
        is.close();
        os.close();
        
        return data;
    }
}
