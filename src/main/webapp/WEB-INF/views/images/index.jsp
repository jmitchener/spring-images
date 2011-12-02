<%@ include file="../includes.jsp" %>

<my:layout title="Recent images">
    <h1>Recent Images</h1>
    
    <c:forEach var="image" items="${recentImages}">
        <div class="image-thumbnail">
            <a href="${pageContext.request.contextPath}/images/${image.id}">
    	        <img src="${pageContext.request.contextPath}/images/${image.id}/thumbnail" />
            </a>
        </div>
    </c:forEach>
</my:layout>