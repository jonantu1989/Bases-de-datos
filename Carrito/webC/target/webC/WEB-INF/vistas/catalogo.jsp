<%@ include file="includes/cabecera.jsp"%>


		<table style="margin: 0 auto;">
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
				<td>${producto.precio}</td>		
			</tr>
			
		</c:forEach>
				
	</tbody>
</table>
<%@ include file="includes/pie.jsp"%>