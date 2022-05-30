<jsp:include page="../validarSesion.jsp" />
<jsp:include page="../validarRolVendedor.jsp" />
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <jsp:include page="/WEB-INF/paginas/comunes/head.jsp" />
    <body>
        <!-- navbar -->
        <jsp:include page="/WEB-INF/paginas/comunes/navbar.jsp" />        
        <!-- /navbar -->
        
        <!-- Form agregar vehiculo -->
        <jsp:include page="/WEB-INF/paginas/vendedor/agregar-vehiculo.jsp" />
        <!-- /Form agregar vehiculo -->

        <!-- Footer -->
        <jsp:include page="/WEB-INF/paginas/comunes/footer.jsp" />        
        <!-- /Footer -->
        
        <!-- Editar vendedor -->
        <jsp:include page="/WEB-INF/paginas/vendedor/editar-perfil.jsp" />        
        <!-- /Editar vendedor -->

        <!-- scripts -->
        <jsp:include page="/WEB-INF/paginas/comunes/link-js.jsp" />        
        <!-- /scripts -->
    </body>
</html>
