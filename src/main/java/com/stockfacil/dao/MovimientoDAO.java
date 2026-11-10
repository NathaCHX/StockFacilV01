package com.stockfacil.dao;

import com.stockfacil.config.Conexion;
import com.stockfacil.model.Movimiento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author natha
 */
public class MovimientoDAO {

    public List<Movimiento> listarRecientes(int cantidad) throws SQLException {
        String sql = "SELECT M.*, P.NOMBRE AS NOMBRE_PRODUCTO, U.NOMBRE AS NOMBRE_USUARIO "
                + "FROM MOVIMIENTO M"
                + "INNER JOIN PRODUCTO P ON P.ID_PRODUCTO = M.ID_PRODUCTO "
                + "INNEE JOIN USUARIO U ON U.ID_USUARIO = M.ID_USUARIO "
                + "ORDER BY M.FECHA DESC, M.ID_MOVIMIENTO DESC LIMIT ?";
        List<Movimiento> lista = new ArrayList<>();

        try (Connection con = Conexion.obtener(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, cantidad);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapear(rs));
                }
            }
        }
        return lista;
    }

    private Movimiento mapear(ResultSet rs) throws SQLException {
        Movimiento m = new Movimiento();
        m.setIdMovimiento(rs.getInt("id_movimiento"));
        m.setIdProducto(rs.getInt("id_producto"));
        m.setNombreProducto(rs.getString("nombre_producto"));
        m.setTipo(rs.getString("tipo"));
        m.setCantidad(rs.getInt("Cantidad"));
        m.setFecha(rs.getTimestamp("fecha").toLocalDateTime());
        m.setIdUsuario(rs.getInt("id_usuario"));
        m.setNombreUsuario(rs.getString("nombre_usuario"));
        m.setNota(rs.getString("nota"));
        return m;
    }
}
