<%@ include file="../includes.jsp" %>

<m:layout title="Recent images">
    <h1>Recent Images</h1>
    
    <c:forEach var="image" items="${recentImages}">
        <i:thumbnail src="${image}" />
    </c:forEach>
</m:layout>