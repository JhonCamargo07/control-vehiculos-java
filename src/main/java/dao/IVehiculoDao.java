package dao;

import domain.UsuarioVO;
import domain.VehiculoVO;
import java.sql.SQLException;

/**
 *
 * @author Camargo
 */
public interface IVehiculoDAO {
    
    boolean insert(VehiculoVO vehiculoVo, String idUsuario) throws SQLException;
    
    boolean update(VehiculoVO vehiculoVo) throws SQLException;
    
    boolean delect(VehiculoVO vehiculoVo) throws SQLException;
    
    boolean select() throws SQLException;
}
