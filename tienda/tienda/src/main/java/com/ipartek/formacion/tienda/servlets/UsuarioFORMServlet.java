package com.ipartek.formacion.tienda.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.formacion.tienda.dao.DAOException;
import com.ipartek.formacion.tienda.dao.UsuarioDAO;
import com.ipartek.formacion.tienda.tipos.Usuario;

public class UsuarioFORMServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String op = request.getParameter("opform");

		String nombre = request.getParameter("nombre");
		String pass = request.getParameter("pass");
		String pass2 = request.getParameter("pass2");

		RequestDispatcher rutaListado = request
				.getRequestDispatcher(UsuarioCRUDServlet.RUTA_SERVLET_LISTADO);
		RequestDispatcher rutaFormulario = request
				.getRequestDispatcher(UsuarioCRUDServlet.RUTA_FORMULARIO);

		// response.setContentType("text/plain");
		// PrintWriter out = response.getWriter();
		// out.println(op);
		// out.println(nombre);
		// out.println(pass);
		// out.println(pass2);

		if (op == null) {
			rutaListado.forward(request, response);
			return;
		}

		Usuario usuario = new Usuario(nombre, pass);

		ServletContext application = getServletContext();
		UsuarioDAO dao = (UsuarioDAO) application.getAttribute("dao");

		switch (op) {
		case "alta":
			if (pass.equals(pass2)) {
				dao.alta(usuario);
				rutaListado.forward(request, response);
			} else {
				usuario.setErrores("Las contraseņas no coinciden");
				request.setAttribute("usuario", usuario);
				rutaFormulario.forward(request, response);
			}

			break;
		case "modificar":
			if (pass.equals(pass2)) {
				try {
					dao.modificar(usuario);
				} catch (DAOException de) {
					usuario.setErrores(de.getMessage());
					request.setAttribute("usuario", usuario);
					rutaFormulario.forward(request, response);
					return;
				}
				rutaListado.forward(request, response);
			} else {
				usuario.setErrores("Las contraseņas no coinciden");
				request.setAttribute("usuario", usuario);
				rutaFormulario.forward(request, response);
			}

			break;
		case "borrar":
			dao.borrar(usuario);
			rutaListado.forward(request, response);

			break;
		}
	}

}
