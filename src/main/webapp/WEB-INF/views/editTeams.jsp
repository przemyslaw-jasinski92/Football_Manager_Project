<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        </div>
    </c:if>
    <!-- Page Heading -->

    <!-- Content Row -->

    <div class="card shadow mb-4">
        <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold text-primary">Lista drużyn</h6>
        </div>
        <div class="card-body">
            <div class="table-responsive">
                <div id="dataTable_wrapper" class="dataTables_wrapper dt-bootstrap4">
                    <div class="col-sm-12">
                        <table id="dataTable" class="table table-bordered dataTable" aria-describedby="dataTable_info">
                            <thead class="thead-dark">
                            <th>Nazwa</th>
                            <th>Akcja</th>
                            </thead>
                            <c:forEach items="${teamsList}" var="team">
                                <tr>
                                    <td>
                                        <c:choose>
                                            <c:when test="${team.stadium == null}">
                                                ${team.name} - <b>brak przypisanego stadionu</b>
                                            </c:when>
                                            <c:otherwise>${team.name}</c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td><a href="/teams/edit/${team.id}">Edytuj</a></td>
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
