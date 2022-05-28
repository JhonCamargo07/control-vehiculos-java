<%
    HttpSession sesion = request.getSession();
    
    String nombre = (String) sesion.getAttribute("usuLogin");
    if (nombre == null) {
        request.setAttribute("mensajeOperacion", "Debe iniciar sesion");
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
%>
