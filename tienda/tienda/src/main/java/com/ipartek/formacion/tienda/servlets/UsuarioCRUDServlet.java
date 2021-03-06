package com.ipartek.formacion.tienda.servlets;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.formacion.tienda.dao.DAOUsuarioFactory;
import com.ipartek.formacion.tienda.dao.UsuarioDAO;
import com.ipartek.formacion.tienda.tipos.Usuario;

public class UsuarioCRUDServlet extends HttpServlet {
	static final String RUTA_FORMULARIO = "/WEB-INF/vistas/usuarioform.jsp";
	static final String RUTA_LISTADO = "/WEB-INF/vistas/usuariocrud.jsp";
	static final String RUTA_SERVLET_LISTADO = "/usuario";

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		ServletContext application = getServletContext();
		UsuarioDAO dao = (UsuarioDAO) application.getAttribute("dao");

		if (dao == null) {
			dao = DAOUsuarioFactory.getUsuarioDAO();

			dao.alta(new Usuario("usuario1", "pass1", null));
			dao.alta(new Usuario("usuario2", "pass2", null));

			application.setAttribute("dao", dao);
		}

		String op = request.getParameter("op");

		if (op == null) {

			Usuario[] usuarios = dao.findAll();

			request.setAttribute("usuarios", usuarios);

			request.getRequestDispatcher(RUTA_LISTADO).forward(request,
					response);
		} else {
			String id = request.getParameter("id");

			Usuario usuario;

			switch (op) {
			case "modificar":
			case "borrar":
				usuario = dao.findById(id);
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
