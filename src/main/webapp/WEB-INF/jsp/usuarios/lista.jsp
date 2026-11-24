<%-- 
    Document   : lista
    Created on : 24 nov 2026, 10:50:03 a. m.
    Author     : natha
--%>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../comun/encabezado.jsp" %>
<div class="d-flex justify-content-between align-items-center mb-3">
    <h1 class="h3">Usuarios</h1>
    <a class="btn btn-primary" href="usuarios?accion=nuevo">Nuevo usuario</a>
</div>
<table class="table table-striped bg-white">
    <thead><tr><th>Nombre</th><th>Usuario</th><th>Rol</th><th>Estado</th><th></th></tr></thead>
    <tbody>
        <c:forEach var="u" items="${usuarios}">
            <tr>
                <td><c:out value="${u.nombre}"/></td>
                <td><c:out value="${u.usuario}"/></td>
                <td>${u.rol}</td>
                <td>${u.activo ? 'Activo' : 'Inactivo'}</td>
                <td class="text-end">
                    <a class="btn btn-sm btn-outline-secondary" href="usuarios?accion=editar&id=${u.idUsuario}">Editar</a>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>
<%@ include file="../comun/pie.jsp" %>
