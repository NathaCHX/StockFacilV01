package com.stockfacil.model;

import java.time.LocalDateTime;

/**
 *
 * @author natha
 */
public class Movimiento {

    private int idMovimiento;
    private int idProducto;
    private String nombreProducto;
    private String tipo;
    private int cantidad;
    private LocalDateTime fecha;
    private int idUsuario;
    private String nombreUsuario;
    private String nota;

    public int getIdMovimiento() {
        return idMovimiento;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public String getTipo() {
        return tipo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public String getFechaTexto() {
        return fecha == null ? "" : fecha.toString().replace('T', ' ').substring(0, 16);
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getNota() {
        return nota;
    }

    public void setIdMovimiento(int idMovimiento) {
        this.idMovimiento = idMovimiento;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

}
