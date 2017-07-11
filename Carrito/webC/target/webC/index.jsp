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
		<ul>
					<li style="display:inline;"><a href="/usuariocrud">Mantenimiento de usuarios</a></li>
					<li style="display:inline;"><a href="/usuarioform?op=alta">Alta de usuarios</a></li>
					<li style="display:inline;"><a href="/productocrud">Mantenimiento de productos</a></li>
					<li style="display:inline;"><a href="/productoform?op=alta">Alta de productos</a></li>
				</ul>
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
