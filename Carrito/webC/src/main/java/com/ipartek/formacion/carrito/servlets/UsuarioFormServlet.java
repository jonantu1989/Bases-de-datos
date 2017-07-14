package com.ipartek.formacion.carrito.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ipartek.formacion.carrito.dao.DAOException;
import com.ipartek.formacion.carrito.dao.UsuarioDAO;
import com.ipartek.formacion.carrito.tipos.Usuario;

@WebServlet("/usuarioform")
public class UsuarioFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static Logger log = Logger.getLogger(UsuarioFormServlet.class);

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		ServletContext application = getServletContext();
		log.info("Comenzamos el POST");
		String op = request.getParameter("opform");

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String password2 = request.getParameter("password2");
		String nombre_completo = request.getParameter("nombre_completo");

		int id_roles; // id_roles siempre se le asigna 2

		if (request.getParameter("id_roles") == null) {
			id_roles = 2;
		} else {
			id_roles = Integer.parseInt(request.getParameter("id_roles"));
		}

		RequestDispatcher rutaListado = request
				.getRequestDispatcher(UsuarioCrudServlet.RUTA_SERVLET_LISTADO);

		RequestDispatcher rutaFormulario = request
				.getRequestDispatcher(UsuarioCrudServlet.RUTA_FORMULARIO);

		if (op == null) {
			rutaListado.forward(request, response); // NumberFormatException:
													// null
			return;
		}

		Usuario usuario = null;

		UsuarioDAO usuarios = (UsuarioDAO) application.getAttribute("usuarios");

		switch (op) {
		case "alta":

			usuario = new Usuario(id_roles, 0, password, username,
					nombre_completo);

			if (password != null && password2 != null) {
				if (password.equals(password2)) {
					try {
						usuarios.abrir();
						usuarios.insert(usuario);
						usuarios.cerrar();
						log.info("Usuario " + usuario.getUsername()
								+ " dado de alta");
					} catch (DAOException e) {
						// Si falla el insert se coge la excepción que lanza y
						// se le
						// reenvía al formulario con el objeto
						// usuario que estaba metido en la request
						request.setAttribute("usuario", usuario);
						e.printStackTrace();
						rutaListado.forward(request, response);
					}
				} else {
					request.setAttribute("usuario", usuario);
					rutaFormulario.forward(request, response);
				}
				break;
			}
		case "modificar":
			if (password != null && password2 != null) {
				if (password.equals(password2)) {
					try {
						usuarios.abrir();
						usuarios.update(usuario);
						usuarios.cerrar();
						log.info("Usuario modificado");
					} catch (DAOException e) {
						request.setAttribute("usuario", usuario);
						e.printStackTrace();
						rutaFormulario.forward(request, response);

					}
					rutaListado.forward(request, response);
				} else {
					request.setAttribute("usuario", usuario);
					rutaFormulario.forward(request, response);
				}
				break;
			}
		case "borrar":
			if (username == null) {
				if (usuario.getUsername().equals(usuario.getUsername())) {
					if (usuarios != null) {
						usuarios.abrir();
						usuarios.delete(usuario);
						usuarios.cerrar();
						log.info("Usuario borrado");
						rutaFormulario.forward(request, response);
					}
				} else {
					rutaListado.forward(request, response);
				}
				break;
			}
		}
	}
}
