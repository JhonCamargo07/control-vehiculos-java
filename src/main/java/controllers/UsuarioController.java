package controllers;

import dao.*;
import domain.*;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

/**
 *
 * @author Camargo
 */
@WebServlet(name = "UsuarioController", urlPatterns = {"/Usuario"})
public class UsuarioController extends HttpServlet {

    private String server, port, mail, pass;
    private String asunto = "", mensaje = "";

    public void init() {
        ServletContext context = getServletContext();
        server = context.getInitParameter("server");
        port = context.getInitParameter("port");
        mail = context.getInitParameter("mail");
        pass = context.getInitParameter("password");
    }

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
        if (request.getParameter("inputUsuario").equals("") || request.getParameter("inputPassword").equals("")) {
            request.setAttribute("loginUsuario", request.getParameter("inputUsuario"));
            request.setAttribute("loginPassword", request.getParameter("inputPassword"));
            request.setAttribute("mensajeOperacion", "Los datos no pueden ser nulos");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } else {
            UsuarioVO usuarioVo = new UsuarioVO();
            // Recibir datos de la vista
            String idRol = "1";
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
                    this.insert(request, response, usuarioVo, idRol);
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
        response.setHeader("Pragma", "No-cache");
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

    private void insert(HttpServletRequest request, HttpServletResponse response, UsuarioVO usuarioVo, String idRol) throws ServletException, IOException {
        UsuarioDAO usuarioDao = new UsuarioDAO();
        String emailUser = usuarioVo.getUsuLogin();
        String password = usuarioVo.getUsuPassword();

        if (request.getParameter("input-nombre").equals("") || request.getParameter("input-apellido").equals("") || request.getParameter("input-tipo-doc").equals("") || request.getParameter("input-documento").equals("") || request.getParameter("inputPhone").equals("")) {
            request.setAttribute("mensajeOperacion", "Los datos no pueden ser nulos");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } else {

            HttpSession sesion = null;

            sesion = request.getSession();
            UsuarioVO usuaVo = null;
            if (sesion.getAttribute("usuario") != null) {
                usuaVo = (UsuarioVO) sesion.getAttribute("usuario");
                String idRolUsuario = usuaVo.getIdRol();
                if (idRolUsuario.equals("4")) {
                    idRol = request.getParameter("rol");
                }
            }

            UsuarioVO userVo = new UsuarioVO(idRol, emailUser, password, request.getParameter("input-nombre"), request.getParameter("input-apellido"), request.getParameter("inputPhone"), emailUser);
            userVo.setIdRol(idRol);
            userVo.setDatNombre(request.getParameter("input-nombre"));
            userVo.setDatApellido(request.getParameter("input-apellido"));
            userVo.setDatTelefono(request.getParameter("inputPhone"));
            userVo.setDatCorreo(emailUser);

            asunto = "Bienvenido a la familia Control Vehiculos JAC";
            System.out.println("userVo = " + userVo);
            mensaje = "Es un placer que pertenezcas a esta gran familia. Bienvenido!!😀";

            if (usuarioDao.existeEmailEnBD(usuarioVo.getUsuLogin())) {
                this.redirigir(request, response, emailUser, password, "El nombre de usuario ya se encuentra registrado, pruebe con otro");
            } else {
                if (usuarioDao.insert(userVo)) {
//                    try {
//                        SendMail.sendMail(server, port, mail, pass, emailUser, asunto, mensaje);
                    if (sesion.getAttribute("usuario") != null) {
                        request.setAttribute("mensajeOperacion", "Usuario agregado exitosamente");
                        request.getRequestDispatcher("vendedor/index.jsp").forward(request, response);
                    } else {
                        this.login(request, response, emailUser, password);
                    }
//                    } catch (MessagingException ex) {
//                        System.out.println("Error al enviar el correo e iniciar sesión");
//                        Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
//                    }
                } else {
                    this.redirigir(request, response, emailUser, password, "Ocurrio un error al registrar el usuario");
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
