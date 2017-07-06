<%@ include file="includes/cabecera.jsp"%>
<%--Para el encoding. --%>
<%@ page contentType="text/html; charset=UTF-8" %>

<%--Cargamos la libreria core de jstl --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>



<%--Titulo --%>
<h2>Mantenimiento de productos.</h2>
<%--Tabla --%>
<table class="table table-hover text-centered">

<%--Titulos de las columnas. --%>
	<thead>
		<tr>
			<th>Operaciones</th>
			<th>Id</th>
			<th>Nombre</th>
			
			<th>Precio</th>
			

		</tr>
	</thead>
	<tbody>
	
	<%--Bucle de lectura de elementos de la tienda. --%>
		<c:forEach items="${requestScope.producto}" var="producto">
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



<%--pie --%>
<%@ include file="includes/pie.jsp"%>