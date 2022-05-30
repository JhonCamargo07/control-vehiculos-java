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
        String idUsuario = request.getParameter("id");
        UsuarioDAO usuarioDao = new UsuarioDAO();
        UsuarioVO usuarioVo = usuarioDao.consultarUsuarioPorId(idUsuario);
        request.setAttribute("usuario", usuarioVo);
        request.getRequestDispatcher("WEB-INF/paginas/vendedor/datos-vendedor.jsp").forward(request, response);
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
            UsuarioVO usuarioVo = new UsuarioVO();
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

            // Obtener la sesion
            HttpSession sesion = request.getSession();

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
                            if (usuarioDao.existeUsuarioEnBD(usuarioVo.getUsuLogin())) {
                                request.setAttribute("insertUsuario", usuario);
                                request.setAttribute("insertPassword", password);
                                request.setAttribute("mensajeOperacion", "El nombre de usuario ya se encuentra registrado, pruebe con otro");
                                request.getRequestDispatcher("index.jsp").forward(request, response);
                            } else {
                                if (usuarioDao.insert(usuarioVo)) {
                                    request.setAttribute("loginUsuario", usuarioVo.getUsuLogin());
                                    request.setAttribute("loginPassword", usuarioVo.getUsuPassword());
                                    request.setAttribute("mensajeOperacion", "Usuario agregado exitosamente, puede iniciar sesion");
                                    request.getRequestDispatcher("index.jsp").forward(request, response);
                                } else {
                                    request.setAttribute("insertUsuario", usuario);
                                    request.setAttribute("insertPassword", password);
                                    request.setAttribute("mensajeOperacion", "Ocurrio un error al registrar el usuario");
                                    request.getRequestDispatcher("index.jsp").forward(request, response);
                                }
                            }
                        }
                    } else {
                        if (usuarioDao.existeUsuarioEnBD(usuarioVo.getUsuLogin())) {
                            request.setAttribute("insertUsuario", usuario);
                            request.setAttribute("insertPassword", password);
                            request.setAttribute("mensajeOperacion", "El nombre de usuario ya se encuentra registrado, pruebe con otro");
                            request.getRequestDispatcher("index.jsp").forward(request, response);
                        } else {
                            if (usuarioDao.insert(usuarioVo)) {
                                request.setAttribute("loginUsuario", usuarioVo.getUsuLogin());
                                request.setAttribute("loginPassword", usuarioVo.getUsuPassword());
                                request.setAttribute("mensajeOperacion", "Usuario agregado exitosamente, puede iniciar sesion");
                                request.getRequestDispatcher("index.jsp").forward(request, response);
                            } else {
                                request.setAttribute("insertUsuario", usuario);
                                request.setAttribute("insertPassword", password);
                                request.setAttribute("mensajeOperacion", "Ocurrio un error al registrar el usuario");
                                request.getRequestDispatcher("index.jsp").forward(request, response);
                            }
                        }
                    }
                    break;
                case 2: // Login
                    UsuarioVO usuVo = usuarioDao.login(usuario, password);
                    if (usuVo != null) {
                        sesion = request.getSession();
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
                    // Cerrar sesion
                    sesion.invalidate();

                    request.setAttribute("mensajeOperacion", "Sesion cerrada exitosamente!");
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                    break;
                case 4: // Update
                    sesion = request.getSession();
                    String idUsu = (String) sesion.getAttribute("idUsuario");
                    String nombre = request.getParameter("inputNombre");
                    String apellido = request.getParameter("inputApellido");
                    String email = request.getParameter("inputEmail");
                    String telefono = request.getParameter("inputTelefono");

                    usuarioVo = new UsuarioVO(idUsu, "", usuario, password, nombre, apellido, telefono, email);

                    boolean usuarioActualizadoExitosamente = usuarioDao.update(usuarioVo);
                    if (usuarioActualizadoExitosamente) {
                        request.setAttribute("mensajeOperacion", "Usuario actualizado exitosamente");
                        request.getRequestDispatcher("vendedor/").forward(request, response);
                    } else {
                        request.setAttribute("mensajeOperacion", "Ocurrió un error al actualizar el usuario");
                        request.getRequestDispatcher("vendedor/").forward(request, response);
                    }

                    break;
                default:
                    request.getRequestDispatcher("vendedor/").forward(request, response);
                    break;
            }
        }
    }

}
