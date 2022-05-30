package dao;

import domain.VehiculoVO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.Conexion;

/**
 *
 * @author Camargo
 */
public class VehiculoDAO extends Conexion implements IVehiculoDAO {

    // Variables necesarias para la funcionalidad de la aplicacion
    private Connection conn = null;
    private PreparedStatement stmt = null;
    private ResultSet rs = null;
    private String sql = "";
    private boolean operacionExitosa = false;

    public VehiculoDAO() {
    }

    public List<VehiculoVO> listarCategorias() {
        List<VehiculoVO> categorias = new ArrayList();

        sql = "SELECT * FROM categoria";

        try {
            conn = Conexion.getConnection();

            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                String idCategoria = rs.getString("CATID");
                String nombreCategoria = rs.getString("CATIPO");

                VehiculoVO vehiculoVo = new VehiculoVO("", "", idCategoria, nombreCategoria);

                categorias.add(vehiculoVo);
                System.out.println(categorias);
            }
        } catch (SQLException ex) {
            operacionExitosa = false;
            System.out.println("Error al consultar las categorias: " + ex.toString());
            Logger.getLogger(VehiculoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return categorias;
    }

    public List<VehiculoVO> listarVehiculos(int idCatalogo) {
        List<VehiculoVO> categorias = new ArrayList();

        sql = "SELECT * FROM vehiculo as veh INNER JOIN categoria AS cat ON veh.CATID = cat.CATID INNER JOIN datosPersonales AS datos ON datos.DATID = veh.DATID INNER JOIN usuario as usu ON usu.USUID = datos.USUID WHERE cat.CATID = ?";

        try {
            conn = Conexion.getConnection();

            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idCatalogo);
            rs = stmt.executeQuery();

            while (rs.next()) {
                String modelo = rs.getString("VEHMODELO");
                String idVendedor = rs.getString("USUID");
//                String color = rs.getString("VEHCOLOR");
                String precio = rs.getString("VEHPRECIO");
                String idCategoria = rs.getString("CATID");
                String nombreCategoria = rs.getString("CATIPO");

                VehiculoVO vehiculoVo = new VehiculoVO("", idVendedor, idCategoria, modelo, "", "", precio);

                categorias.add(vehiculoVo);
                System.out.println(categorias);
            }
        } catch (SQLException ex) {
            operacionExitosa = false;
            System.out.println("Error al consultar las categorias: " + ex.toString());
            Logger.getLogger(VehiculoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return categorias;
    }
    public boolean existeVehiculoEnBD(String placa) {
        boolean existeLaPlacaEnDb = false;
        sql = "SELECT VEHPLACA FROM vehiculo WHERE VEHPLACA = ?";

        try {
            conn = Conexion.getConnection();

            stmt = conn.prepareStatement(sql);
            stmt.setString(1, placa);
            rs = stmt.executeQuery();

            if (rs.next()){
                existeLaPlacaEnDb = true;
            }
            
        } catch (SQLException ex) {
            operacionExitosa = false;
            System.out.println("Error al consultar los vehiculos por la placa: " + ex.toString());
            Logger.getLogger(VehiculoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return existeLaPlacaEnDb;
    }

    @Override
    public boolean insert(VehiculoVO vehiculoVo, String idUsuario) {
        sql = "SELECT datos.DATID FROM USUARIO INNER JOIN datospersonales as datos on usuario.USUID = datos.USUID WHERE usuario.USUID = ?";
        try {
            conn = Conexion.getConnection();

            stmt = conn.prepareStatement(sql);
            stmt.setString(1, idUsuario);
            rs = stmt.executeQuery();

            if (rs.next()) {
                vehiculoVo.setDatosId(rs.getString("DATID"));
                sql = "INSERT INTO vehiculo (VEHPLACA, DATID, CATID, VEHMODELO, VEHMARCA, VEHESTADO, VEHPRECIO) VALUES (?,?,?,?,?,?,?)";

                stmt = conn.prepareStatement(sql);
                stmt.setString(1, vehiculoVo.getVehPlaca());
                stmt.setString(2, vehiculoVo.getDatosId());
                stmt.setString(3, vehiculoVo.getCategoId());
                stmt.setString(4, vehiculoVo.getVehModelo());
                stmt.setString(5, vehiculoVo.getVehMarca());
                stmt.setString(6, vehiculoVo.getVehEstado());
                stmt.setString(7, vehiculoVo.getVehPrecio());

                stmt.executeUpdate();

                operacionExitosa = true;

            }

        } catch (SQLException ex) {
            operacionExitosa = false;
            System.out.println("Error al insertar el vehiculo: " + ex.toString());
            Logger.getLogger(VehiculoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
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
