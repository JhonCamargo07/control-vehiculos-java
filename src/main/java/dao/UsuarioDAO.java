package dao;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.Conexion;
import domain.UsuarioVO;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

/**
 *
 * @author Camargo
 */
public class UsuarioDAO extends Conexion implements IUsuarioDao{
    // Variables necesarias para la funcionalidad de la aplicacion
    private Connection conn = null;
    private PreparedStatement stmt = null;
    private ResultSet rs = null;
    private String sql = "";
    private boolean operacionExitosa = false;

    public UsuarioDAO() {
//        try {
//            //Obtener la conexion
//            conn = Conexion.getConnection();
//        } catch (SQLException ex) {
//            System.out.println("Error al conectarse con la BD en UsuarioDAO");
//            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
        
    }
    
    public boolean login(String usuario, String password) {
        
        sql = "SELECT * FROM USUARIO WHERE BINARY USULOGIN = ? AND BINARY USUPASSWORD = ?";
        
        try {
            conn = Conexion.getConnection();
            // Ejecutar la consulta
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, usuario);
            stmt.setString(2, password);
            rs = stmt.executeQuery();
            
            if(rs.next()) {
                operacionExitosa = true;
            }
            
        } catch (SQLException ex) {
            operacionExitosa = false;
            System.out.println("Error al iniciar sesion");
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        
        return operacionExitosa;
    }

    @Override
    public boolean insert(UsuarioVO usuarioVo){
//        sql = "INSERT INTO usuario(USULOGIN, USUPASSWORD) VALUES (?,?)";
        sql = "INSERT INTO USUARIO(USULOGIN, USUPASSWORD) VALUES (?,?)";
        
        try {
            // Conectarnos a la base de datos
            conn = Conexion.getConnection();
            // Ejecutar la insercion
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, usuarioVo.getUsuLogin());
            stmt.setString(2, usuarioVo.getUsuPassword());
            stmt.executeUpdate();
            
            // Si logra insertar el usuario retorna true
            operacionExitosa = true;
            
        } catch (SQLException ex) {
            operacionExitosa = false;
            System.out.println("Error al insertar el usuario: " + ex.toString());
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        
        return operacionExitosa;
    }

    @Override
    public boolean update(UsuarioVO usuarioVo) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delect(UsuarioVO usuarioVo) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean select() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
