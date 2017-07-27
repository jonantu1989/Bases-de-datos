package com.ipartek.formacion.carrito.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ipartek.formacion.carrito.dao.UsuarioDAO;
import com.ipartek.formacion.carrito.dao.UsuarioDAOMySQL;
import com.ipartek.formacion.carrito.tipos.Usuario;

@WebServlet("/usuariocrud")
public class UsuarioCrudServlet extends HttpServlet {

	public static UsuarioDAO dao = null;

	static final String RUTA_FORMULARIO = "/WEB-INF/vistas/usuarioform.jsp";
	static final String RUTA_LISTADO = "/WEB-INF/vistas/usuariocrud.jsp";
	static final String RUTA_SERVLET_LISTADO = "/usuariocrud";

	private static final long serialVersionUID = 1L;

	private static Logger log = Logger.getLogger(UsuarioCrudServlet.class);

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		dao = new UsuarioDAOMySQL("jdbc:mysql://localhost/catalogo", "root", "");

		log.info("Comenzamos el POST");

		String op = request.getParameter("op");

		dao.abrir();
		if (op == null) {// si op el null se cargan los productos
			// si op el null se cargan los productos
			Usuario[] usuarios = dao.findAll();
			dao.cerrar();

			request.setAttribute("usuarios", usuarios);

			request.getRequestDispatcher(RUTA_LISTADO).forward(request,
					response);
		} else {
			String nombre = request.getParameter("nombre");

			Usuario usuario;

			switch (op) {
			case "modificar":
			case "borrar":
				dao.abrir();
				usuario = dao.findByName(nombre);
				dao.cerrar();
				request.setAttribute("usuario", usuario);
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