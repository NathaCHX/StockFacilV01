package com.stockfacil.controller;

import com.stockfacil.dao.MovimientoDAO;
import com.stockfacil.dao.ProductoDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

/**
 * @author natha
 */
@WebServlet("/movimientos")
public class MovimientoServlet extends HttpServlet {

    private final MovimientoDAO dao = new MovimientoDAO();
    private final ProductoDAO productodao = new ProductoDAO();

    @Override
     protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        mostrar(req, resp);
    }

    private void mostrar(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            req.setAttribute("productos", productodao.listarActivos());
            req.setAttribute("movimientos", dao.listarRecientes(30)); //el 30 es para que el servlet solo muestre 30 registros
            req.getRequestDispatcher("/WEB-INF/jsp/movimientos/lista.jsp").forward(req, resp);
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}
