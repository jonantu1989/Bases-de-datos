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
			<th>Contrase�a</th>
			<th>Nombre completo</th>
			<th>Operaciones</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${applicationScope.usuariosArr}" var="usuario">
			<tr>
				<td>${usuario.id_roles}</td>	
				<td>${usuario.username}</td>
				<td>${usuario.password}</td>
				<td>${usuario.nombre_completo}</td>
				<td>
					<a href="?op=modificar&id=${usuario.id}">Modificar</a>
					<a href="?op=borrar&id=${usuario.id}">Borrar</a>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>


<%@ include file="includes/pie.jsp"%>