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
import com.ipartek.formacion.carrito.dao.UsuarioDAO;
import com.ipartek.formacion.carrito.dao.UsuarioDAOMySQL;
import com.ipartek.formacion.carrito.tipos.Usuario;

@WebServlet("/usuarioform")
public class UsuarioFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static UsuarioDAO dao = null;

	private static Logger log = Logger.getLogger(UsuarioFormServlet.class);

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		dao = new UsuarioDAOMySQL("jdbc:mysql://localhost/catalogo", "root", "");

		RequestDispatcher rutaListado = request
				.getRequestDispatcher(UsuarioCrudServlet.RUTA_SERVLET_LISTADO);
		RequestDispatcher rutaFormulario = request
				.getRequestDispatcher(UsuarioCrudServlet.RUTA_FORMULARIO);

		// recojer la opcion que se carga en la url
		String op = request.getParameter("opform");

		// variables del objeto Usuario
		String nombre = request.getParameter("nombre");
		String pass = request.getParameter("pass");
		String pass2 = request.getParameter("pass2");
		String admin = "admin";

		// crear objeto Pproducto
		Usuario usuario = new Usuario(nombre, pass);

		// actuar en consecuencia de la opcion recogida anteriormente

		if (op == null) {
			rutaListado.forward(request, response);
			return;
		}
		switch (op) {
		case "alta":
			if ((nombre == null || nombre == "")
					|| (pass == null || pass == "")
					|| (pass2 == null || pass2 == "")) {
				log.info("alta de usuario con id '" + nombre + "' erronea");

				usuario.setErrores("- Todos campos deben estar rellenados ");

				request.setAttribute("usuario", usuario);
				rutaFormulario.forward(request, response);

			} else if (pass.equals(pass2)) {
				log.info("usuario: '" + nombre + "' dado de alta por el admin");

				try {
					log.info("producto con id '" + nombre + "' dado de alta");
					dao.abrir();
					dao.insert(usuario);
					dao.cerrar();
					rutaListado.forward(request, response);
				} catch (DAOException a) {
					log.info("usuario con  '" + nombre
							+ "' repetido, el alta no ha sido finalizada");
					usuario.setErrores("Usuario ya existente");
					request.setAttribute("usuario", usuario);
					rutaFormulario.forward(request, response);
				}
			} else {
				log.info("error al crear el usuario: '" + nombre
						+ "' , las contraseñas no coinciden");

				usuario.setErrores("Las contraseñas no coinciden");
				request.setAttribute("usuario", usuario);
				rutaFormulario.forward(request, response);

			}

			break;

		case "modificar":
			if (admin.equals(nombre)) {
				log.info("el usuario: '" + nombre + "' no se puede modificar");

				usuario.setErrores("El administrador no puede ser modificado");
				request.setAttribute("usuario", usuario);
				rutaFormulario.forward(request, response);
			} else if ((nombre == null || nombre == "")
					|| (pass == null || pass == "")
					|| (pass2 == null || pass2 == "")) {
				log.info("alta de usuario con id '" + nombre + "' erronea");

				usuario.setErrores("- Todos campos deben estar rellenados ");

				request.setAttribute("usuario", usuario);
				rutaFormulario.forward(request, response);
			} else if (pass.equals(pass2)) {
				try {
					log.info("usuario: '" + nombre + "' modificado");

					dao.abrir();
					dao.update(usuario);
					dao.cerrar();
				} catch (DAOException de) {
					log.info("error al modificar el usuario: '" + nombre
							+ "' , no se ha completado la modificacion");

					usuario.setErrores(de.getMessage());
					request.setAttribute("usuario", usuario);
					rutaFormulario.forward(request, response);
					return;
				}
				rutaListado.forward(request, response);
			} else {
				log.info("error al modificar el usuario: '" + nombre
						+ "' , las contraseñas no coinciden");

				usuario.setErrores("Las contraseñas no coinciden");
				request.setAttribute("usuario", usuario);
				rutaFormulario.forward(request, response);
			}

			break;
		case "borrar":
			if (admin.equals(nombre)) {
				log.info("el usuario: '" + nombre + "' no se puede borrar");

				usuario.setErrores("El administrador no puede ser eliminado");
				request.setAttribute("usuario", usuario);
				rutaFormulario.forward(request, response);
			} else {
				log.info("usuario: '" + nombre + "' borrado");

				dao.abrir();
				dao.delete(usuario);
				dao.cerrar();
				rutaListado.forward(request, response);
			}

			break;
		}
	}

}
