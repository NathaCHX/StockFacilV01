package com.stockfacil.dao;

/**
 *
 * @author natha
 */
public class StockInsuficienteException extends Exception {

    public StockInsuficienteException(String mensaje) {
        super(mensaje);
    }
}
