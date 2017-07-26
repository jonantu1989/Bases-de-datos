<%@ include file="includes/cabecera.jsp" %>


<%--Para el encoding. --%>
<%@ page contentType="text/html; charset=UTF-8" %>

<%--Cargamos las librerias de jstl. --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

	<h2>Formulario de usuarios</h2>
	
	<%--Cargamos la clase usuario. --%>
	<jsp:useBean id="usuario" scope="request"
		class="com.ipartek.formacion.carrito.tipos.Usuario" />

<%--Formulario dependiente de la opcion elegida. --%>
	<form action="login" method="post">
		<fieldset>
			<label for="username">Username</label> 
			
			<input id="username" name="username"
			  required="required" minlength="4" value="${usuario.username}" 
			  
			  <c:if test="${param.op == 'modificar' or param.op == 'borrar'}">
			  
			  </c:if>   
		  	/>
		</fieldset>
		
	
		<fieldset>
			<label for="pass">Contraseña</label> <input type="password" id="pass"
				name="pass" />
		</fieldset>
		<fieldset>
			<label for="pass2">Contraseña otra vez</label> <input type="password" id="pass2"
				name="pass2" />
		</fieldset>
		<fieldset>
			<input type="submit" value="${fn:toUpperCase(param.op)}" />
			
			
			<input type="hidden" name="opform" value="${param.op}" />
		</fieldset>
	</form>
	
	<c:if test="${param.op == 'borrar'}">
		<script>
			document.forms[0].onsubmit = confirmarBorrado;
		</script>
	</c:if>


<%--Pie de la pagina. --%>	
<%@ include file="includes/pie.jsp" %>