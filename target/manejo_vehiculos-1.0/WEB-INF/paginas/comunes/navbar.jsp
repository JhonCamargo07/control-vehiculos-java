<nav class="navbar navbar-expand-lg navbar-dark bg-info">
    <div class="container">
        <a class="navbar-brand fw-bold" href="#"><i class="fas fa-car me-2"></i>Control vehiculos</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav ms-auto mb-2 mb-lg-0 d-flex align-self-auto">
                <li class="nav-item">
                    <a class="nav-link active fw-bold m-1" aria-current="page" href="">Inicio</a>
                </li>
                <%
                    HttpSession sesion = request.getSession();
                    String idRol = (String) sesion.getAttribute("idRol");
                    if (idRol.equals("3")) {
                %>
                <li class="nav-item dropdown m-1">
                    <a class="nav-link dropdown-toggle" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">Rol</a>
                    <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <li><a class="dropdown-item" href="../vendedor">Vendedor</a></li>
                        <li><a class="dropdown-item" href="../comprador">Comprador</a></li>
                    </ul>
                </li>

                <%
                    }
                %>

                <%
                    if (!idRol.equals("1")) {
                %>
                <li class="nav-item">
                    <a class="nav-link m-1" aria-current="page" href="" data-toggle="modal" data-target="#editarPerfilModal">Editar perfil</a>
                </li>
                <%
                    }
                %>

                <li class="nav-item">
                    <form action="${pageContext.request.contextPath}/Usuario" method="POST">
                        <input type="hidden" name="inputUsuario" value="${usuLogin}">
                        <input type="hidden" name="inputPassword" value="${usuPassword}">
                        <input type="hidden" name="accion" value="3">
                        <button type="submit" class="btn btn-primary text-white m-1 fw-bolder">Cerrar sesión</button>
                    </form>
                </li>
            </ul>
        </div>
    </div>
</nav>