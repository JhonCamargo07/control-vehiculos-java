<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.setHeader("Pragma", "no-cache");
    response.setDateHeader("Expires", 0);

    //Obtenemos el arreglo de cookies
    Cookie[] cookies = request.getCookies();

    //Cookie 
    if (cookies != null) {
        for (Cookie c : cookies) {
            if (c.getName().equals("sesionActive") && c.getValue().equals("no")) {
                request.setAttribute("mensajeOperacion", "Debe iniciar sesion");
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
        }
    }

    HttpSession sesion = request.getSession();

    if (sesion.getAttribute("usuario") == null) {
        request.setAttribute("mensajeOperacion", "Debe iniciar sesion");
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
%>
