<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="domain.VehiculoVO"%>
<%@page import="dao.VehiculoDAO"%>
<%@page import="java.util.List"%>
<jsp:include page="../validarSesion.jsp" />
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <jsp:include page="/WEB-INF/paginas/comunes/head.jsp" />
    <body>
        <!-- navbar -->
        <jsp:include page="/WEB-INF/paginas/comunes/navbar.jsp" />        
        <!-- /navbar -->

        <section>
            <div class="container">
                <div class="row">
                    <div class="col-md-2"></div>
                    <div class="col-md-8">
                        <h3 class="text-center my-4 fw-bold">Consultar vehiculo</h3>
                        <form action="${pageContext.request.contextPath}/Vehiculo" method="POST" class="was-validated">

                            <div class="form-outline mb-2">
                                <select class="form-select" name="inputCategoria" aria-label="Default select example" required>
                                    <c:forEach var="categoria" items="${categorias}" varStatus="status">
                                        <option value="${categoria.categoId}">${categoria.nombreCategoria}</option> 
                                    </c:forEach>
                                </select>
                                <input type="hidden" value="${totalCategorias}" name="totalCategorias">
                                <input type="hidden" value="2" name="accion">
                                <label class="form-label" for="form6Example5">Tipo de vehiculo <span class="text-danger">*</span></label>
                            </div>

                            <div class="text-center mb-5">
                                <button type="submit" name="buscarVehiculo" class="btn btn-primary btn-block">Buscar vehiculo</button>
                            </div>
                        </form>
                        <%
                            if (request.getAttribute("mensajeOperacion") != null) {
                        %>
                        <div class="mt-1 mb-2"><p class="fw-bold lead"><span class="text-danger">*</span> ${mensajeOperacion}</p></div>
                        <script>alert('${mensajeOperacion}');</script>
                        <%
                            }
                        %>
                    </div>
                    <div class="col-md-2"></div>
                </div>
            </div>
        </section>

        <!-- Footer -->
        <jsp:include page="/WEB-INF/paginas/comunes/footer.jsp" />        
        <!-- /Footer -->

        <!-- scripts -->
        <jsp:include page="/WEB-INF/paginas/comunes/link-js.jsp" />        
        <!-- /scripts -->
</html>
