<%@page import="domain.UsuarioVO"%>
<%
    HttpSession sesion = request.getSession();

    UsuarioVO userVo = (UsuarioVO) sesion.getAttribute("usuario");
    String idRol = userVo.getIdRol();%>
<div class="modal fade" id="editarPerfilModal">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header bg-info text-white">
                <h5 class="modal-title">Editar mi perfil</h5>
                <button class="close bg-transparent border-0" data-dismiss="modal">
                    <i class="fas fa-times text-white"></i>
                </button>
            </div>
            <form action="${pageContext.request.contextPath}/Usuario" method="POST" class="was-validated">
                <div class="modal-body">
                    <div class="form-group">
                        <label for="nombre">Nombre <span class="text-danger">*</span></label>
                        <input type="text" class="form-control mt-1" name="inputNombre" value="<%= userVo.getDatNombre()%>" required>
                        <input type="hidden" value="<%= userVo.getUsuLogin()%>" name="inputUsuario">
                        <input type="hidden" value="<%= userVo.getUsuLogin()%>" name="inputPassword">
                        <input type="hidden" value="4" name="accion">
                    </div>
                    <div class="form-group my-2">
                        <label for="apellido">Apellido <span class="text-danger">*</span></label>
                        <input type="text" class="form-control mt-1" name="inputApellido" value="<%= userVo.getDatApellido()%>" required>
                    </div>
                    <div class="form-group my-2">
                        <label for="email">Correo <span class="text-danger">*</span></label>
                        <input type="email" class="form-control mt-1" name="inputEmail" value="<%= userVo.getDatCorreo()%>" required>
                    </div>
                    <div class="form-group my-2">
                        <label for="telefono">Telefono <span class="text-danger">*</span></label>
                        <input type="tel" class="form-control mt-1" name="inputTelefono" value="<%= userVo.getDatTelefono()%>" required>
                    </div>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-primary" type="submit" name="btnUpdateVendedor">Actualizar</button>
                </div>
            </form>
        </div>
    </div>
</div>