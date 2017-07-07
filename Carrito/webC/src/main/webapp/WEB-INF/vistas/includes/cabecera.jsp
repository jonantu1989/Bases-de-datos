<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
	<html>
		<head>
			<title>Bicis</title>
		</head>
		
		<body>
	
			<header>
				<h1>Bicis</h1>
				<h3>Venta de bicis</h3>
				<p class="bienvenido">Bienvenido ${sessionScope.usuario.username}</p>
			</header>
			
			<nav>
				<ul>
				 	 <li><a href="${applicationScope.catalogo}/catalogo">Catálogo</a></li>
				 	 <li><a href="${applicationScope.login}/login">Login</a></li>
					 <li><a href="${applicationScope.login}/login?op=logout">Logout</a></li>
					 <br/>
					 <li style="display:inline;"><a href="/usuariocrud">Mantenimiento de usuarios</a></li>
					 <li style="display:inline;"><a href="/usuarioform?op=alta">Alta de usuarios</a></li>
					 <li style="display:inline;"><a href="/productocrud">Mantenimiento de productos</a></li>
					 <li style="display:inline;"><a href="/productoform?op=alta">Alta de productos</a></li>
				</ul>
				
				<ul <c:if test="${sessionScope.usuario.id_roles != '1'}">
						style = "display:none"
					</c:if>
				>
					<li><a href="/productocrud">Mantenimiento de productos</a></li>
					<li><a href="/productoform?op=alta">Alta de productos</a></li>
					<li><a href="/usuariocrud">Mantenimiento de usuarios</a></li>
					<li><a href="/usuarioform?op=alta">Alta de usuarios</a></li>
				</ul>
			</nav>