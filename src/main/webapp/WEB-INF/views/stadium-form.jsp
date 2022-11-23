<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="fragments/headerSB.jsp"/>

<div class="container-fluid">
    <!-- Page Heading -->

    <!-- Content Row -->

    <div class="card shadow mb-4">
        <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold text-primary">Podaj informacje o stadionie</h6>
        </div>
        <div class="card-body">
            <form:form method="post" modelAttribute="stadium">
                <form:hidden path="id"/>
                <div class="mb-3">
                    <p><form:label path="name">Nazwa stadionu</form:label></p>
                    <form:input path="name"/>
                    <p><form:errors path="name" cssStyle="color: purple"/></p>
                </div>
                <div class="mb-3">
                    <p><form:label path="country">Kraj</form:label></p>
                    <form:input path="country"/>
                    <p><form:errors path="country" cssStyle="color: purple"/></p>
                </div>
                <div class="mb-3">
                    <p><form:label path="city">Miejscowość</form:label></p>
                    <form:input path="city"/>
                    <p><form:errors path="city" cssStyle="color: purple"/></p>
                </div>
                <div class="mb-3">
                    <p><form:label path="address">Adres</form:label></p>
                    <form:input path="address"/>
                    <p><form:errors path="address" cssStyle="color: purple"/></p>
                </div>
                <form:button class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm">Zapisz</form:button>
            </form:form>
        </div>
    </div>
    <!-- /.container-fluid -->
</div>

<jsp:include page="fragments/footerSB.jsp"/>
