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
public class UsuarioDAO extends Conexion implements IUsuarioDAO {

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
        sql = "SELECT USUARIO.USUID, ROL.ROLID, USULOGIN, USUPASSWORD, DATNOMBRE, DATAPELLIDO, DATELEFONO, DATCORREO FROM USUARIO INNER JOIN datospersonales as datos ON datos.USUID = usuario.USUID INNER JOIN USUARIO_ROL as usuRol ON usuRol.USUID = usuario.USUID INNER JOIN ROL ON ROL.ROLID = usuRol.ROLID WHERE BINARY USULOGIN = ? AND BINARY USUPASSWORD = ?";

        try {
            conn = Conexion.getConnection();
            // Ejecutar la consulta
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, usuario);
            stmt.setString(2, password);
            rs = stmt.executeQuery();

            if (rs.next()) {
                String idUsuario = rs.getString("USUID");
                String idRol = rs.getString("ROLID");
                String usuLogin = rs.getString("DATCORREO");
                String usuPassword = rs.getString("USUPASSWORD");
                String datNombre = rs.getString("DATNOMBRE");
                String datApellido = rs.getString("DATAPELLIDO");
                String datTelefono = rs.getString("DATELEFONO");
                String datCorreo = rs.getString("DATCORREO");

                usuarioLoginVo = new UsuarioVO(idUsuario, idRol, usuLogin, usuPassword, datNombre, datApellido, datTelefono, datCorreo);
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
        return usuarioLoginVo;
    }

    public boolean existeEmailEnBD(String nombreUsuario) {
        boolean existeUsuarioEnDb = false;
        sql = "SELECT USULOGIN FROM usuario WHERE USULOGIN = ?";

        try {
            conn = Conexion.getConnection();

            stmt = conn.prepareStatement(sql);
            stmt.setString(1, nombreUsuario);
            rs = stmt.executeQuery();

            if (rs.next()) {
                existeUsuarioEnDb = true;
            }

        } catch (SQLException ex) {
            operacionExitosa = false;
            System.out.println("Error al consultar los usuario por el nombre de usuario: " + ex.toString());
            Logger.getLogger(VehiculoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return existeUsuarioEnDb;
    }

    public UsuarioVO consultarUsuarioPorId(String idUsuario) {
        UsuarioVO usuarioVo = new UsuarioVO();
        sql = "SELECT * FROM datospersonales as datos INNER JOIN usuario ON usuario.USUID = datos.USUID WHERE usuario.USUID = ?";

        try {
            conn = Conexion.getConnection();

            stmt = conn.prepareStatement(sql);
            stmt.setString(1, idUsuario);
            rs = stmt.executeQuery();

            if (rs.next()) {
                String id = rs.getString("USUID");
                String nombre = rs.getString("DATNOMBRE");
                String apellido = rs.getString("DATAPELLIDO");
                String telefono = rs.getString("DATELEFONO");
                String correo = rs.getString("DATCORREO");

                usuarioVo = new UsuarioVO(id, "", "", "", nombre, apellido, telefono, correo);
            }

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close(rs);
            close(stmt);
            close(conn);
        }
        return usuarioVo;
    }

    @Override
    public boolean insert(UsuarioVO usuarioVo) {
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
            
            if (rs.next()) {
                UsuarioVO usuarioVo2 = new UsuarioVO(rs.getString("USUID"));
                
                String idUltimoUsuarioRegistrado = usuarioVo2.getIdUsuario();

                sql = "INSERT INTO usuario_rol (ROLID, USUID) VALUES (?,?)";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, usuarioVo.getIdRol());
                stmt.setString(2, idUltimoUsuarioRegistrado);
                stmt.executeUpdate();
                
                sql = "INSERT INTO datospersonales VALUES (null,?,?,?,?,?,?,?)";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, idUltimoUsuarioRegistrado);
                stmt.setString(2, usuarioVo.getDatNombre());
                stmt.setString(3, usuarioVo.getDatApellido());
                stmt.setString(4, "C.C");
                stmt.setString(5, "1010101010");
                stmt.setString(6, usuarioVo.getDatTelefono());
                stmt.setString(7, usuarioVo.getDatCorreo());
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
    public boolean update(UsuarioVO usuarioVo) {
        sql = "UPDATE datospersonales SET DATNOMBRE = ?, DATAPELLIDO = ?, DATELEFONO = ?, DATCORREO = ? WHERE USUID = ?";

        try {
            // Conectarnos a la base de datos
            conn = Conexion.getConnection();
            // Actualizar usuario en la tabla usuario
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, usuarioVo.getDatNombre());
            stmt.setString(2, usuarioVo.getDatApellido());
            stmt.setString(3, usuarioVo.getDatTelefono());
            stmt.setString(4, usuarioVo.getDatCorreo());
            stmt.setString(5, usuarioVo.getIdUsuario());
            stmt.executeUpdate();

            // Si logra actualizar el usuario retorna true
            operacionExitosa = true;

        } catch (SQLException ex) {
            operacionExitosa = false;
            System.out.println("Error al actualizar el usuario: " + ex.toString());
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return operacionExitosa;
    }

    @Override
    public boolean delect(UsuarioVO usuarioVo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean select() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
