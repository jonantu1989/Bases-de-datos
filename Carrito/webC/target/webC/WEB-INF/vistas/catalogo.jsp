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
		<c:forEach items="${requestScope.producto}" var="producto">
			<tr>
				<td>
					<a href="?op=alta&id=${producto.id}">Alta</a>
					<a href="?op=modificar&id=${producto.id}">Modificar</a>
					<a href="?op=borrar&id=${producto.id}">Borrar</a>
				</td>
				<td>${producto.id}</td>
				<td>${producto.nombre}</td>
				<td>${producto.precio}</td>		
			</tr>
			
		</c:forEach>
				
	</tbody>
</table>
<%@ include file="includes/pie.jsp"%>