<%@ include file="includes/cabecera.jsp" %>

<%--Para el encoding. --%>
<%@ page contentType="text/html; charset=UTF-8" %>

<%--Cargamos las librerias. --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

	<h2>Formulario de productos</h2>
	
	<%--Llamamos a clases. --%>
	<jsp:useBean id="producto" scope="request"
		class="com.ipartek.formacion.carrito.tipos.Producto" />

		

   <%--"Creacion" de los formularios. --%>
	<form action="productocrud" method="post">
	 
	    <%--Nombre. --%>
		<fieldset>
			<label for="nombre">Nombre</label> 
			
			<input id="nombre" name="nombre"
			  required="required"  value="${producto.nombre}" 
			  
			  <c:if test="${param.op == 'modificar' or param.op == 'borrar'}">
			  	
			  </c:if>   
		  	/>
		  	
		  	<%--Que solo se vea el dato de nombre a la hora de borrar. --%>
		  	
		  	<c:if test="${param.op != 'borrar' }">
		</fieldset>
		
		
		<%--id, en el alta no aparece por que se crea automaticamente. --%>
		<c:if test="{param.op != 'alta'">
		<fieldset>
			<label for="id">id</label> 
			<input  id="id" name="id" 
			<c:if test="${param.op=='modificar' }">
			
			value="${producto.id}"
			</c:if>
			/>
		</fieldset>
		</c:if>
		
		
		
		
		<%--Precio --%>
		<fieldset>
			<label for="precio">Precio</label> 
			<input  id="precio" name="precio" 
			<c:if test="${param.op=='modificar' }">
			value="${producto.precio}"
			</c:if>
			/>
		</fieldset>		
		
		
		
		 </c:if>  
		
		<%--El BOTON para validar el formulario. --%>
		<fieldset>
			<input type="submit" value="${fn:toUpperCase(param.op)}" />
			
			
			<input type="hidden" name="opform" value="${param.op}" />
		</fieldset>
	</form>
	
	
	<%--Para que se crea una ventana que hay que validar la operacion de borrado. --%>
	<c:if test="${param.op == 'borrar'}">
		<script>
			document.forms[0].onsubmit = confirmarBorrado;
		</script>
	</c:if>

<%--pie --%>
<%@ include file="includes/pie.jsp" %>