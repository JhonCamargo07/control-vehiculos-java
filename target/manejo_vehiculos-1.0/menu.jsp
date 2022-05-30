<jsp:include page="validarSesion.jsp" />
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <jsp:include page="/WEB-INF/paginas/comunes/head.jsp" />
    <body>

        <!-- Header -->
        <jsp:include page="/WEB-INF/paginas/comunes/header.jsp" />        
        <!-- /Header -->
        
        
        <%
            HttpSession sesion = request.getSession();
            String idRol = (String) sesion.getAttribute("idRol");
            if( idRol.equals("1") ){
                response.sendRedirect("comprador/");
            }else if( idRol.equals("2") || idRol.equals("3") ){
                response.sendRedirect("vendedor/");
            }else{
                response.sendRedirect("index.jsp");
            }
        
        %>

        <form action="${pageContext.request.contextPath}/Usuario" method="POST">
            <input type="hidden" name="inputUsuario" value="${usuLogin}">
            <input type="hidden" name="inputPassword" value="${usuPassword}">
            <input type="hidden" name="accion" value="3">
            <button type="submit" class="btn btn-info">Cerrar sesión</button>
        </form>

        <!-- Footer -->
        <jsp:include page="/WEB-INF/paginas/comunes/footer.jsp" />        
        <!-- /Footer -->
        
        <!-- scripts -->
        <jsp:include page="/WEB-INF/paginas/comunes/link-js.jsp" />        
        <!-- /scripts -->
        
    </body>
</html>
