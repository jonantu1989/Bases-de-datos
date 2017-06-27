package com.ipartek.formacion.carrito.servlets;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ipartek.formacion.carrito.dao.ProductoDAO;
import com.ipartek.formacion.carrito.tipos.Producto;

public class ProductoCrudServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static Logger log = Logger.getLogger(ProductoCrudServlet.class);

	static final String RUTA_FORMULARIO = "/WEB-INF/vistas/productoform.jsp";
	static final String RUTA_LISTADO = "/WEB-INF/vistas/productocrud.jsp";
	static final String RUTA_SERVLET_LISTADO = "/productocrud";

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		ServletContext application = getServletContext();
		log.info("Comenzamos el POST");
		ProductoDAO productos = (ProductoDAO) application
				.getAttribute("productos");

		String op = request.getParameter("op");

		if (op == null) {

			if (productos != null) {

				productos.abrir();

				Producto[] productosArr = productos.findAll();

				productos.cerrar();

				application.setAttribute("productosArr", productosArr);

			}

			request.getRequestDispatcher(RUTA_LISTADO).forward(request,
					response);

		} else {

			Producto producto;

			switch (op) {
			case "modificar":
			case "borrar":
				int id = Integer.parseInt(request.getParameter("id"));
				productos.abrir();
				producto = productos.findById(id);
				productos.cerrar();
				request.setAttribute("producto", producto);
			case "alta":
				request.getRequestDispatcher(RUTA_FORMULARIO).forward(request,
						response);
				break;
			default:
				request.getRequestDispatcher(RUTA_LISTADO).forward(request,
						response);
			}
		}
	}

}
