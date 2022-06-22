<%@page import="domain.UsuarioVO"%>
<%
    HttpSession sesion = request.getSession();
    
    UsuarioVO userVo = (UsuarioVO) sesion.getAttribute("usuario");
    String idRol = userVo.getIdRol();
    if (!idRol.equals("1") && !idRol.equals("3")) {
        request.getRequestDispatcher("vendedor/").forward(request, response);
    }
%>
