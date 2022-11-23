<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="fragments/headerSB.jsp"/>


<div class="container-fluid">
    <c:if test="${not empty message}">
        <div class="alert alert-success">
                ${message}
        </div>
    </c:if>
    <c:if test="${not empty messageError}">
        <div class="alert alert-warning">
                ${messageError}
            <a href="/schedulemanager" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm">Generuj
                kolejki</a>
        </div>
    </c:if>
    <!-- Page Heading -->

    <!-- Content Row -->

    <div class="card shadow mb-4">
        <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold text-primary">Kolejki</h6>
        </div>
        <div class="card-body">
            <div class="table-responsive">
                <div id="dataTable_wrapper" class="dataTables_wrapper dt-bootstrap4">
                    <div class="col-sm-12">
                        <c:if test="${empty messageError}">
                        <form method="post">
                            <label>
                                <select name="round_number">
                                    <c:forEach items="${globalRounds}" var="round">
                                        <option value="${round.roundNumber}">${round.roundNumber}. kolejka
                                        </option>
                                    </c:forEach>
                                </select>
                            </label>
                            <p><label><input type="submit" value="Wyświetl kolejkę"></label></p>
                        </form>
                        <table id="dataTable" class="table table-bordered dataTable" aria-describedby="dataTable_info">
                            <c:choose>
                                <c:when test="${not empty rounds}">
                                    <c:forEach items="${rounds}" var="round">
                                        <thead class="thead-light">
                                        <th scope="col">${round.roundNumber}. kolejka</th>
                                        <th>Akcja</th>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${round.matches}" var="match">
                                            <tr>
                                                <c:choose>
                                                    <c:when test="${match.updatedAt != null}">
                                                        <td>${match.homeTeam.name}
                                                            <b>${match.homeTeamGoals}-${match.awayTeamGoals}</b> ${match.awayTeam.name}
                                                        </td>
                                                        <td><a href="<c:url value="matches/matchdetails/${match.id}"/>">Szczegóły meczu</a></td>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <td>${match.homeTeam.name} <b>-</b> ${match.awayTeam.name}</td>
                                                        <td><a href="/matches/setresult/${match.id}">Zakończ mecz</a>
                                                        </td>
                                                    </c:otherwise>
                                                </c:choose>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </c:forEach>
                                </c:when>
                                <c:otherwise>
                                    <thead class="thead-light">
                                    <th scope="col">${round.roundNumber}. kolejka</th>
                                    <th>Akcja</th>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${round.matches}" var="match">
                                        <tr>
                                            <c:choose>
                                                <c:when test="${match.updatedAt != null}">
                                                    <td>${match.homeTeam.name}
                                                        <b>${match.homeTeamGoals}-${match.awayTeamGoals}</b> ${match.awayTeam.name}
                                                    </td>
                                                    <td><a href="matches/matchdetails/${match.id}">Szczegóły meczu</a></td>
                                                </c:when>
                                                <c:otherwise>
                                                    <td>${match.homeTeam.name} <b>-</b> ${match.awayTeam.name}</td>
                                                    <td><a href="/matches/setresult/${match.id}">Zakończ mecz</a></td>
                                                </c:otherwise>
                                            </c:choose>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </c:otherwise>
                            </c:choose>
                            </c:if>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- /.container-fluid -->
</div>

<jsp:include page="fragments/footerSB.jsp"/>