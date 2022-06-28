<%@include file="../cache.jsp" %>
<jsp:include page="../validarSesion.jsp" />
<jsp:include page="../validarRolComprador.jsp" />
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <jsp:include page="/WEB-INF/paginas/comunes/head.jsp" />
    <body>
        <!-- navbar -->
        <jsp:include page="/WEB-INF/paginas/comunes/navbar.jsp" />        
        <!-- /navbar -->

        <!-- consultar vehiculos -->
        <jsp:include page="/WEB-INF/paginas/comprador/consultar-vehiculos.jsp" />        
        <!-- /consultar vehiculos -->

        <!-- Footer -->
        <jsp:include page="/WEB-INF/paginas/comunes/footer.jsp" />        
        <!-- /Footer -->

        <!-- Editar vendedor -->
        <jsp:include page="/WEB-INF/paginas/vendedor/editar-perfil.jsp" />        
        <!-- /Editar vendedor -->

        <!--  Agregar usuario -->
        <jsp:include page="/WEB-INF/paginas/comunes/singup.jsp" />        
        <!-- / Agregar usuario -->

        <!-- scripts -->
        <jsp:include page="/WEB-INF/paginas/comunes/link-js.jsp" />        
        <!-- /scripts -->
</html>
