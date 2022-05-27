<jsp:include page="../validarSesion.jsp" />
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <jsp:include page="/WEB-INF/paginas/comunes/head.jsp" />
    <body>
        <!-- navbar -->
        <jsp:include page="/WEB-INF/paginas/comunes/navbar.jsp" />        
        <!-- /navbar -->
        
        <h1>Hello comprador ${datNombre} ${datApellido}!</h1>
        
        <!-- Footer -->
        <jsp:include page="/WEB-INF/paginas/comunes/footer.jsp" />        
        <!-- /Footer -->

        <!-- scripts -->
        <jsp:include page="/WEB-INF/paginas/comunes/link-js.jsp" />        
        <!-- /scripts -->
</html>
