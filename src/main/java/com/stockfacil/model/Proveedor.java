/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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

    public String getcedulaJuridica() {
        return cedulaJuridica;
    }
    
    public String gettelefono() {
        return telefono;
    }
    
    public String getcorreo() {
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

    public void setcedulaJuridica(String cedulaJuridica) {
        this.cedulaJuridica = cedulaJuridica;
    }
    
    public void setcorreo(String correo) {
        this.correo = correo;
    }
    
    public void telefono(String telefono) {
        this.telefono = telefono;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}
