<%
    HttpSession sesion = request.getSession();
    
    String idRol = (String) sesion.getAttribute("idRol");
    int rol = Integer.parseInt(idRol);
    if (!idRol.equals("2") && !idRol.equals("3")) {
        request.getRequestDispatcher("comprador/").forward(request, response);
    }
%>
