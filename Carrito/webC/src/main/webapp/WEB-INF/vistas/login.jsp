<%@ include file="includes/cabecera.jsp"%>

<%@ page contentType="text/html; charset=UTF-8" %>

	<h2>Login</h2>
	
	<jsp:useBean id="usuario" scope="request"
		class="com.ipartek.formacion.carrito.tipos.Usuario" />


<form action="login" method="post">
	<fieldset>
		<label for="username">Username</label>
		<input id="username" name="username" value="${usuarios.username}"/>
	</fieldset>
	<fieldset>
		<label for="password">Password</label>
		<input id="password" name="password" type="password" value="${usuarios.password}"/>
		</fieldset>
	<fieldset>
		<input type="submit" value="LOGIN"/>
	</fieldset>
</form>

<div id="alta">
	<a href="${applicationScope.rutaBase}/alta">Alta</a>
</div>

<div>
	<p class="mensaje">${sessionScope.mensaje }</p>
</div>
<%@ include file="includes/pie.jsp"%>