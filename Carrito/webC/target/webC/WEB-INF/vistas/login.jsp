<%@ include file="includes/cabecera.jsp"%>

<%@ page contentType="text/html; charset=UTF-8" %>

	<h2>Login</h2>
	
	<jsp:useBean id="usuario" scope="request"
		class="com.ipartek.formacion.carrito.tipos.Usuario" />


<form class="form-group" action="catalogo" method="post">
		<fieldset>
			<label for="nombre">Nombre</label> <input id="nombre" name="nombre"
			  required="required" value="${usuario.username}"  />
		</fieldset>
		<fieldset>
			<label for="pass">Contraseña</label> <input type="password" id="pass"
				name="pass"/>
		</fieldset>
		<fieldset>
			<input type="submit" value="LOGIN" />
			
		</fieldset>		
	</form>
	
	
<%--El pie de la pagina. --%>	
<%@ include file="includes/pie.jsp" %>