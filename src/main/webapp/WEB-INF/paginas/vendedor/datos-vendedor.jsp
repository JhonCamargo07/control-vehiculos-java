<%@include file="../../../cache.jsp" %>
<jsp:include page="../../../validarSesion.jsp" />
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="domain.VehiculoVO"%>
<%@page import="dao.VehiculoDAO"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html lang="es">
    <jsp:include page="../comunes/head.jsp" />
    <body>

        <!-- navbar -->
        <jsp:include page="/WEB-INF/paginas/comunes/navbar.jsp" />        
        <!-- /navbar -->

        <div style="height: 300px"></div>

        <div class="modal fade" data-backdrop="static" data-keyboard="false" tabindex="-1" id="verDatosVendedor">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">
                    <div class="modal-header bg-info text-white">
                        <h5 class="modal-title">Datos del vendedor</h5>
                        <button class="close bg-transparent border-0" id="closeModal" data-dismiss="modal">
                            <i class="fas fa-times text-white"></i>
                        </button>
                    </div>
                    <div class="p-4">
                        <p><span class="fw-bold">Nombre:</span> ${usuario.datNombre} ${usuario.datApellido}</p>
                        <p><span class="fw-bold">Telefono:</span> ${usuario.datTelefono}</p>
                        <p><span class="fw-bold">Email:</span> ${usuario.datCorreo}</p>
                        <div class="text-center mt-3">
                            <a href="${pageContext.request.contextPath}/comprador/" class="text-center"><button class="btn btn-primary">Volver a consultar vehiculos</button></a>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Footer -->
        <jsp:include page="/WEB-INF/paginas/comunes/footer.jsp" />        
        <!-- /Footer -->

        <!-- scripts -->
        <jsp:include page="/WEB-INF/paginas/comunes/link-js.jsp" />        
        <!-- /scripts -->

        <script>
            window.$('#verDatosVendedor').modal('show');
            $(document).on('click', '#closeModal', function () {
                location.href = "${pageContext.request.contextPath}/comprador/";
            });
        </script>
    </body>

</html>