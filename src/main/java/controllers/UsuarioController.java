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

            switch (accion) {
                case 1: // Insert
                    this.insert(request, response, usuarioVo, usuario, password, idRol);
                    break;
                case 2: // Login
                    this.login(request, response, usuario, password);
                    break;
                case 3: // Logout
                    this.logout(request, response);
                    break;
                case 4: // Update
                    this.update(request, response, usuario, password);
                    break;
                default:
                    request.getRequestDispatcher("vendedor/").forward(request, response);
                    break;
            }
        }
    }

    private void login(HttpServletRequest request, HttpServletResponse response, String usuario, String password) throws IOException, ServletException {
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);

        UsuarioDAO usuarioDao = new UsuarioDAO();
        UsuarioVO usuVo = usuarioDao.login(usuario, password);
        HttpSession sesion = null;
        if (usuVo != null) {
            Cookie visitanteCookie = new Cookie("sesionActive", "yes");
            response.addCookie(visitanteCookie);

            sesion = request.getSession(true);
            sesion.setAttribute("usuario", usuVo);

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
    }

    private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);

        HttpSession sesion = request.getSession();
        sesion.removeAttribute("usuario");
        sesion.removeAttribute("categorias");
        sesion.removeAttribute("totalCategorias");

        Cookie usuarioCookie = new Cookie("sesionActive", "no");
//                    usuarioCookie.setMaxAge(0);
        response.addCookie(usuarioCookie);

        sesion.invalidate();

        request.setAttribute("mensajeOperacion", "Sesion cerrada exitosamente!");
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    private void insert(HttpServletRequest request, HttpServletResponse response, UsuarioVO usuarioVo, String usuario, String password, String idRol) throws ServletException, IOException {
        UsuarioDAO usuarioDao = new UsuarioDAO();
        // UsuarioVO usuarioVo = new UsuarioVO();

        if (!idRol.equals("1")) {
            String passwordAdmin = request.getParameter("passAdmin");
            if (!PASSWORD_ADMIN_ORIGINAL.equals(passwordAdmin)) {
                this.redirigir(request, response, usuario, password, "La contraseña de administrador es incorrecta");
            } else {
                if (usuarioDao.existeUsuarioEnBD(usuarioVo.getUsuLogin())) {
                    this.redirigir(request, response, usuario, password, "El nombre de usuario ya se encuentra registrado, pruebe con otro");
                } else {
                    if (usuarioDao.insert(usuarioVo)) {
                        this.login(request, response, usuario, password);
                    } else {
                        this.redirigir(request, response, usuario, password, "Ocurrio un error al registrar el usuario");
                    }
                }
            }
        } else {
            if (usuarioDao.existeUsuarioEnBD(usuarioVo.getUsuLogin())) {
                this.redirigir(request, response, usuario, password, "El nombre de usuario ya se encuentra registrado, pruebe con otro");
            } else {
                if (usuarioDao.insert(usuarioVo)) {
                    this.login(request, response, usuario, password);
                } else {
                    this.redirigir(request, response, usuario, password, "Ocurrio un error al registrar el usuario");
                }
            }
        }
    }

    private void redirigir(HttpServletRequest request, HttpServletResponse response, String usuario, String password, String mensaje) throws ServletException, IOException {
        request.setAttribute("insertUsuario", usuario);
        request.setAttribute("insertPassword", password);
        request.setAttribute("mensajeOperacion", mensaje);
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    private void update(HttpServletRequest request, HttpServletResponse response, String usuario, String password) throws ServletException, IOException {
        UsuarioDAO usuarioDao = new UsuarioDAO();
        HttpSession sesion = null;

        sesion = request.getSession();
        UsuarioVO usuaVo = null;
        usuaVo = (UsuarioVO) sesion.getAttribute("usuario");

        String idUsu = usuaVo.getIdUsuario();
        String nombre = request.getParameter("inputNombre");
        String apellido = request.getParameter("inputApellido");
        String email = request.getParameter("inputEmail");
        String telefono = request.getParameter("inputTelefono");

        UsuarioVO userVo = new UsuarioVO(idUsu, "", usuario, password, nombre, apellido, telefono, email);

        boolean usuarioActualizadoExitosamente = usuarioDao.update(userVo);
        if (usuarioActualizadoExitosamente) {
            request.setAttribute("mensajeOperacion", "Usuario actualizado exitosamente");
            request.getRequestDispatcher("vendedor/").forward(request, response);
        } else {
            request.setAttribute("mensajeOperacion", "Ocurrió un error al actualizar el usuario");
            request.getRequestDispatcher("vendedor/").forward(request, response);
        }
    }
}
