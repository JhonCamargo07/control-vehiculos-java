<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <jsp:include page="/WEB-INF/paginas/comunes/head.jsp" />
    <body>

        <!-- Header -->
        <jsp:include page="/WEB-INF/paginas/comunes/header.jsp" />        
        <!-- /Header -->

        <div class="container my-5">
            <div class="row">
                <div class="col-md-6">
                    <h1 class="text-center fw-bold">Login</h1>
                    <form action="${pageContext.request.contextPath}/Usuario" method="POST">
                        <div class="mb-3">
                            <label for="nameUsuario" class="form-label">Usuario <span class="text-danger">*</span></label>
                            <input type="text" name="inputUsuario" class="form-control" id="nameUsuario" value="${loginUsuario}">
                            <input type="hidden" name="accion" value="2">
                        </div>
                        <div class="mb-3">
                            <label for="exampleInputPassword1" class="form-label">Password <span class="text-danger">*</span></label>
                            <input type="password" name="inputPassword" class="form-control" id="exampleInputPassword1" value="${loginPassword}">
                        </div>
                        <div class="mb-3 form-check">
                            <input type="checkbox" class="form-check-input" id="exampleCheck1">
                            <label class="form-check-label" for="exampleCheck1">Check me out</label>
                        </div>
                        <div class="text-center">
                            <button type="submit" class="btn btn-primary">Ingresar</button>
                        </div>
                    </form>
                </div>
                        
                <!-- Form logout -->
                <div class="col-md-6">
                    <h1 class="text-center fw-bold">Registro</h1>
                    <form action="${pageContext.request.contextPath}/Usuario" method="POST">
                        <div class="mb-3">
                            <label for="nameUsuario1" class="form-label">Usuario <span class="text-danger">*</span></label>
                            <input type="text" name="inputUsuario" class="form-control" id="nameUsuario1" value="${insertUsuario}">
                            <input type="hidden" name="accion" value="1">
                        </div>
                        <div class="mb-3">
                            <label for="exampleInputPassword2" class="form-label">Password <span class="text-danger">*</span></label>
                            <input type="password" name="inputPassword" class="form-control" id="exampleInputPassword2" value="${insertPassword}">
                        </div>
                        <div class="form-group my-2">
                            <label for="rol" class="label-control">Rol <span class="text-danger">*</span></label><br>
                            <input name="rol" checked id="option1" class="form-check-input me-2 mt-2" type="radio" value="1" aria-label="Radio button for following text input">Comprador <br>
                            <input name="rol" id="option2" class="form-check-input me-2 mt-2" type="radio" value="2" aria-label="Radio button for following text input">Vendedor <br>
                            <input name="rol" id="option3" class="form-check-input me-2 mt-2" type="radio" value="3" aria-label="Radio button for following text input">Ambos <br>
                        </div>
                        <div class="form-group my-2" style="display: none;" id="admin">
                            <label for="passA" class="label-control">Contraseña de administrador <span class="text-danger">*</span></label>
                            <input type="password" class="form-control mt-2" id="passA" name="passAdmin">
                        </div>
                        <div class="text-center">
                            <button type="submit" class="btn btn-primary my-2">Registrarme</button>
                        </div>
                    </form>
                </div>
                <!-- Form logout -->

                <%
                    if (request.getAttribute("mensajeOperacion") != null) {
                %>
                <div class="mt-4 mb-2"><p class="fw-bold lead"><span class="text-danger">*</span> ${mensajeOperacion}</p></div>
                <script>alert('${mensajeOperacion}');</script>
                <%
                    }
                %>
            </div>
        </div>


    <!-- Footer -->
    <jsp:include page="/WEB-INF/paginas/comunes/footer.jsp" />        
    <!-- /Footer -->

    <script src="utilidades/js/validacion.js"></script>
    <!-- scripts -->
    <jsp:include page="/WEB-INF/paginas/comunes/link-js.jsp" />        
    <!-- /scripts -->
</body>
</html>
