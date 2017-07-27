package com.ipartek.formacion.carrito.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ipartek.formacion.carrito.dao.DAOException;
import com.ipartek.formacion.carrito.dao.ProductoDAO;
import com.ipartek.formacion.carrito.dao.ProductoDAOMySQL;
import com.ipartek.formacion.carrito.tipos.Producto;

@WebServlet("/productoform")
public class ProductoFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static ProductoDAO dao = null;

	private static Logger log = Logger.getLogger(ProductoFormServlet.class);

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		dao = new ProductoDAOMySQL("jdbc:mysql://localhost/catalogo", "root",
				"");

		RequestDispatcher rutaListado = request
				.getRequestDispatcher(ProductoCrudServlet.RUTA_SERVLET_LISTADO);
		RequestDispatcher rutaFormulario = request
				.getRequestDispatcher(ProductoCrudServlet.RUTA_FORMULARIO);

		// La application.
		// Recoger el objeto application del ServletContext

		log.info("Comenzamos el POST");
		// Regocoger la opción elegida por el usuario en el formulario enviada
		// por url
		String op = request.getParameter("opform");
		// variables del objeto Producto
		int id;
		String nombre = request.getParameter("nombre");
		String descripcion = request.getParameter("descripcion");
		double precio;

		// recoger valores para inicializar variables
		if (request.getParameter("id") == null
				|| request.getParameter("id") == "") {
			id = 0;
		} else {
			id = Integer.parseInt(request.getParameter("id"));// pasar de String
																// a int
		}
		if (request.getParameter("precio") == null
				|| request.getParameter("precio") == "") {
			precio = 0;
		} else {
			precio = Double.parseDouble(request.getParameter("precio"));// pasar
																		// de
																		// String
																		// a
																		// double

		}

		if (request.getParameter("stock") == null
				|| request.getParameter("stock") == "") {

		} else {

		}

		// crear objeto Pproducto
		Producto producto = new Producto(id, nombre, precio);
		producto.setId(id);

		// actuar en consecuencia de la opcion recogida anteriormente
		if (op == null) {
			rutaListado.forward(request, response);
			return;
		}

		switch (op) {
		case "alta":

			if (id == 0 || (nombre == null || nombre == "")
					|| (descripcion == null || descripcion == "")
					|| precio == 0) {
				log.info("alta de producto con id '" + id + "' erronea");

				producto.setErrores("- Los campos deben estar rellenados </br> - ID y precio deben ser numericos y no tener valor 0");

				request.setAttribute("producto", producto);
				rutaFormulario.forward(request, response);

			} else {
				try {
					log.info("producto  '" + nombre + "' dado de alta");
					dao.abrir();
					dao.insert(producto);
					dao.cerrar();

					rutaListado.forward(request, response);
				} catch (DAOException a) {
					log.info("producto con id '" + id
							+ "' repetida, el alta no ha sido finalizada");
					producto.setErrores("ID ya existente");
					request.setAttribute("producto", producto);
					rutaFormulario.forward(request, response);

				}
			}

			break;
		case "modificar":
			if (id == 0 || (nombre == null || nombre == "")
					|| (descripcion == null || descripcion == "")
					|| precio == 0) {
				log.info("modificacion de producto con id '" + id + "' erronea");
				producto.setErrores("Los campos deben estar rellenados y no deben tener valor 0");
				request.setAttribute("producto", producto);
				rutaFormulario.forward(request, response);

			} else
				try {

					dao.abrir();
					dao.update(producto);
					dao.cerrar();
					log.info("producto con id '" + id + "' modificado");
				} catch (DAOException de) {
					log.info("Error al modificar producto con id '" + id
							+ "', la modificacion no ha sido finalizada");

					producto.setErrores(de.getMessage());
					request.setAttribute("producto", producto);
					rutaFormulario.forward(request, response);
					return;
				}
			rutaListado.forward(request, response);

			break;
		case "borrar":
			dao.abrir();
			dao.delete(producto);
			dao.cerrar();
			rutaListado.forward(request, response);
			log.info("producto con id '" + id + "' borrado");
			break;
		}
	}

}