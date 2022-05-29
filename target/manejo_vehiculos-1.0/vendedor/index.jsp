<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="domain.VehiculoVO"%>
<%@page import="dao.VehiculoDAO"%>
<%@page import="java.util.List"%>
<jsp:include page="../validarSesion.jsp" />
<jsp:include page="../validarRolVendedor.jsp" />
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
                    <div class="col-md-3"></div>
                    <div class="col-md-6">
                        <h3 class="text-center my-4 fw-bold">Registrar vehiculo</h3>
                        <form action="${pageContext.request.contextPath}/Vehiculo" method="POST" class="was-validated">
                            <div class="row mb-2">
                                <div class="col">
                                    <div class="form-outline">
                                        <input type="text" id="form6Example1" name="inputPlaca" class="form-control" value="" required />
                                        <label class="form-label" for="form6Example1">Placa <span class="text-danger">*</span></label>
                                        <input type="hidden" value="${totalCategorias}" name="totalCategorias">
                                        <input type="hidden" value="1" name="accion">
                                    </div>
                                </div>
                                <div class="col">
                                    <div class="form-outline">
                                        <input type="text" id="form6Example2" name="inputMarca" class="form-control" value="" required />
                                        <label class="form-label" for="form6Example2">Marca <span class="text-danger">*</span></label>
                                    </div>
                                </div>
                            </div>

                            <div class="row mb-2">
                                <div class="col">
                                    <div class="form-outline">
                                        <input type="number" id="form6Example3" name="inputModelo" class="form-control" value="" required />
                                        <label class="form-label" for="form6Example3">Modelo <span class="text-danger">*</span></label>
                                    </div>
                                </div>
                                <div class="col">
                                    <div class="form-outline">
                                        <input type="number" id="form6Example6" name="inputPrecio" step="any" class="form-control" value="" required />
                                        <label class="form-label" for="form6Example6">Precio <span class="text-danger">*</span></label>
                                    </div>
                                </div>
                            </div>

                            <div class="form-outline mb-2">
                                <input type="text" id="form6Example4" name="inputColor" class="form-control" value="" required />
                                <label class="form-label" for="form6Example4">Color <span class="text-danger">*</span></label>
                            </div>

                            <div class="form-outline mb-2">
                                <select class="form-select" name="inputEstado" aria-label="Default select example" required>
                                    <option value="1">Nuevo</option>
                                    <option value="2">Usado</option>
                                </select>
                                <label class="form-label" for="form6Example5">Estado <span class="text-danger">*</span></label>
                            </div>

                            <div class="form-outline mb-2">
                                <select class="form-select" name="inputCategoria" aria-label="Default select example" required>
                                    <c:forEach var="categoria" items="${categorias}" varStatus="status">
                                    <option value="${categoria.categoId}">${categoria.nombreCategoria}</option> 
                                    </c:forEach>
                                </select>
                                <label class="form-label" for="form6Example5">Categoría <span class="text-danger">*</span></label>
                            </div>

                            <div class="text-center mb-3">
                                <button type="submit" name="btnVehiculo" class="btn btn-primary btn-block">Registrar vehiculo</button>
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
                    <div class="col-md-3"></div>
                </div>
            </div>
        </section>


        <!-- Footer -->
        <jsp:include page="/WEB-INF/paginas/comunes/footer.jsp" />        
        <!-- /Footer -->

        <!-- scripts -->
        <jsp:include page="/WEB-INF/paginas/comunes/link-js.jsp" />        
        <!-- /scripts -->
    </body>
</html>
