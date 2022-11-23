<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="fragments/headerSB.jsp"/>

<div class="container-fluid">
    <c:if test="${not empty message}">
        <div class="alert alert-success">
                ${message}
        </div>
    </c:if>

    <div class="d-sm-flex align-items-center justify-content-between mb-4">
        <h1 class="h3 mb-2 text-gray-800">List stadionów</h1>
        <a href="/stadiums/add" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm">Dodaj nowy stadion</a>
    </div>

    <div class="card shadow mb-4">

        <div class="card-body">
            <div class="table-responsive">
                <div id="dataTable_wrapper" class="dataTables_wrapper dt-bootstrap4">
                    <div class="col-sm-12">
                        <table id="dataTable" class="table table-bordered dataTable" aria-describedby="dataTable_info">
                            <thead class="thead-dark">
                            <th>Nazwa</th>
                            <th>Kraj</th>
                            <th>Miasto</th>
                            <th>Adres</th>
                            <th>Akcja</th>
                            </thead>
                            <c:forEach items="${stadiums}" var="stadium">
                                <tr>
                                    <td>${stadium.name}</td>
                                    <td>${stadium.country}</td>
                                    <td>${stadium.city}</td>
                                    <td>${stadium.address}</td>
                                    <td>
                                        <p><a href="/stadiums/edit/${stadium.id}" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm">Edycja</a></p>
                                        <p><a href="/stadiums/delete/${stadium.id}" class="d-none d-sm-inline-block btn btn-sm btn-danger shadow-sm">Usuń</a></p>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="fragments/footerSB.jsp"/>
