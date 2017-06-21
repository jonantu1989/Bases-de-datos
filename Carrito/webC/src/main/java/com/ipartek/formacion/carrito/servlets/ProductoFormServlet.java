package com.ipartek.formacion.carrito.servlets;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.formacion.carrito.dao.DAOException;
import com.ipartek.formacion.carrito.dao.ProductoDAO;
import com.ipartek.formacion.carrito.rutas.Rutas;
import com.ipartek.formacion.carrito.tipos.Producto;

public class ProductoFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// La "application"
		ServletContext applicationProductos = getServletContext();
		ProductoDAO daoProductos = (ProductoDAO) applicationProductos
				.getAttribute("daoProductos");
		// op.
		String op = request.getParameter("opform");

		// Cogiendo los datos
		String nombre = request.getParameter("nombre");
		// Para sacar la id.
		Producto[] productos = daoProductos.findAll(); // Solo
														// sirve
														// para
														// el
														// id.
		// String id = request.getParameter("id");
		String id = String.valueOf(productos.length + 1);
		String precio = request.getParameter("precio");

		if (precio == null) {
			precio = "Sin precio";
		}

		// Miramos si op es null.
		if (op == null) {
			request.getRequestDispatcher(Rutas.RUTA_SERVLET_LISTADO).forward(
					request, response);

			return;
		}

		// Creamos el producto.

		Producto producto = new Producto();

		switch (op) {
		case "alta":
			try {
				daoProductos.insert(producto);
			} catch (DAOException de) {
				producto.setErrores("El producto ya existe");
				request.setAttribute("producto", producto);
				request.getRequestDispatcher("?op=alta").forward(request,
						response);

			}
			request.getRequestDispatcher(Rutas.RUTA_SERVLET_LISTADO).forward(
					request, response);

			break;
		case "modificar":
			try {
				daoProductos.update(producto);
			} catch (DAOException de) {
				producto.setErrores(de.getMessage());
				request.setAttribute("producto", producto);
				request.getRequestDispatcher(Rutas.RUTA_FORMULARIO).forward(
						request, response);
				return;
			}
			request.getRequestDispatcher(Rutas.RUTA_SERVLET_LISTADO).forward(
					request, response);

			// dalProductos.modificarProducto(producto);
			// request.getRequestDispatcher(ConstantesGlobales.RUTA_SERVLET_LISTADO).forward(request,
			// response);

			break;
		case "borrar":
			daoProductos.delete(producto);
			request.getRequestDispatcher(Rutas.RUTA_SERVLET_LISTADO).forward(
					request, response);
			break;
		}

	}
}
