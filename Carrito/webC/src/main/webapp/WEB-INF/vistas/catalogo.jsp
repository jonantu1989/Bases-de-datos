<%@ include file="includes/cabecera.jsp"%>


		<table>
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
				<td>${productos.id}</td>
				<td>${productos.nombre}</td>
				<td>${productos.precio}</td>		
			</tr>
			
		</c:forEach>
				
	</tbody>
</table>
<%@ include file="includes/pie.jsp"%>