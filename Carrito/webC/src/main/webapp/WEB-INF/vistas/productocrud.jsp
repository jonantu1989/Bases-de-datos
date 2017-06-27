<%@ include file="includes/cabecera.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div>
	<h2>Mantenimiento de productos</h2>
</div>

<table border=1>
	<thead>
		<tr>
			<th>Id</th>
			<th>Nombre producto</th>
			<th>Precio</th>
			<th>Operaciones</th>
			</tr>
	</thead>
	<tbody>
		<c:forEach items="${applicationScope.productosArr}" var="producto">
			<tr>
				<td>${producto.id}</td>
				<td>${producto.nombre}</td>
				<td>${producto.precio} €</td>
				<td>
					<a href="?op=modificar&id=${producto.id}">Modificar</a>
					<a href="?op=borrar&id=${producto.id}">Borrar</a>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<%@ include file="includes/pie.jsp"%>