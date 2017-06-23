<html>
<head>
<title>TIENDA DE BICIS</title>
</head>
<body>
<h1>TIENDA DE BICIS</h1>
</body>
<h2>CATALOGO DE BICIS</h2>
	<ul>
			<li><a href="login">Login</a></li>
			<li><a href="alta">Alta</a></li>
			<li><a href="login?opcion=logout">Salir</a></li>
		</ul>
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
		<c:forEach items="${requestScope.productos}" var="producto">
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
</html>