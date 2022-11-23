<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="fragments/headerSB.jsp"/>

<div class="container-fluid">
    <c:if test="${not empty message}">
        <div class="alert alert-success">
                ${message}
        </div>
    </c:if>
    <!-- Page Heading -->
    <div class="d-sm-flex align-items-center justify-content-between mb-4">
        <h1 class="h3 mb-2 text-gray-800">Mecz ${matchDetails.round.roundNumber}. kolejki</h1>

    </div>

    <!-- Content Row -->

    <div class="card shadow mb-4">
        <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold text-primary">Informacje o spotkaniu</h6>
        </div>
        <div class="card-body">
            <div class="table-responsive">
                <div id="dataTable_wrapper" class="dataTables_wrapper dt-bootstrap4">
                    <div class="col-sm-12">
                        <table id="dataTable" class="table table-bordered dataTable" aria-describedby="dataTable_info">
                            <c:choose>
                                <c:when test="${matchDetails.updatedAt != null}">
                                    <center><h3><b>${matchDetails.homeTeamGoals}-${matchDetails.awayTeamGoals}</b></h3></center>
                                    <center><h3>${matchDetails.homeTeam.name} <b>-</b> ${matchDetails.awayTeam.name}</h3></center>

                                </c:when>
                                <c:otherwise>
                                    <h3>${matchDetails.homeTeam.name} - ${matchDetails.awayTeam.name}</h3>
                                    <h4>Mecz jeszcze nie został rozegrany</h4>
                                </c:otherwise>
                            </c:choose>
                            <center><p>Miejsce: ${matchDetails.homeTeam.stadium.name}, ${matchDetails.homeTeam.stadium.city}</p></center>
                            <center><p>Data: ${matchDetails.updatedAt}</p></center>
                            <center><p><a href="/rounds" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm">Powrót</a></p></center>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- /.container-fluid -->
</div>

<jsp:include page="fragments/footerSB.jsp"/>
