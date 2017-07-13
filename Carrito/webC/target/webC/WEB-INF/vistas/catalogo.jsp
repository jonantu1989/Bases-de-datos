<%@ include file="includes/cabecera.jsp"%>


		<table>
	<thead>
		<tr>
			<th>Operaciones</th>
			<th>ID producto</th>
			<th>Nombre</th>
			<th>Precio</th>
			
			
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${requestScope.productos}" var="producto">
			<tr>
				<td>
					<a href="?op=alta&id=${productos.id}">Añadir al CARRITO</a>
					<a href="?op=modificar&id=${productos.id}">Modificar CARRITO</a>
					<a href="?op=borrar&id=${productos.id}">Borrar CARRITO</a>
				</td>
				<td>${productos.id}</td>
				<td>${productos.nombre}</td>
				<td>${productos.precio}</td>		
			</tr>
			
		</c:forEach>
				
	</tbody>
</table>
<%@ include file="includes/pie.jsp"%>