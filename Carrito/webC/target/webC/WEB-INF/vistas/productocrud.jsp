<%@ include file="includes/cabecera.jsp"%>
<%--Para el encoding. --%>
<%@ page contentType="text/html; charset=UTF-8" %>

<%--Cargamos la libreria core de jstl --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>



<%--Titulo --%>
<h2>Mantenimiento de productos.</h2>
<%--Tabla --%>
<table class="table table-hover text-centered" style="margin: 0 auto;">

<%--Titulos de las columnas. --%>
	<thead>
		<tr>
			
			<th>ID producto</th>
			<th>Nombre</th>
			<th>Precio</th>
			
			
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${requestScope.productos}" var="producto">
			<tr>
				
				<td>${producto.id}</td>
				<td>${producto.nombre}</td>
	
				<td>${producto.precio} €</td>
				
				
				
			</tr>
		</c:forEach>
	</tbody>
</table>



<%--pie --%>
<%@ include file="includes/pie.jsp"%>