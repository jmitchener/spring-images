<%@ include file="../includes.jsp"%>

<my:layout title="Upload a new image">
    <h1>Please upload a file</h1>
    
    <form method="post" enctype="multipart/form-data">
        File: <input type="file" name="file" /> <br />
        File: <input type="file" name="file" /> <br />
        File: <input type="file" name="file" /> <br />
        
        <input type="submit" />
    </form>
</my:layout>