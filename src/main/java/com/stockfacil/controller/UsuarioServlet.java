package com.stockfacil.controller;

import com.stockfacil.dao.UsuarioDAO;
import com.stockfacil.model.Usuario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
import java.rmi.ServerException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/usuarios")
/*** @author natha*/
public class UsuarioServlet extends HttpServlet {

    private final UsuarioDAO dao = new UsuarioDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String accion = req.getParameter("accion");
        try {
            if ("nuevo".equals(accion)) {
                Usuario u = new Usuario();
                u.setIdRol(02);
                mostrarFormulario(u, req, resp);
            } else if ("editar".equals(accion)) {
                mostrarFormulario(dao.buscarPorId(Integer.parseInt(req.getParameter("id"))), req, resp);
            } else {
                req.setAttribute("usuarios", dao.listar());
                req.getRequestDispatcher("/WEB-INF/jsp/usuarios/lista.jsp").forward(req, resp);
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        try {
            //1. PASAR EL FORMULARIO A UN OBJETO
            Usuario u = new Usuario();
            String id = req.getParameter("idUsuario");
            u.setIdUsuario(id == null || id.isBlank() ? 0 : Integer.parseInt(id));
            u.setNombre(req.getParameter("nombre").trim());
            u.setUsuario(req.getParameter("usuario").trim().toLowerCase());
            u.setIdRol(Integer.parseInt(req.getParameter("idRol")));
            u.setActivo(u.getIdUsuario() == 0 || req.getParameter("activo") != null);
            String clave = req.getParameter("clave");

            //2. VALIDAR EN EL SERVIDOR
            List<String> errores = new ArrayList<>();
            if (u.getNombre().isEmpty()) {
                errores.add("El nombre es obligatorio");
            }
            if (u.getUsuario().isEmpty()) {
                errores.add("El usuario es obligatorio");
            }
            if (dao.existeUsuario(u.getUsuario(), u.getIdUsuario())) {
                errores.add("Este usuario ya existe");
            }
            boolean esNuevo = u.getIdUsuario() == 0;
            boolean escribioClave = clave != null && !clave.isBlank();
            if (esNuevo && !escribioClave) {
                errores.add("la contraseña es obligatoria");
            }
            if (escribioClave && clave.length() < 8) {
                errores.add("La contraseña debe tener al menos 8 caracteres");
            }

            if (!errores.isEmpty()) {
                req.setAttribute("errores", errores);
                mostrarFormulario(u, req, resp);
                return;
            }
            //3. LA CONTRASEÑA NUNCA SE GUARDA TAL CUAL LA DIGITE EL USUARIO, SE GUARDA SU HASH
            u.setClave(escribioClave ? BCrypt.hashpw(clave, BCrypt.gensalt()) : null);

            //4. GUARDAR Y VOLVER A LA LISTA
            if (esNuevo) {
                dao.insertar(u);
            } else {
                dao.actualizar(u);
            }
            resp.sendRedirect("usuarios");
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    //metodo de mostrarFormulario
    private void mostrarFormulario(Usuario u, HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("u", u);
        req.getRequestDispatcher("/WEB-INF/jsp/usuarios/form.jsp").forward(req, resp);
    }
}
