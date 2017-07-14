<%@ include file="includes/cabecera.jsp"%>

<%@ page contentType="text/html; charset=UTF-8" %>

	<h2>Login</h2>
	
	<jsp:useBean id="usuario" scope="request"
		class="com.ipartek.formacion.carrito.tipos.Usuario" />


<form class="form-group" action="login" method="post">
		<fieldset>
			<label for="nombre">Nombre</label> <input id="nombre" name="nombre"
			  required="required" value="${usuarios.username}"  />
		</fieldset>
		<fieldset>
			<label for="pass">Contraseña</label> <input type="password" id="pass"
				name="pass"value="${usuarios.password}" />
		</fieldset>
		<fieldset>
			<input type="submit" value="Login" />
			
		</fieldset>		
	</form>
	
	
<%--El pie de la pagina. --%>	
<%@ include file="includes/pie.jsp" %>