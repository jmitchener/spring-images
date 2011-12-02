<%@ include file="../includes.jsp" %>

<my:layout title="">
    id: ${image.id}<br />
    contentType: ${image.contentType}<br />
    
    <img src="${pageContext.request.contextPath}/images/${image.id}/raw" />
</my:layout>