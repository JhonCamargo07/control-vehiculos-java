<%
    HttpSession sesion = request.getSession();
    
    String idRol = (String) sesion.getAttribute("idRol");
    if (!idRol.equals("1") && !idRol.equals("3")) {
        request.getRequestDispatcher("vendedor/").forward(request, response);
    }
%>
