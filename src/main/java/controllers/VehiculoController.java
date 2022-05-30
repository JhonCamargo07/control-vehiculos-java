package controllers;

import dao.UsuarioDAO;
import dao.VehiculoDAO;
import domain.UsuarioVO;
import domain.VehiculoVO;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Camargo
 */
@WebServlet(name = "VehiculoController", urlPatterns = {"/Vehiculo"})
public class VehiculoController extends HttpServlet {

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("vendedor/").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String placa = request.getParameter("inputPlaca");
        String marca = request.getParameter("inputMarca");
        String modelo = request.getParameter("inputModelo");
        String precio = request.getParameter("inputPrecio");
        String color = request.getParameter("inputColor");
        String estado = request.getParameter("inputEstado");
        int categoria = Integer.parseInt(request.getParameter("inputCategoria"));
        int accion = Integer.parseInt(request.getParameter("accion"));
        int totalCategorias = Integer.parseInt(request.getParameter("totalCategorias"));
        
        HttpSession sesion = request.getSession();
        String idUsuario = (String) sesion.getAttribute("idUsuario");

        if (categoria < 1 || categoria > totalCategorias) {
            request.setAttribute("mensajeOperacion", "Ocurrió un error, por favor recargue la página e intente nuevamente");
            request.getRequestDispatcher("vendedor/index.jsp").forward(request, response);
        } else {

            VehiculoVO vehiculoVo = new VehiculoVO(placa, "", String.valueOf(categoria), modelo, marca, String.valueOf(estado), precio);

            VehiculoDAO vehiculoDao = new VehiculoDAO();

            switch (accion) {
                case 1: // Insert vehiculo
                    if (Integer.parseInt(estado) > 2 || Integer.parseInt(estado) < 1 || placa.equals("") || marca.equals("") || modelo.equals("") || Double.parseDouble(precio) == 0 || color.equals("") || estado.equals("") || categoria == 0 || idUsuario.equals("")) {
                        request.setAttribute("mensajeOperacion", "Ningun campo puede ser nulo");
                        request.getRequestDispatcher("vendedor/index.jsp").forward(request, response);
                    }else{
                        // Comprobar si la placa ya se encuentra registrada
                        boolean existePlacaEnBD = vehiculoDao.existeVehiculoEnBD(vehiculoVo.getVehPlaca());
                        if(existePlacaEnBD){
                            request.setAttribute("datosEnviados", vehiculoVo);
                            request.setAttribute("mensajeOperacion", "El vehiculo con placas " + vehiculoVo.getVehPlaca() + " ya se encuentra registrado, pruebe con otra placa.");
                            request.getRequestDispatcher("vendedor/").forward(request, response);
                        }else{
                            // Si no se encuentra registrada, se inserta el vehiculo
                            boolean insertExitoso = vehiculoDao.insert(vehiculoVo, idUsuario);
                            if (insertExitoso) {
                                request.setAttribute("mensajeOperacion", "Vehiculo agregado exitosamente");
                                request.getRequestDispatcher("vendedor/").forward(request, response);
                            } else {
                                request.setAttribute("mensajeOperacion", "Ocurrió un error al registrar el vehiculo");
                                request.getRequestDispatcher("vendedor/").forward(request, response);
                            }
                        }
                    }
                    break;
                case 2: // Listar vehiculos
                    List<VehiculoVO> vehiculos = vehiculoDao.listarVehiculos(categoria);
                    
                    if(vehiculos != null || vehiculos.isEmpty()){
                        request.setAttribute("idCategoria", categoria);
                        request.setAttribute("vehiculos", vehiculos);
                        request.setAttribute("totalVehiculos", vehiculos.size());
                        request.getRequestDispatcher("comprador/").forward(request, response);
                    }else{
                        request.setAttribute("mensajeOperacion", "La categoria no existe");
                        request.getRequestDispatcher("comprador/").forward(request, response);
                    }
                    
//                    <% if (request.getAttribute("idCategoria") == request.getAttribute("categoId")) {out.print("selected");} %>
                default:
//                    response.sendRedirect("vendedor/");
                    break;
            }
        }
    }

}
