package dao;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.Conexion;
import domain.UsuarioVO;

/**
 *
 * @author Camargo
 */
public class UsuarioDAO extends Conexion implements IUsuarioDAO{
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
    
    public UsuarioVO login(String usuario, String password) {
        
        UsuarioVO usuarioLoginVo = null;
        
//        sql = "SELECT * FROM USUARIO INNER JOIN datospersonales ON datospersonales.USUID = usuario.USUID INNER JOIN USUARIO_ROL ON USUARIO_ROL.USUID = USUARIO.USUID INNER JOIN ROL ON ROL.ROLID = USUARIO_ROL.ROLID  WHERE BINARY USULOGIN = ? AND BINARY USUPASSWORD = ?";
        sql = "SELECT USUARIO.USUID, ROL.ROLID, USULOGIN, USUPASSWORD, DATNOMBRE, DATAPELLIDO, DATELEFONO, DATCORREO FROM USUARIO INNER JOIN datospersonales as datos ON datos.USUID = usuario.USUID INNER JOIN USUARIO_ROL as usuRol ON usuRol.USUID = usuario.USUID INNER JOIN ROL ON ROL.ROLID = usuRol.ROLID  WHERE BINARY USULOGIN = ? AND BINARY USUPASSWORD = ?";
        
        try {
            conn = Conexion.getConnection();
            // Ejecutar la consulta
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, usuario);
            stmt.setString(2, password);
            rs = stmt.executeQuery();
            
            if(rs.next()) {
                String idUsuario = rs.getString("USUID");
                String idRol = rs.getString("ROLID");
                String usuLogin = rs.getString("USULOGIN");
                String usuPassword = rs.getString("USUPASSWORD");
                String datNombre = rs.getString("DATNOMBRE");
                String datApellido = rs.getString("DATAPELLIDO");
                String datTelefono = rs.getString("DATELEFONO");
                String datCorreo = rs.getString("DATCORREO");
                
                
                usuarioLoginVo = new UsuarioVO(idUsuario, idRol, usuLogin, usuPassword, datNombre, datApellido, datTelefono, datCorreo);
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
        
        return usuarioLoginVo;
    }
    
//    public static boolean validarSesion(Session sesion){
//        httpSession sesion = 
//        if(session.)
//    }

    @Override
    public boolean insert(UsuarioVO usuarioVo){
//        sql = "INSERT INTO usuario(USULOGIN, USUPASSWORD) VALUES (?,?)";
        sql = "INSERT INTO USUARIO(USULOGIN, USUPASSWORD) VALUES (?,?)";
        
        try {
            // Conectarnos a la base de datos
            conn = Conexion.getConnection();
            // Insertar usuario en la tabla usuario
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, usuarioVo.getUsuLogin());
            stmt.setString(2, usuarioVo.getUsuPassword());
            stmt.executeUpdate();
            
            // Consultar el ultimo usuario registrado (el que acabos de insertar)
            sql = "SELECT * FROM usuario ORDER BY USUID DESC LIMIT 1";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            System.out.println("Se seleccionó el ultimo usuario");
            
            if(rs.next()) {
                UsuarioVO usuarioVo2 = new UsuarioVO(rs.getString("USUID"));
                
                sql = "INSERT INTO usuario_rol (ROLID, USUID) VALUES (?,?)";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, usuarioVo.getIdRol());
                stmt.setString(2, usuarioVo2.getIdUsuario());
                stmt.executeUpdate();
            }
            
            
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
