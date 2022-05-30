<%
    HttpSession sesion = request.getSession();
    
    String nombre = (String) sesion.getAttribute("usuLogin");
    if (nombre == null) {
        out.print("No existe la sesion");
        request.setAttribute("mensajeOperacion", "Debe iniciar sesion");
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }else{
        out.print("Si existe la sesion");
    }
%>
