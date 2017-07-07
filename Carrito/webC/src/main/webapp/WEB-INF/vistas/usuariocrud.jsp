<%@ include file="includes/cabecera.jsp"%>


<%@ page contentType="text/html; charset=UTF-8" %>

<%--Librerias de jstl que necesitamos. --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>

<h2>Mantenimiento de usuarios</h2>


<%--Tabla que muestra los usuarios --%>
<table border=1 class="table table-hover text-centered">

<%--Cabecera de la tabla. --%>
	<thead>
		<tr>
			<th>Operaciones</th>
			<td>Id</td>
			<td>Id_Roles</td>
			<th>Usuario</th>
			<th>Contraseña</th>
			<th>Nombre Completo</th>
		</tr>
	</thead>
	
	<%--Fila de las tablas. --%>
	<tbody>
		<c:forEach items="${requestScope.productos}" var="producto">
			<tr>
				<td>
					<a href="?op=modificar&id=${producto.id}">Modificar</a>
					<a href="?op=borrar&id=${producto.id}">Borrar</a>
				</td>
				<td>${producto.id}</td>
				<td>${producto.nombre}</td>
				
				<td>${producto.precio} €</td>
				
				
				
			</tr>
		</c:forEach>
	</tbody>
</table>




<%--Pie de la pagina. --%>
<%@ include file="includes/pie.jsp"%>