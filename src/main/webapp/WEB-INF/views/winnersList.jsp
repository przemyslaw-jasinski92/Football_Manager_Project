<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="fragments/headerSB.jsp"/>

<div class="container-fluid">
    <!-- Page Heading -->

    <!-- Content Row -->

    <div class="card shadow mb-4">
        <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold text-primary">Zwycięzcy rozgrywek</h6>
        </div>
        <div class="card-body">
            <div class="table-responsive">
                <div id="dataTable_wrapper" class="dataTables_wrapper dt-bootstrap4">
                    <div class="col-sm-12">
                        <table id="dataTable" class="table table-bordered dataTable" aria-describedby="dataTable_info">
                            <thead class="thead-dark">
                            <th>Sezon</th>
                            <th>Drużyna</th>
                            <th>Zdobyte punkty</th>
                            <th>Bilans</th>
                            <th>Data zdobycia mistrzostwa</th>
                            </thead>
                            <c:forEach items="${winners}" var="winner" varStatus="count">
                                <tr>
                                    <td>${winners.size() - count.index}</td>
                                    <td>${winner.teamName}</td>
                                    <td>${winner.points}</td>
                                    <td>${winner.allScoreGoals}:${winner.allLostGoals}</td>
                                    <td>${winner.createdAt}</td>
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
