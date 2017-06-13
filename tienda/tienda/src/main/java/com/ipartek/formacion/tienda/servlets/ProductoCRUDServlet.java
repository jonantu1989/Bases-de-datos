package com.ipartek.formacion.tienda.servlets;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.formacion.tienda.dao.DAOProductoFactory;
import com.ipartek.formacion.tienda.dao.ProductoDAO;
import com.ipartek.formacion.tienda.rutas.Rutas;
import com.ipartek.formacion.tienda.tipos.Producto;

public class ProductoCRUDServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// Primero recogemos los datos...??
		ServletContext applicationProducto = getServletContext();
		ProductoDAO daoProducto = (ProductoDAO) applicationProducto
				.getAttribute("daoProducto");

		if (daoProducto == null) {

			daoProducto = DAOProductoFactory.getProductoDAO();

			// Creamos unos productos de prueba.
			// dalProductos.altaProducto(new ProductoStockImagen());

			applicationProducto.setAttribute("daoProducto", daoProducto);
		}

		// Creamos op.
		String op = request.getParameter("op");

		if (op == null) {

			Producto[] productos = daoProducto.findAll();
			request.setAttribute("productos", productos);
			request.getRequestDispatcher(Rutas.RUTA_LISTADO).forward(request,
					response);
		} else {

			String id = request.getParameter("id");

			Producto producto;

			switch (op) {
			case "modificar":
			case "borrar":
				producto = daoProducto.findById(id);
				request.setAttribute("producto", producto);
			case "alta":
				request.getRequestDispatcher(Rutas.RUTA_FORMULARIO).forward(
						request, response);
				break;
			default:
				request.getRequestDispatcher(Rutas.RUTA_LISTADO).forward(
						request, response);
			}
		}
	}

}
