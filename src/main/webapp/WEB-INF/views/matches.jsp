<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="fragments/headerSB.jsp"/>
<body>

<table class="table">
    <thead class="thead-light">
    <th>Nr kolejki</th>
    <th>Mecz</th>
    <th>Akcja</th>
    </thead>
<c:forEach items="${matches}" var="match" varStatus="count">
    <tr>
        <td>${count.index + 1}</td>
        <td>${match.homeTeam.name} - ${match.awayTeam.name}</td>
        <td><a href="/matches/matchdetails/${match.id}">Szczegóły meczu</a></td>
    </tr>
</c:forEach>
</table>

<jsp:include page="fragments/footerSB.jsp"/>
