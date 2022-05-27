package domain;

/**
 *
 * @author Camargo
 */
public class VehiculoVO {
    private String vehPlaca;
    private String datosId;
    private String categoId;
    private String nombreCategoria;
    private String vehModelo;
    private String vehMarca;
    private String vehEstado;
    private double vehPrecio;

    public VehiculoVO() {
    }

    public VehiculoVO(String vehPlaca) {
        this.vehPlaca = vehPlaca;
    }

    public VehiculoVO(String vehPlaca, String datosId) {
        this.vehPlaca = vehPlaca;
        this.datosId = datosId;
    }

    public VehiculoVO(String vehPlaca, String datosId, String categoId) {
        this.vehPlaca = vehPlaca;
        this.datosId = datosId;
        this.categoId = categoId;
    }
    
    public VehiculoVO(String vehPlaca, String datosId, String categoId, String nombreCategoria) {
        this.vehPlaca = vehPlaca;
        this.datosId = datosId;
        this.categoId = categoId;
        this.nombreCategoria = nombreCategoria;
    }
    
    public VehiculoVO(String vehPlaca, String datosId, String categoId, String vehModelo, String vehMarca, String vehEstado, double vehPrecio) {
        this.vehPlaca = vehPlaca;
        this.datosId = datosId;
        this.categoId = categoId;
        this.vehModelo = vehModelo;
        this.vehMarca = vehMarca;
        this.vehEstado = vehEstado;
        this.vehPrecio = vehPrecio;
    }

    public String getVehPlaca() {
        return this.vehPlaca;
    }

    public void setVehPlaca(String vehPlaca) {
        this.vehPlaca = vehPlaca;
    }

    public String getDatosId() {
        return this.datosId;
    }

    public void setDatosId(String datosId) {
        this.datosId = datosId;
    }

    public String getCategoId() {
        return this.categoId;
    }

    public void setCategoId(String categoId) {
        this.categoId = categoId;
    }

    public String getVehModelo() {
        return this.vehModelo;
    }

    public void setVehModelo(String vehModelo) {
        this.vehModelo = vehModelo;
    }

    public String getVehMarca() {
        return this.vehMarca;
    }

    public void setVehMarca(String vehMarca) {
        this.vehMarca = vehMarca;
    }

    public String getVehEstado() {
        return this.vehEstado;
    }

    public void setVehEstado(String vehEstado) {
        this.vehEstado = vehEstado;
    }

    public double getVehPrecio() {
        return this.vehPrecio;
    }

    public void setVehPrecio(double vehPrecio) {
        this.vehPrecio = vehPrecio;
    }

    public String getNombreCategoria() {
        return this.nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    @Override
    public String toString() {
        return "VehiculoVO{" + "vehPlaca=" + vehPlaca + ", datosId=" + datosId + ", categoId=" + categoId + ", nombreCategoria=" + nombreCategoria + ", vehModelo=" + vehModelo + ", vehMarca=" + vehMarca + ", vehEstado=" + vehEstado + ", vehPrecio=" + vehPrecio + '}';
    }
    
}
