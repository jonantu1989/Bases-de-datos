package com.ipartek.formacion.tienda.servlets;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.formacion.tienda.dao.DAOException;
import com.ipartek.formacion.tienda.dao.ProductoDAO;
import com.ipartek.formacion.tienda.rutas.Rutas;
import com.ipartek.formacion.tienda.tipos.Producto;

public class ProductoFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		ServletContext applicationProductos = getServletContext();
		ProductoDAO daoProducto = (ProductoDAO) applicationProductos
				.getAttribute("dalProductos");
		// op.
		String op = request.getParameter("opform");

		// Cogiendo los datos
		String nombre = request.getParameter("nombre");
		// Para sacar la id.
		Producto[] productos = daoProducto.findAll(); // Solo
														// sirve
														// para
														// el
														// id.
		// String id = request.getParameter("id");
		String id = String.valueOf(productos.length + 1);
		String descripcion = request.getParameter("descripcion");
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

		Producto producto = new Producto(id, nombre, descripcion, precio);

		switch (op) {
		case "alta":
			try {
				daoProducto.altaProducto(producto);
			} catch (DAOException de) {
				producto.setErrores("El producto ya existe");
				request.setAttribute("producto", producto);
				request.getRequestDispatcher("?op=alta").forward(request,
						response);
				return;

			}
			request.getRequestDispatcher(Rutas.RUTA_SERVLET_LISTADO).forward(
					request, response);

			break;
		case "modificar":
			try {
				daoProducto.modificarProducto(producto);
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
			daoProducto.borrarProducto(producto);
			request.getRequestDispatcher(Rutas.RUTA_SERVLET_LISTADO).forward(
					request, response);
			break;
		}

	}
}
