<%@ include file="includes/cabecera.jsp"%>


<%@ page contentType="text/html; charset=UTF-8" %>

<%--Librerias de jstl que necesitamos. --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>

<h2>Mantenimiento de usuarios</h2>


<%--Tabla que muestra los usuarios --%>
<table border=1 class="table table-hover text-centered" style="margin: 0 auto;">

<%--Cabecera de la tabla. --%>
	<thead>
		<tr>
			
			<td>Id</td>
			<td>Id_Roles</td>
			<th>Usuario</th>
			<th>Contraseña</th>
			<th>Nombre Completo</th>
		</tr>
	</thead>
	
	<%--Fila de las tablas. --%>
	<tbody>
		<c:forEach items="${requestScope.usuarios}" var="usuario">
			<tr>
				
				<td>${usuario.id}</td>
				<td>${usuario.id_roles}</td>
				<td>${usuario.username}</td>
				<td>${usuario.password}</td>
				<td>${usuario.nombre_completo}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>




<%--Pie de la pagina. --%>
<%@ include file="includes/pie.jsp"%>