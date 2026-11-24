package com.stockfacil.dao;

import com.stockfacil.config.Conexion;
import com.stockfacil.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/*** @author natha*/
public class UsuarioDAO {

    private static final String SELECT_BASE
            = "SELECT U.*, R.NOMBRE AS ROL FROM USUARIO U INNER JOIN ROL R ON R.ID_ROL = U.ID_ROL ";

    public Usuario buscarPorUsuario(String usuario) throws SQLException {
        String sql = SELECT_BASE + "WHERE U.USUARIO = ?";

        try (Connection con = Conexion.obtener(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, usuario);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? mapear(rs) : null;
            }
        }
    }

    //NUEVOS METODOS PARA EL MANTENIMIENTO DE USUARIOS
    public List<Usuario> listar() throws SQLException {
        String sql = SELECT_BASE + "ORDER BY u.nombre";
        List<Usuario> lista = new ArrayList<>();
        try (Connection con = Conexion.obtener(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(mapear(rs));
            }
        }
        return lista;
    }

    public Usuario buscarPorId(int id) throws SQLException {
        String sql = SELECT_BASE + "WHERE u.id_usuario = ?";
        try (Connection con = Conexion.obtener(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? mapear(rs) : null;
            }
        }
    }

    public boolean existeUsuario(String usuario, int idExcluir) throws SQLException {
        String sql = "SELECT COUNT(*) FROM usuario WHERE usuario = ? AND id_usuario <> ?";
        try (Connection con = Conexion.obtener(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, usuario);
            ps.setInt(2, idExcluir);
            try (ResultSet rs = ps.executeQuery()) {
                rs.next();
                return rs.getInt(1) > 0;
            }
        }
    }

    public void insertar(Usuario u) throws SQLException {
        String sql = "INSERT INTO usuario (nombre, usuario, clave, id_rol) VALUES (?, ?, ?, ?)";
        try (Connection con = Conexion.obtener(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, u.getNombre());
            ps.setString(2, u.getUsuario());
            ps.setString(3, u.getClave());
            ps.setInt(4, u.getIdRol());
            ps.executeUpdate();
        }
    }

    public void actualizar(Usuario u) throws SQLException {
        boolean cambiaClave = u.getClave() != null;
        String sql = "UPDATE usuario SET nombre = ?, usuario = ?, id_rol = ?, activo = ?"
                + (cambiaClave ? ", clave = ?" : "") + " WHERE id_usuario = ?";
        try (Connection con = Conexion.obtener(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, u.getNombre());
            ps.setString(2, u.getUsuario());
            ps.setInt(3, u.getIdRol());
            ps.setBoolean(4, u.isActivo());
            if (cambiaClave) {
                ps.setString(5, u.getClave());
                ps.setInt(6, u.getIdUsuario());
            } else {
                ps.setInt(5, u.getIdUsuario());
            }
            ps.executeUpdate();
        }
    }

    private Usuario mapear(ResultSet rs) throws SQLException {
        Usuario u = new Usuario();
        u.setIdUsuario(rs.getInt("id_usuario"));
        u.setNombre(rs.getString("nombre"));
        u.setUsuario(rs.getString("usuario"));
        u.setClave(rs.getString("clave"));
        u.setIdRol(rs.getInt("id_rol"));
        u.setRol(rs.getString("Rol"));
        u.setActivo(rs.getBoolean("activo"));
        return u;
    }
}
