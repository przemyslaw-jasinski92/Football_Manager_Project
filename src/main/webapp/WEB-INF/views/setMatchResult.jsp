<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="fragments/headerSB.jsp"/>

<div class="container-fluid">
    <c:if test="${not empty messageError}">
        <div class="alert alert-danger">
                ${messageError} <a href="/teams/list" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm">Edytuj drużyny</a>
        </div>
    </c:if>
    <!-- Page Heading -->

    <!-- Content Row -->

    <div class="card shadow mb-4">
        <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold text-primary">Podaj wynik meczu</h6>
        </div>
        <div class="card-body">
            <div class="table-responsive">
                <div id="dataTable_wrapper" class="dataTables_wrapper dt-bootstrap4">
                    <div class="col-sm-12">
                        <form:form method="post" modelAttribute="setMatch">
                            <form:hidden path="id"/>
                            <form:hidden path="homeTeam.id"/>
                            <form:hidden path="awayTeam.id"/>
                            <form:hidden path="round"/>
                            <table id="dataTable" class="table table-bordered dataTable"
                                   aria-describedby="dataTable_info">
                                <thead>
                                <th>Gospodarz</th>
                                <th>Gość</th>
                                </thead>
                                <tbody>
                                <tr>
                                    <td>${setMatch.homeTeam.name}</td>
                                    <td>${setMatch.awayTeam.name}</td>
                                </tr>
                                <tr>
                                    <td>
                                        <form:label path="homeTeamGoals">Gole gospodarzy</form:label>
                                        <form:input path="homeTeamGoals"/>
                                        <form:errors path="homeTeamGoals" cssStyle="color: purple"/>
                                    </td>
                                    <td>
                                        <form:label path="awayTeamGoals">Gole gości</form:label>
                                        <form:input path="awayTeamGoals"/>
                                        <form:errors path="awayTeamGoals" cssStyle="color: purple"/>
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                            <c:if test="${setMatch.homeTeam.stadium != null}">
                                <form:button class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm">Zapisz</form:button>
                            </c:if>
                        </form:form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- /.container-fluid -->
</div>

<jsp:include page="fragments/footerSB.jsp"/>
