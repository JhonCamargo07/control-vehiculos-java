<%
    HttpSession sesion = request.getSession();
    
    String nombre = (String) session.getAttribute("usuLogin");
    if (nombre == null) {
        request.setAttribute("mensajeOperacion", "Debe iniciar sesion");
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
%>
