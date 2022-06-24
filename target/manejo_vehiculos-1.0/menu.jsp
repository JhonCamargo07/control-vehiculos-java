<jsp:include page="validarSesion.jsp" />
<%@page import="domain.UsuarioVO"%>
<%
    HttpSession sesion = request.getSession();

    UsuarioVO userVo = (UsuarioVO) sesion.getAttribute("usuario");
    String idRol = userVo.getIdRol();

    if (idRol.equals("1")) {
        response.sendRedirect("comprador/");
    } else if (idRol.equals("2") || idRol.equals("3") || idRol.equals("4")) {
        response.sendRedirect("vendedor/");
    } else {
        response.sendRedirect("index.jsp");
    }
%>
