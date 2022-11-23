<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="fragments/headerSB.jsp"/>
<body>

<div class="container-fluid">
    <c:if test="${not empty message}">
        <div class="alert alert-success">
                ${message}
        </div>
    </c:if>
    <c:if test="${not empty messageError}">
        <div class="alert alert-danger">
                ${messageError} <a href="/teams/list" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm">Edytuj drużyny</a>
        </div>
    </c:if>
    <!-- Page Heading -->
    <%--    <div class="d-sm-flex align-items-center justify-content-between mb-4">--%>
    <%--        <h1 class="h3 mb-2 text-gray-800">Kolejki</h1>--%>
    <%--        &lt;%&ndash;        <a href="/user/add" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm">Add new user</a>&ndash;%&gt;--%>
    <%--    </div>--%>

    <!-- Content Row -->

    <div class="card shadow mb-4">
        <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold text-primary">Zarządzanie ligą</h6>
        </div>
        <div class="card-body">
            <div class="table-responsive">
                <div id="dataTable_wrapper" class="dataTables_wrapper dt-bootstrap4">
                    <div class="col-sm-12">
                        <table id="dataTable" class="table table-bordered dataTable" aria-describedby="dataTable_info">
                            <c:choose>
                                <c:when test="${schedule.contains('empty')}">
                                    <a href="rounds/createrounds">Wygeneruj mecze</a>
                                </c:when>
                                <c:when test="${endSeason}">
                                    <a href="/winners/finishseason">Zakończ sezon</a>
                                </c:when>
                                <c:otherwise>
                                    <a href="rounds/delete">Skasuj terminarz</a>
                                </c:otherwise>
                            </c:choose>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- /.container-fluid -->
</div>

<jsp:include page="fragments/footerSB.jsp"/>
