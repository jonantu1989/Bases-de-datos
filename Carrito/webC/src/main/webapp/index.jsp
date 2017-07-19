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
		<h3>Mantenimiento de usuarios:</h3>
			<li style="display:inline;"><a href="/usuariocrud">Listado de usuarios</a></li>
			<li style="display:inline;"><a href="/usuarioform?op=alta">Alta de usuarios</a></li>
			<li style="display:inline;"><a href="/usuarioform?op=modificar">Modificar usuario</a></li>
			<li style="display:inline;"><a href="/usuarioform?op=borrar">Borrar usuario</a></li>
		<h3>Mantenimiento productos:</h3>
			<li style="display:inline;"><a href="/productocrud">Listado de productos</a></li>
			<li style="display:inline;"><a href="/productoform?op=alta">Alta de productos</a></li>
			<li style="display:inline;"><a href="/productoform?op=modificar">Modificar producto</a></li>
			<li style="display:inline;"><a href="/productoform?op=borrar">Borrar producto</a></li>
		</ul>
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
			<tr>  <%--Si quito los enlaces no salen porque ya estan arriba--%>
			<%--
				<td>
					<a href="?op=alta&id=${producto.id}">Alta</a>
					<a href="?op=modificar&id=${producto.id}">Modificar</a>
					<a href="?op=borrar&id=${producto.id}">Borrar</a> 
				</td> --%>
				<td>${producto.id}</td>
				<td>${producto.nombre}</td>
				<td>${producto.precio}</td>
		
			</tr>
			
		</c:forEach>
				
	</tbody>
</table>
</html>
