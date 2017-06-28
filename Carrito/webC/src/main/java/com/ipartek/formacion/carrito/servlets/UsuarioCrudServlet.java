package com.ipartek.formacion.carrito.servlets;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ipartek.formacion.carrito.dao.DAOUsuarioFactory;
import com.ipartek.formacion.carrito.dao.UsuarioDAO;
import com.ipartek.formacion.carrito.tipos.Usuario;

@WebServlet("/usuariocrud")
public class UsuarioCrudServlet extends HttpServlet {

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

		ServletContext application = getServletContext();
		log.info("Comenzamos el POST");
		UsuarioDAO usuarios = (UsuarioDAO) application.getAttribute("usuarios");

		String op = request.getParameter("op");

		if (op == null) {

			if (usuarios != null) {

				usuarios = DAOUsuarioFactory.getUsuarioDAO();

				usuarios.abrir();
				Usuario[] usuariosArr = usuarios.findAll();
				usuarios.cerrar();

				application.setAttribute("usuariosArr", usuariosArr);

			}

			request.getRequestDispatcher(RUTA_LISTADO).forward(request,
					response);

		} else {

			Usuario usuario;

			switch (op) {
			case "modificar":
			case "borrar":
				String username = request.getParameter("username");
				usuarios.abrir();
				usuario = usuarios.findByName(username);
				usuarios.cerrar();
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
