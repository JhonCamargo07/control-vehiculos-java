package dao;

import domain.UsuarioVO;
import domain.VehiculoVO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.Conexion;

/**
 *
 * @author Camargo
 */
public class VehiculoDAO extends Conexion implements IVehiculoDAO{
    // Variables necesarias para la funcionalidad de la aplicacion
    private Connection conn = null;
    private PreparedStatement stmt = null;
    private ResultSet rs = null;
    private String sql = "";
    private boolean operacionExitosa = false;

    public VehiculoDAO() {
    }
    
    public List<VehiculoVO> listarCategorias(){
        List<VehiculoVO> categorias = null;
        
        sql = "SELECT * FROM categoria";
        
        try {
            conn = Conexion.getConnection();
            
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                String idCategoria = rs.getString("CATID");
                String nombreCategoria = rs.getString("CATIPO");
                
                VehiculoVO vehiculoVo = new VehiculoVO("","", idCategoria, nombreCategoria);
                
                categorias.add(vehiculoVo);
            }
        } catch (SQLException ex) {
            System.out.println("Error al consultar las categorias: " + ex.toString());
            Logger.getLogger(VehiculoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return categorias;
    }
    
    
    @Override
    public boolean insert(VehiculoVO vehiculoVo, UsuarioVO usuarioVo){
        sql = "SELECT datos.DATID FROM USUARIO INNER JOIN datospersonales as datos on usuario.USUID = datos.USUID WHERE usuario.USUID = ?";
        try {
            conn = Conexion.getConnection();
            
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, usuarioVo.getIdUsuario());
            rs = stmt.executeQuery();
            
            if(rs.next()) {
                vehiculoVo.setDatosId(rs.getString("DATID"));
                sql = "INSERT INTO vehiculo (VEHPLACA, DATID, CATID, VEHMODELO, VEHMARCA, VEHESTADO, VEHPRECIO) VALUES (?,?,?,?,?,?,?)";
                
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, vehiculoVo.getVehPlaca());
                stmt.setString(2, vehiculoVo.getDatosId());
                stmt.setString(3, vehiculoVo.getCategoId());
                stmt.setString(4, vehiculoVo.getVehModelo());
                stmt.setString(5, vehiculoVo.getVehMarca());
                stmt.setString(6, vehiculoVo.getVehEstado());
                stmt.setDouble(7, vehiculoVo.getVehPrecio());

                stmt.executeUpdate();
                
                operacionExitosa = true;
                
            }
            
            
        } catch (SQLException ex) {
            operacionExitosa = false;
            System.out.println("Error al insertar el vehiculo: " + ex.toString());
            Logger.getLogger(VehiculoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
       
       return operacionExitosa;
    }

    @Override
    public boolean update(VehiculoVO vehiculoVo) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delect(VehiculoVO vehiculoVo) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean select() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
