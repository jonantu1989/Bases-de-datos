<%@ include file="includes/cabecera.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div>
	<h2>Formulario de productos</h2>
</div>
	
	<jsp:useBean id="producto" scope="request"
		class="com.ipartek.formacion.carrito.tipos.Producto" />

	<form action="${applicationScope.rutaBase}/productoform" method="post">
		<fieldset 
			<c:if test="${param.op == 'alta'}">
				style="display:none;"
			</c:if> 
		>
			<label for="id">Id</label> 
			
			<input id="id" name="id" type="number"
			  required="required"  value="${producto.id}" 
			  
				<c:if test="${param.op == 'modificar' or param.op == 'borrar'}">
					readonly="readonly"
			  	</c:if> 
		  	/>
		</fieldset>
		<fieldset>
			<label for="nombre">Nombre</label> 
			
			<input <c:if test="${param.op == 'borrar'}">
					readonly="readonly"
					</c:if> id="nombre" name="nombre"  
				required="required"  value="${producto.nombre}"/>
		</fieldset>
		<fieldset 
			<c:if test="${param.op == 'borrar'}">
				style="display:none;"
			</c:if>
		>
		</fieldset>	
		
		<fieldset 
			<c:if test="${param.op == 'borrar'}">
				style="display:none;"
			</c:if>
		>
			<label for="precio">Precio</label> 
			<input id="precio" name="precio" value="${producto.precio}"/>
		</fieldset>
		<fieldset>
			<input type="submit" value="${fn:toUpperCase(param.op)}" 
				<c:if test="${param.op == null or param.op == ''}">
			  		style="display:none;"
			  	</c:if>
			/>
			<p class="errores">${producto.errores}</p>
			
			<input type="hidden" name="opform" value="${param.op}" />
		</fieldset>
	</form>
	

	
<%@ include file="includes/pie.jsp" %>