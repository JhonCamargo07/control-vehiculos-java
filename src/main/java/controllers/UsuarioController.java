package controllers;

import dao.UsuarioDAO;
import domain.UsuarioVO;
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
@WebServlet(name = "UsuarioController", urlPatterns = {"/Usuario"})
public class UsuarioController extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        if (request.getParameter("inputUsuario").equals("") && request.getParameter("inputPassword").equals("")) {
            request.setAttribute("mensajeOperacion", "Mal!");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } else {
            UsuarioVO usuarioVo = null;
            // Recibir datos de la vista
            String idUsuario = request.getParameter("idUsuario");
            String usuario = request.getParameter("inputUsuario");
            String password = request.getParameter("inputPassword");
            int accion = Integer.parseInt(request.getParameter("accion"));

            if(idUsuario != null){
                System.out.println("Entró con el id");
                usuarioVo = new UsuarioVO(idUsuario, usuario, password);
            }else{
                // Instancia de VO
                usuarioVo = new UsuarioVO(usuario, password);
            }

            // DAO
            UsuarioDAO usuarioDao = new UsuarioDAO();

            // Administrar operadores
            switch (accion) {
                case 1: // Insert
                    if(usuarioDao.insert(usuarioVo)){
                        response.sendRedirect("menu.jsp");
                    }else{
                        request.setAttribute("insertUsuario", usuario);
                        request.setAttribute("insertPassword", password);
                        request.setAttribute("mensajeOperacion", "Ocurrio un error al registrar el usuario");
                        request.getRequestDispatcher("index.jsp").forward(request, response);
                    }
                    break;
                case 2: // Login
                    if(usuarioDao.login(usuario, password)){
                        response.sendRedirect("menu.jsp");
                    }else{
                        request.setAttribute("loginUsuario", usuario);
                        request.setAttribute("loginPassword", password);
                        request.setAttribute("mensajeOperacion", "Los datos suministrados son incorrectos");
                        request.getRequestDispatcher("index.jsp").forward(request, response);
                    }
                    break;
//                case 1: // Login
//                    if(usuarioDao.login(usuario, password)){
////                       sesion.setAttribute("id", this);
////                        response.sendRedirect("menu.jsp");
//                        request.setAttribute("mensajeOperacion", "Bienvenido!");
//                        request.getRequestDispatcher("index.jsp").forward(request, response);
//                    }else{
//                    }
//                    break;
//                case 2: // Insertar
//                    if(usuarioDao.insertar()){
//                        usuarioDao.login(usuarioVo.getUsuLogin(), usuarioVo.getUsuPassword());
////                        response.sendRedirect("menu.jsp");
//                        request.setAttribute("mensajeOperacion", "Se agregó ");
//                        request.getRequestDispatcher("index.jsp").forward(request, response);
//                    }else{
//                        request.setAttribute("mensajeOperacion", "Ocurrió un error al registrar el usuario");
//                        request.getRequestDispatcher("index.jsp").forward(request, response);
//                    }
//                    break;
            }
//            request.setAttribute("mensajeOperacion", "Welcome!");
//            request.getRequestDispatcher("index.jsp").forward(request, response);

        }
    }

}
