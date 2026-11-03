
package com.stockfacil.model;

/**
 *
 * @author natha
 */
public class Proveedor {

    private int idProveedor;
    private String nombre;
    private String cedulaJuridica;
    private String telefono;
    private String correo;
    private boolean activo = true;

    public int getIdProveedor() {
        return idProveedor;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCedulaJuridica() {
        return cedulaJuridica;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCedulaJuridica(String cedulaJuridica) {
        this.cedulaJuridica = cedulaJuridica;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}
