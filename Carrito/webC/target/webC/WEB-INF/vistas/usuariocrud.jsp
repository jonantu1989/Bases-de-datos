<%@ include file="includes/cabecera.jsp"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div>
<h2>Mantenimiento de usuarios</h2>
</div>

<table border=1>
	<thead>
		<tr>
			<th>Operaciones</th>
			<th>Rol</th>
			<th>Id</th>
			<th>Username</th>
			<th>Nombre completo</th>
			
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${applicationScope.usuarios}" var="usuarios">
			<tr>
				<td>${usuarios.Rol}</td>
				<td>${usuarios.id}</td>	
				<td>${usuarios.username}</td>
				<td>${usuarios.password}</td>
				<td><td>${usuarios.operaciones} €
					<a href="?op=modificar&id=${usuarios.id}">Modificar</a>
					<a href="?op=borrar&id=${usuarios.id}">Borrar</a>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>


<%@ include file="includes/pie.jsp"%>