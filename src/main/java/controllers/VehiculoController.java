package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
        String categoria = request.getParameter("inputCategoria");

        if (placa.equals("") || marca.equals("") || modelo.equals("") || precio.equals("") || color.equals("") || estado.equals("") || categoria.equals("")) {
            request.setAttribute("mensajeOperacion", "Ningun campo puede ser nulo");
            request.getRequestDispatcher("vendedor/index.jsp").forward(request, response);
        }else {
            
        }
    }

}
