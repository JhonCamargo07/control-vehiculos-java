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
@WebServlet(name = "UsuarioController", urlPatterns = {"/Usuario"})
public class UsuarioController extends HttpServlet {

    private static String PASSWORD_ADMIN_ORIGINAL = "JC*bN1mP6wE%jM-oO507";

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
//        response.setContentType("text/html;charset=UTF-8");
        if (request.getParameter("inputUsuario").equals("") || request.getParameter("inputPassword").equals("")) {
            request.setAttribute("mensajeOperacion", "Los datos no pueden ser nulos");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } else {
            UsuarioVO usuarioVo = null;
            // Recibir datos de la vista
            String idRol = request.getParameter("rol");
            String idUsuario = request.getParameter("idUsuario");
            String usuario = request.getParameter("inputUsuario");
            String password = request.getParameter("inputPassword");
            int accion = Integer.parseInt(request.getParameter("accion"));

            if (idUsuario != null) {
                System.out.println("Entró con el id");
                usuarioVo = new UsuarioVO(idUsuario, usuario, password);
            } else {
                // Instancia de VO
                usuarioVo = new UsuarioVO(idRol, usuario, password);
            }

            // DAO
            UsuarioDAO usuarioDao = new UsuarioDAO();

            // Administrar operadores
            switch (accion) {
                case 1: // Insert
                    if (!idRol.equals("1")) {
                        String passwordAdmin = request.getParameter("passAdmin");
                        if (!PASSWORD_ADMIN_ORIGINAL.equals(passwordAdmin)) {
                            request.setAttribute("insertUsuario", usuario);
                            request.setAttribute("insertPassword", password);
                            request.setAttribute("mensajeOperacion", "La contraseña de administrador es incorrecta");
                            request.getRequestDispatcher("index.jsp").forward(request, response);
                        } else {
                            if (usuarioDao.insert(usuarioVo)) {
                                response.sendRedirect("menu.jsp");
                            } else {
                                request.setAttribute("insertUsuario", usuario);
                                request.setAttribute("insertPassword", password);
                                request.setAttribute("mensajeOperacion", "Ocurrio un error al registrar el usuario");
                                request.getRequestDispatcher("index.jsp").forward(request, response);
                            }
                        }
                    } else {
                        if (usuarioDao.insert(usuarioVo)) {
                            response.sendRedirect("menu.jsp");
                        } else {
                            request.setAttribute("insertUsuario", usuario);
                            request.setAttribute("insertPassword", password);
                            request.setAttribute("mensajeOperacion", "Ocurrio un error al registrar el usuario");
                            request.getRequestDispatcher("index.jsp").forward(request, response);
                        }
                    }
                    break;
                case 2: // Login
                    UsuarioVO usuVo = usuarioDao.login(usuario, password);
                    if (usuVo != null) {
                        HttpSession sesion = request.getSession();
                        sesion.setAttribute("idUsuario", usuVo.getIdUsuario());
                        sesion.setAttribute("idRol", usuVo.getIdRol());
                        sesion.setAttribute("usuLogin", usuVo.getUsuLogin());
                        sesion.setAttribute("usuPassword", usuVo.getUsuPassword());
                        sesion.setAttribute("datNombre", usuVo.getDatNombre());
                        sesion.setAttribute("datApellido", usuVo.getDatApellido());
                        sesion.setAttribute("datTelefono", usuVo.getDatTelefono());
                        sesion.setAttribute("datCorreo", usuVo.getDatCorreo());
                        
                        VehiculoDAO vehDao = new VehiculoDAO();
                        List<VehiculoVO> categorias = vehDao.listarCategorias();
                        sesion.setAttribute("categorias", categorias);
                        sesion.setAttribute("totalCategorias", categorias.size());
                        
                        response.sendRedirect("menu.jsp");
                    } else {
                        request.setAttribute("loginUsuario", usuario);
                        request.setAttribute("loginPassword", password);
                        request.setAttribute("mensajeOperacion", "Los datos suministrados son incorrectos");
                        request.getRequestDispatcher("index.jsp").forward(request, response);
                    }
                    break;
                case 3: // Logout
                    HttpSession sesion = request.getSession();
                    // Cerrar sesion
                    sesion.invalidate();

                    request.setAttribute("mensajeOperacion", "Sesion cerrada exitosamente!");
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                default:
                    response.sendRedirect("vendedor/");
            }
        }
    }

}
