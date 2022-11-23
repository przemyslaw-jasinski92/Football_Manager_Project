<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="fragments/headerSB.jsp"/>


<div class="container-fluid">
    <c:choose>
        <c:when test="${not empty message}">
            <div class="alert alert-success">
                    ${message}
            </div>
        </c:when>
        <c:when test="${not empty messageError}">
            <div class="alert alert-warning">
                    ${messageError}
                <a href="/schedulemanager" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm">Generuj
                    kolejki</a>
            </div>
        </c:when>
    </c:choose>

    <c:choose>
        <c:when test="${matchesToEnd > 0}">
            <div class="alert alert-warning">
                Do końca sezonu pozostało ${matchesToEnd} meczów do rozegrania.
            </div>
            <div class="d-sm-flex align-items-center justify-content-between mb-4">
                <h1 class="h3 mb-2 text-gray-800">Liderzy tabeli</h1>
            </div>
        </c:when>
        <c:when test="${not empty winner && empty messageError}">
            <div class="alert alert-success">
                Wszystkie spotkania zostały rozegrane. <a href="/schedulemanager" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm">Zakończ sezon</a>
            </div>
            <div class="d-sm-flex align-items-center justify-content-between mb-4">
                <h1 class="h3 mb-2 text-gray-800">Mistrz ligi</h1>
            </div>
            <div class="card shadow mb-4">
                <div class="card-body">
                        <center><h1>${winner.name}</h1></center>
                    </div>
            </div>
        </c:when>
    </c:choose>

    <!-- Page Heading -->

    <!-- Content Row -->

    <div class="card shadow mb-4">
        <c:if test="${empty messageError && matchesToEnd > 0}">
            <div class="card-header py-3">
                <h6 class="m-0 font-weight-bold text-primary">Pierwsze trzy zespoły</h6>
            </div>
            <div class="card-body">
                <div class="table-responsive">
                    <div id="dataTable_wrapper" class="dataTables_wrapper dt-bootstrap4">
                        <div class="col-sm-12">

                            <table id="dataTable" class="table table-bordered dataTable"
                                   aria-describedby="dataTable_info">
                                <thead>
                                <tr role="row">
                                    <th>Poz</th>
                                    <th>Nazwa</th>
                                    <th>Rozegrane mecze</th>
                                    <th>Punkty</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${topThree}" var="team" varStatus="count">
                                    <tr>
                                        <td>${count.index + 1}</td>
                                        <td>${team.name}</td>
                                        <td>${team.matchesPlayed}</td>
                                        <td>${team.points}</td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </c:if>
    </div>
    <!-- /.container-fluid -->
</div>

<jsp:include page="fragments/footerSB.jsp"/>
