package controllers;

import dao.*;
import domain.*;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

/**
 *
 * @author Camargo
 */
@WebServlet(name = "VehiculoController", urlPatterns = {"/Vehiculo"})
public class VehiculoController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("vendedor/").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int categoria = Integer.parseInt(request.getParameter("inputCategoria"));
        int accion = Integer.parseInt(request.getParameter("accion"));
        int totalCategorias = Integer.parseInt(request.getParameter("totalCategorias"));

        HttpSession sesion = request.getSession();

        if (categoria < 1 || categoria > totalCategorias) {
            request.setAttribute("mensajeOperacion", "Ocurrió un error, por favor recargue la página e intente nuevamente");
            request.getRequestDispatcher("vendedor/index.jsp").forward(request, response);
        } else {

            if (sesion.getAttribute("usuario") != null) {
                UsuarioVO userVo = (UsuarioVO) sesion.getAttribute("usuario");
                String idUsuario = userVo.getIdUsuario();

                VehiculoDAO vehiculoDao = new VehiculoDAO();

                switch (accion) {
                    case 1: // Insert vehiculo
                        this.insertVehiculo(request, response, categoria, idUsuario);
                        break;
                    case 2: // Listar vehiculos
                        this.listarVehiculos(request, response, categoria);
                    default:
                        response.sendRedirect("vendedor/");
                        break;
                }
            } else {
                request.setAttribute("mensajeOperacion", "Debe iniciar sesión");
                request.getRequestDispatcher("vendedor/index.jsp").forward(request, response);
            }

        }
    }

    private void insertVehiculo(HttpServletRequest request, HttpServletResponse response, int categoria, String idUsuario) throws ServletException, IOException {

        VehiculoDAO vehiculoDao = new VehiculoDAO();

        String placa = request.getParameter("inputPlaca");
        String marca = request.getParameter("inputMarca");
        String modelo = request.getParameter("inputModelo");
        String precio = request.getParameter("inputPrecio");
        String color = request.getParameter("inputColor");
        String estado = request.getParameter("inputEstado");

        VehiculoVO vehiculoVo = new VehiculoVO(placa, idUsuario, String.valueOf(categoria), modelo, marca, String.valueOf(estado), precio);

        if (Integer.parseInt(estado) > 2 || Integer.parseInt(estado) < 1 || placa.equals("") || marca.equals("") || modelo.equals("") || Double.parseDouble(precio) == 0 || color.equals("") || estado.equals("") || categoria == 0 || idUsuario.equals("")) {
            request.setAttribute("mensajeOperacion", "Ningun campo puede ser nulo");
            request.getRequestDispatcher("vendedor/index.jsp").forward(request, response);
        } else {
            // Comprobar si la placa ya se encuentra registrada
//            boolean existePlacaEnBD = vehiculoDao.existeVehiculoEnBD(vehiculoVo.getVehPlaca());
            if (vehiculoDao.existeVehiculoEnBD(vehiculoVo.getVehPlaca())) {
                request.setAttribute("datosEnviados", vehiculoVo);
                request.setAttribute("mensajeOperacion", "El vehiculo con placas " + vehiculoVo.getVehPlaca() + " ya se encuentra registrado, pruebe con otra placa.");
                request.getRequestDispatcher("vendedor/").forward(request, response);
            } else {
                // Si no se encuentra registrada, se inserta el vehiculo
//                boolean insertExitoso = vehiculoDao.insert(vehiculoVo, idUsuario);
                if (vehiculoDao.insert(vehiculoVo, idUsuario)) {
                    request.setAttribute("mensajeOperacion", "Vehiculo agregado exitosamente");
                    request.getRequestDispatcher("vendedor/").forward(request, response);
                } else {
                    request.setAttribute("mensajeOperacion", "Ocurrió un error al registrar el vehiculo");
                    request.getRequestDispatcher("vendedor/").forward(request, response);
                }
            }
        }
    }

    private void listarVehiculos(HttpServletRequest request, HttpServletResponse response, int categoria) throws ServletException, IOException {
        VehiculoDAO vehiculoDao = new VehiculoDAO();

        List<VehiculoVO> vehiculos = vehiculoDao.listarVehiculos(categoria);

        if (vehiculos != null || vehiculos.isEmpty()) {
            request.setAttribute("idCategoria", categoria);
            request.setAttribute("vehiculos", vehiculos);
            request.setAttribute("totalVehiculos", vehiculos.size());
            request.getRequestDispatcher("comprador/").forward(request, response);
        } else {
            request.setAttribute("mensajeOperacion", "La categoria no existe");
            request.getRequestDispatcher("comprador/").forward(request, response);
        }
    }

}
