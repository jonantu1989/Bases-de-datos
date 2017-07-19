package com.ipartek.formacion.carrito.servlets;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.ipartek.formacion.carrito.dao.CatalogoDAO;
import com.ipartek.formacion.carrito.dao.CatalogoDAOFactory;
import com.ipartek.formacion.carrito.dao.ProductoDAO;
import com.ipartek.formacion.carrito.tipos.Producto;

@WebServlet("/catalogo")
public class CatalogoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static Logger log = Logger.getLogger(CatalogoServlet.class);

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

		// Generar el cat�logo. El cat�logo es un array en el que cada elemento
		// es, a su vez, el primer elemento de la lista de productos
		// de un determinado grupo de productos.

		if (productos != null) {
			productos.abrir();
			application.setAttribute("catalogo", productos.getCatalogo());
			productos.cerrar();
		}

		HttpSession session = request.getSession();

		// Recoger el carrito asociado a la sesi�n o, en caso de que no exista
		// (porque el usuario haya entrado directamente al cat�logo desde
		// URL), crearlo.

		CatalogoDAO carrito = (CatalogoDAO) session.getAttribute("carrito");

		if (carrito == null) {

			carrito = CatalogoDAOFactory.getCarritoDAO();
		}

		// L�gica del servlet seg�n la opci�n con la que haya llegado el usuario

		String op = request.getParameter("op");

		if (op == null) {

			session.setAttribute("carrito", carrito);
			session.setAttribute("numeroProductos",
					carrito.buscarTodosLosProductos().length);

			request.getRequestDispatcher("/WEB-INF/vistas/catalogo.jsp")
					.forward(request, response);

		} else {

			switch (op) {

			case "logout":
				session.setAttribute("carrito", carrito);
				session.setAttribute("numeroProductos",
						carrito.buscarTodosLosProductos().length);
				request.getRequestDispatcher("/WEB-INF/vistas/catalogo.jsp")
						.forward(request, response);
				break;

			case "anadir":

				Producto producto;

				int id = Integer.parseInt(request.getParameter("id"));

				productos.abrir();

				producto = productos.findById(id);

				if (producto != null) {

					productos.iniciarTransaccion();
					try {
						productos.delete(producto);
						carrito.anadirAlCarrito(producto);
						productos.confirmarTransaccion();
					} catch (Exception e) {
						productos.deshacerTransaccion();
					}

					log.info("A�adido un producto al carrito");
				}

				application.setAttribute("catalogo", productos.getCatalogo());
				productos.cerrar();

				application.setAttribute("productos", productos);
				session.setAttribute("carrito", carrito);
				session.setAttribute("numeroProductos",
						carrito.buscarTodosLosProductos().length);

				request.getRequestDispatcher("/WEB-INF/vistas/catalogo.jsp")
						.forward(request, response);

				break;

			default:

				request.getRequestDispatcher("/WEB-INF/vistas/catalogo.jsp")
						.forward(request, response);

			}
		}
	}

}
