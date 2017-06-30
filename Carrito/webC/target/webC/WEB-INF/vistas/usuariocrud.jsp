<%@ include file="includes/cabecera.jsp"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div>
<h2>Mantenimiento de usuarios</h2>
</div>

<table border=1>
	<thead>
		<tr>
			<th>Rol</th>
			<th>Username</th>
			<th>Contraseña</th>
			<th>Nombre completo</th>
			<th>Operaciones</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${applicationScope.usuariosArr}" var="usuarios">
			<tr>
				<td>${usuarios.id_roles}</td>	
				<td>${usuarios.username}</td>
				<td>${usuarios.password}</td>
				<td>${usuarios.nombre_completo}</td>
				<td>
					<a href="?op=modificar&id=${usuarios.id}">Modificar</a>
					<a href="?op=borrar&id=${usuarios.id}">Borrar</a>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>


<%@ include file="includes/pie.jsp"%>