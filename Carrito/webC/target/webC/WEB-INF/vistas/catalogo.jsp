<%@ include file="includes/cabecera.jsp"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
		<table border="1">
	<thead>
		<tr>
			<th>Operaciones</th>
			<th>ID producto</th>
			<th>Nombre</th>
			<th>Precio</th>
			
			
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${requestScope.productos}" var="productos">
			<tr>
				<td>
					<a href="?op=alta&id=${productos.id}">Alta</a>
					<a href="?op=modificar&id=${productos.id}">Modificar</a>
					<a href="?op=borrar&id=${productos.id}">Borrar</a>
				</td>
				<td>${productos.id}</td>
				<td>${productos.nombre}</td>
				<td>${productos.precio}</td>		
			</tr>
			
		</c:forEach>
				
	</tbody>
</table>
</html>