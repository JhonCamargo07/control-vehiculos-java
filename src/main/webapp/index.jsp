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
                <div class="col-md-3"></div>
                <div class="col-md-6">
                    <h1 class="text-center fw-bold">Login</h1>
                    <form action="${pageContext.request.contextPath}/Usuario" method="POST">
                        <div class="form-floating mb-3 mt-4">
                            <input type="email" name="inputUsuario" class="form-control" id="nameUsuario" value="${loginUsuario}" placeholder=" ">
                            <label for="nameUsuario" class="form-label">Correo electr&#243;nico <span class="text-danger">*</span></label>
                            <input type="hidden" name="accion" value="2">
                        </div>
                        <div class="form-floating mb-3">
                            <input type="password" name="inputPassword" class="form-control" id="exampleInputPassword1" value="${loginPassword}" placeholder=" ">
                            <label for="exampleInputPassword1" class="form-label">Password <span class="text-danger">*</span></label>
                        </div>
                        <div class="mb-3 form-check">
                            <input type="checkbox" class="form-check-input" id="exampleCheck1">
                            <label class="form-check-label" for="exampleCheck1">Recordar credenciales</label>
                        </div>
                        <div class="text-center">
                            <button type="submit" class="btn btn-primary my-2">Ingresar</button>
                        </div>
                    </form>
                </div>
                <div class="col-md-3"></div>
                <jsp:include page="/WEB-INF/paginas/comunes/mensajeOperacion.jsp" />
            </div>
        </div>

        <!-- SingUp -->
        <jsp:include page="/WEB-INF/paginas/comunes/singup.jsp" />        
        <!-- /SingUp -->

        <!-- Footer -->
        <jsp:include page="/WEB-INF/paginas/comunes/footer.jsp" />        
        <!-- /Footer -->

        <!-- scripts -->
        <jsp:include page="/WEB-INF/paginas/comunes/link-js.jsp" />        
        <!-- /scripts -->
    </body>
</html>
