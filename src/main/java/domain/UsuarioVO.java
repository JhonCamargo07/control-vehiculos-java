package domain;

/**
 *
 * @author Camargo
 */
public class UsuarioVO {
    private String usuId;
    private String usuLogin;
    private String usuPassword;

    public UsuarioVO() {
    }

    public UsuarioVO(String usuId) {
        this.usuId = usuId;
    }

    public UsuarioVO(String usuLogin, String usuPassword) {
        this.usuLogin = usuLogin;
        this.usuPassword = usuPassword;
    }

    public UsuarioVO(String usuId, String usuLogin, String usuPassword) {
        this.usuId = usuId;
        this.usuLogin = usuLogin;
        this.usuPassword = usuPassword;
    }

    public String getUsuId() {
        return this.usuId;
    }

    public void setUsuId(String usuId) {
        this.usuId = usuId;
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
    
}
