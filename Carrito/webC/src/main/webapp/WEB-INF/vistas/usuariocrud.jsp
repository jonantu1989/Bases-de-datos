<%@ include file="includes/cabecera.jsp"%>


<%@ page contentType="text/html; charset=UTF-8" %>

<%--Librerias de jstl que necesitamos. --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>

<h2>Mantenimiento de usuarios</h2>


<%--Tabla que muestra los usuarios --%>
<table class="table table-hover text-centered">

<%--Cabecera de la tabla. --%>
	<thead>
		<tr>
			<th>Operaciones</th>
			<th>Usuario</th>
			<th>Contraseña</th>
			<th>Nombre Completo</th>
		</tr>
	</thead>
	
	<%--Fila de las tablas. --%>
	<tbody>
		<c:forEach var="usuario" items="${usuarios}" >
			<tr>
				<td>
					<a href="?op=modificar&id=${usuario.id}">Modificar</a>
					<a href="?op=borrar&id=${usuario.id}">Borrar</a>
				</td>
				<td>${usuario.username}</td>
				<td>${usuario.password}</td>
				<td>${usuario.nombreCompleto}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>




<%--Pie de la pagina. --%>
<%@ include file="includes/pie.jsp"%>