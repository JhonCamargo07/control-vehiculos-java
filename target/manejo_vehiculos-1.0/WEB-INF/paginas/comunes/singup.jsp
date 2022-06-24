<%@page import="domain.UsuarioVO"%>
<!-- Modal -->
<div class="modal fade" id="singup" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header bg-info text-white">
                <div>
                    <h5 class="modal-title" id="exampleModalLabel">Crear una cuenta</h5>
                    <span>Es facil y rapido</span>
                </div>
                <button type="button" class="btn-close text-white" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <!-- Form singup -->
                <div class="">
                    <form action="${pageContext.request.contextPath}/Usuario" method="POST">
                        <div class="form-floating mb-3">
                            <input type="text" name="input-nombre" class="form-control" id="name" value="${insertPassword}" placeholder=" ">
                            <label for="name" class="form-label">Nombre <span class="text-danger">*</span></label>
                        </div>
                        <div class="form-floating mb-3">
                            <input type="text" name="input-apellido" class="form-control" id="apellido" value="${insertPassword}" placeholder=" ">
                            <label for="apellido" class="form-label">Apellido <span class="text-danger">*</span></label>
                        </div>
                        <div class="form-floating mb-3">
                            <input type="email" name="inputUsuario" class="form-control" id="email" value="${insertUsuario}" placeholder=" ">
                            <label for="email" class="form-label">Correo electr&oacute;nico <span class="text-danger">*</span></label>
                            <input type="hidden" name="accion" value="1">
                        </div>
                        <div class="form-floating mb-3">
                            <input type="password" name="inputPassword" class="form-control" id="password" value="${insertPassword}" placeholder=" ">
                            <label for="password" class="form-label">Contrase&ntilde;a <span class="text-danger">*</span></label>
                        </div>
                        <div class="mb-3">
                            <label for="" class="form-label">Tipo de documento <span class="text-danger">*</span></label>
                            <select class="form-select form-control" name="input-tipo-doc" style="height: 58px">
                                <option value="c.c">Cedula de ciudadania</option>
                                <option value="c.e">Cedula de extrangeria</option>
                                <option value="t.i">Tarjeta de identidad</option>
                                <option value="pasport">Pasaporte</option>
                                <option value="nit">Nit</option>
                            </select>
                        </div>
                        <div class="form-floating mb-3">
                            <input type="number" name="input-documento" class="form-control" id="documento" value="${insertPassword}" placeholder=" ">
                            <label for="documento" class="form-label">Número de documento <span class="text-danger">*</span></label>
                        </div>
                        <div class="form-floating mb-3">
                            <input type="tel" name="inputPhone" class="form-control" id="phone" value="${insertPassword}" placeholder=" ">
                            <label for="phone" class="form-label">Telefono <span class="text-danger">*</span></label>
                        </div>

                        <%
                            HttpSession sesion = request.getSession();

                            if (sesion.getAttribute("usuario") != null) {
                                UsuarioVO userVo = (UsuarioVO) sesion.getAttribute("usuario");
                                String idRol = userVo.getIdRol();
                                if (idRol.equals("3") || idRol.equals("4")) {

                                }
                        %>
                        <div class="mb-3">
                            <label for="" class="form-label">Tipo de documento <span class="text-danger">*</span></label>
                            <select class="form-select form-control" name="rol" style="height: 58px">
                                <option value="2">Vendedor</option>
                                <option value="3">Comprador y vendedor</option>
                                <option value="4">Administrador</option>
                            </select>
                        </div>
                        <%   
                            }
                        %>

                        <div class="modal-footer">
                            <button type="submit" class="btn btn-primary">Crear cuenta</button>
                        </div>
                    </form>
                </div>
                <!-- Form singup -->
            </div>
        </div>
    </div>
</div>