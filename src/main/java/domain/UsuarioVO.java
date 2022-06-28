package domain;

/**
 *
 * @author Camargo
 */
public class UsuarioVO {

    private String idUsuario;
    private String idRol;
    private String usuLogin;
    private String usuPassword;
    private String datNombre;
    private String datApellido;
    private String datTelefono;
    private String datCorreo;

    public UsuarioVO() {
    }

    public UsuarioVO(String usuId) {
        this.idUsuario = usuId;
    }

    public UsuarioVO(String idRol, String usuLogin, String usuPassword) {
        this.idRol = idRol;
        this.usuLogin = usuLogin;
        this.usuPassword = usuPassword;
    }

    public UsuarioVO(String usuId, String idRol, String usuLogin, String usuPassword) {
        this.idUsuario = usuId;
        this.idRol = idRol;
        this.usuLogin = usuLogin;
        this.usuPassword = usuPassword;
    }

    public UsuarioVO(String idUsuario, String idRol, String usuLogin, String usuPassword, String datNombre, String datApellido, String datTelefono, String datCorreo) {
        this.idUsuario = idUsuario;
        this.idRol = idRol;
        this.usuLogin = usuLogin;
        this.usuPassword = usuPassword;
        this.datNombre = datNombre;
        this.datApellido = datApellido;
        this.datTelefono = datTelefono;
        this.datCorreo = datCorreo;
    }

    public UsuarioVO(String idRol, String usuLogin, String usuPassword, String datNombre, String datApellido, String datTelefono, String datCorreo) {
        this.idRol = idRol;
        this.usuLogin = usuLogin;
        this.usuPassword = usuPassword;
        this.datNombre = datNombre;
        this.datApellido = datApellido;
        this.datTelefono = datTelefono;
        this.datCorreo = datCorreo;
    }

    public String getIdUsuario() {
        return this.idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getIdRol() {
        return idRol;
    }

    public void setIdRol(String idRol) {
        this.idRol = idRol;
    }

    public String getUsuLogin() {
        return this.usuLogin;
    }

    public void setUsuLogin(String usuLogin) {
        this.usuLogin = usuLogin;
    }

    public String getUsuPassword() {
        return this.usuPassword;
    }

    public void setUsuPassword(String usuPassword) {
        this.usuPassword = usuPassword;
    }

    public String getDatNombre() {
        return datNombre;
    }

    public void setDatNombre(String datNombre) {
        this.datNombre = datNombre;
    }

    public String getDatApellido() {
        return datApellido;
    }

    public void setDatApellido(String datApellido) {
        this.datApellido = datApellido;
    }

    public String getDatTelefono() {
        return datTelefono;
    }

    public void setDatTelefono(String datTelefono) {
        this.datTelefono = datTelefono;
    }

    public String getDatCorreo() {
        return datCorreo;
    }

    public void setDatCorreo(String datCorreo) {
        this.datCorreo = datCorreo;
    }

    @Override
    public String toString() {
        return "UsuarioVO{" + "idUsuario=" + idUsuario + ", idRol=" + idRol + ", usuLogin=" + usuLogin + ", usuPassword=" + usuPassword + ", datNombre=" + datNombre + ", datApellido=" + datApellido + ", datTelefono=" + datTelefono + ", datCorreo=" + datCorreo + '}';
    }

}
