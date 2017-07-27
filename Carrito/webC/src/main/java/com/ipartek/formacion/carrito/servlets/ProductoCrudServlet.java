package com.ipartek.formacion.carrito.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ipartek.formacion.carrito.dao.ProductoDAO;
import com.ipartek.formacion.carrito.dao.ProductoDAOMySQL;
import com.ipartek.formacion.carrito.tipos.Producto;

@WebServlet("/productocrud")
public class ProductoCrudServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static ProductoDAO dao = null;

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
		dao = new ProductoDAOMySQL("jdbc:mysql://localhost/catalogo", "root",
				"");

		log.info("Comenzamos el POST");

		String op = request.getParameter("op");

		// actuar en consecuencia de la opcion
		dao.abrir();
		if (op == null) {// si op el null se cargan los productos
			// si op el null se cargan los productos
			Producto[] productos = dao.findAll();
			dao.cerrar();
			request.setAttribute("productos", productos);

			request.getRequestDispatcher(RUTA_LISTADO).forward(request,
					response);
		} else {
			int id;
			if (request.getParameter("id") == null
					|| request.getParameter("id") == "") {
				id = 0;
			} else {
				id = Integer.parseInt(request.getParameter("id"));
			}
			Producto producto;

			switch (op) {
			case "modificar":
			case "borrar":
				dao.abrir();
				producto = dao.findById(id);
				dao.cerrar();
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
