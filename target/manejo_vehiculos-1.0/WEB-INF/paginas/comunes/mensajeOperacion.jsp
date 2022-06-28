<%
    if (request.getAttribute("mensajeOperacion") != null) {
%>
<div class="alert alert-dark alert-dismissible fade show mt-4" role="alert">
    <h3 class="load fw-bold">${mensajeOperacion}</h3>
    ${DescripcionMensaje}
    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
</div>
<%
    }
%>