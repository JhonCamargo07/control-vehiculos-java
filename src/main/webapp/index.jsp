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
                    <h1 class="text-center">Login</h1>
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
                    <h1 class="text-center">Registro</h1>
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
                        <div class="text-center">
                            <button type="submit" class="btn btn-primary">Registrarme</button>
                        </div>
                    </form>
                </div>
                <!-- Form logout -->

                <%
                    if (request.getAttribute("mensajeOperacion") != null) {
                %>
                <div class="mt-4 mb-2"><p><span class="text-danger">*</span> ${mensajeOperacion}</p></div>
                <script>alert('${mensajeOperacion}');</script>
                <%
                    }
                %>
            </div>
        </div>


    <!-- Footer -->
    <jsp:include page="/WEB-INF/paginas/comunes/footer.jsp" />        
    <!-- /Footer -->

    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>
