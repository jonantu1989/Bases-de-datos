<%@ include file="includes/cabecera.jsp"%>
<div>
	<h2>Alta de usuarios</h2>
</div>

<jsp:useBean id="usuario" scope="request"
		class="com.ipartek.formacion.carrito.tipos.Usuario" />

<form action="usuarioform" method="post">
<fieldset>
<label for="username">Username</label>
<input id="username" name="username" value="${usuario.username}"/>
</fieldset>
<fieldset>
<label for="password">Password</label>
<input id="password" name="password" type="password"/>
</fieldset>
<fieldset>
<label for="password2">Repita el password</label>
<input id="password2" name="password2" type="password"/>
</fieldset>
<fieldset>
<input type="submit" value="ALTA">
</fieldset>
</form>
<div id="login">
	<a href="${applicationScope.rutaBase}/login">Login</a>
</div>
<div>
<p class="mensaje">${sessionScope.catalogo}</p>
</div>
<%@ include file="includes/pie.jsp"%>