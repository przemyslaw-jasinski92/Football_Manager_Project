<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <%--    <div class="d-sm-flex align-items-center justify-content-between mb-4">--%>

    <!-- Content Row -->

    <div class="card shadow mb-4">
        <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold text-primary">Tabela ligi</h6>
        </div>
        <div class="card-body">
            <div class="table-responsive">
                <div id="dataTable_wrapper" class="dataTables_wrapper dt-bootstrap4">
                    <div class="col-sm-12">
                        <table id="dataTable" class="table table-bordered dataTable" aria-describedby="dataTable_info">
                            <thead class="thead-dark">
                            <th>Poz</th>
                            <th>Nazwa</th>
                            <th>Rozegrane mecze</th>
                            <th>Wygrane</th>
                            <th>Remisy</th>
                            <th>Przegrane</th>
                            <th>Bilans</th>
                            <th>Punkty</th>
                            </thead>
                            <c:forEach items="${teams}" var="team" varStatus="count">
                                <tr>
                                    <td>${count.index + 1}</td>
                                    <td>${team.name}</td>
                                    <td>${team.matchesPlayed}</td>
                                    <td>${team.wins}</td>
                                    <td>${team.draws}</td>
                                    <td>${team.lost}</td>
                                    <td>${team.allScoresGoals}:${team.allLostGoals}</td>
                                    <td>${team.points}</td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- /.container-fluid -->
</div>

<jsp:include page="fragments/footerSB.jsp"/>
