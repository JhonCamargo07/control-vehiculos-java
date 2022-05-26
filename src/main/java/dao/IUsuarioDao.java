package dao;

import domain.UsuarioVO;
import java.sql.SQLException;

/**
 *
 * @author Camargo
 */
public interface IUsuarioDao {
    
    boolean insert(UsuarioVO usuarioVo) throws SQLException;
    
    boolean update(UsuarioVO usuarioVo) throws SQLException;
    
    boolean delect(UsuarioVO usuarioVo) throws SQLException;
    
    boolean select() throws SQLException;
}
