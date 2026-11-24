<%-- 
    Document   : form
    Created on : 24 nov 2026, 10:50:48 a. m.
    Author     : natha
--%>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../comun/encabezado.jsp" %>
<h1 class="h3 mb-3">${u.idUsuario == 0 ? 'Nuevo usuario' : 'Editar usuario'}</h1>
<c:if test="${not empty errores}">
    <div class="alert alert-danger">
        <c:forEach var="e" items="${errores}"><div><c:out value="${e}"/></div></c:forEach>
        </div>
</c:if>
<form method="post" action="usuarios" class="card card-body" style="max-width: 560px">
    <input type="hidden" name="idUsuario" value="${u.idUsuario}">
    <label class="form-label">Nombre completo</label>
    <input class="form-control mb-3" name="nombre" maxlength="100" required value="<c:out value='${u.nombre}'/>">
    <label class="form-label">Usuario</label>
    <input class="form-control mb-3" name="usuario" maxlength="30" required value="<c:out value='${u.usuario}'/>">
    <label class="form-label">
        Contraseña <c:if test="${u.idUsuario != 0}"><small class="text-muted">(déjela vacía para no cambiarla)</small></c:if>
        </label>
        <input class="form-control mb-3" name="clave" type="password" minlength="8" ${u.idUsuario == 0 ? 'required' : ''}>
    <%-- Los id coinciden con la tabla rol: 1 = ADMIN, 2 = OPERADOR --%>
    <label class="form-label">Rol</label>
    <select class="form-select mb-3" name="idRol">
        <option value="1" ${u.idRol == 1 ? 'selected' : ''}>ADMIN</option>
        <option value="2" ${u.idRol == 2 ? 'selected' : ''}>OPERADOR</option>
    </select>
    <c:if test="${u.idUsuario != 0}">
        <div class="form-check mb-3">
            <input class="form-check-input" type="checkbox" name="activo" id="activo" ${u.activo ? 'checked' : ''}>
            <label class="form-check-label" for="activo">Activo (un usuario inactivo no puede entrar)</label>
        </div>
    </c:if>
    <div>
        <button class="btn btn-primary">Guardar</button>
        <a class="btn btn-link" href="usuarios">Cancelar</a>
    </div>
</form>
<%@ include file="../comun/pie.jsp" %>