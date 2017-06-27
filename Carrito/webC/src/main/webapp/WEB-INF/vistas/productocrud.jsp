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
		<c:forEach items="${applicationScope.productosArr}" var="productos">
			<tr>
				<td>${productos.id}</td>
				<td>${productos.nombre}</td>
				<td>${productos.precio} €</td>
				<td>
					<a href="?op=modificar&id=${productos.id}">Modificar</a>
					<a href="?op=borrar&id=${productos.id}">Borrar</a>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<%@ include file="includes/pie.jsp"%>