package com.ipartek.formacion.carrito.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.ipartek.formacion.carrito.dao.UsuarioDAO;
import com.ipartek.formacion.carrito.tipos.Usuario;

public class AltaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static Logger log = Logger.getLogger(AltaServlet.class);

	private final String RUTA = "/WEB-INF/vistas/";
	private final String RUTA_LOGIN = RUTA + "login.jsp";
	private final String RUTA_ALTA = RUTA + "alta.jsp";

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// Se recogen los objetos sesión y aplicación
		HttpSession session = request.getSession();
		ServletContext application = request.getServletContext();

		// Se recogen los valores de los atributos de usuario introducidos en el
		// formulario de alta
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String password2 = request.getParameter("password2");
		String nombre_completo = request.getParameter("nombre_completo");
		// id_roles se asigna directamente como usuario estándar
		int id_roles = 2;
		// Se crea un objeto usuario con el que trabajar a partir de esos datos
		Usuario usuario = new Usuario(id_roles, 2, nombre_completo, password,
				username);
		// Se extrae el conjunto de usuarios extraído de la BBDD e introducido
		// en el objeto application en el listener
		UsuarioDAO usuarios = (UsuarioDAO) application.getAttribute("usuarios");

		// Se declara e inicializan las booleanas a partir de las cuales se
		// desarrollará la lógica del servlet
		boolean nombreDemasiadoLargo = false;
		if (username != null) {
			nombreDemasiadoLargo = username.length() > 16;
		}
		boolean usuarioExistente = false;
		// Se considera que el usuario ya existe sólo con que coincida el
		// username, de ahí el método validarNombre()
		usuarios.abrir();// NullPointerException
		usuarioExistente = usuarios.validarNombre(usuario);
		usuarios.cerrar();
		boolean sinDatos = username == null || username == ""
				|| password == null || password == "" || password2 == null
				|| password2 == "";
		// Se considera que en un principio, sin datos, ambas pass son iguales
		// (igual a null)
		boolean passIguales = true;
		if (password != null) {
			passIguales = password.equals(password2);
		}
		boolean esCorrecto = false;
		if (!sinDatos) {
			esCorrecto = !usuarioExistente && passIguales;
		}

		// Declaro los dispatcher aquí porque en un momento me dieron un extraño
		// error al declararlos en el momento de necesitarlos
		RequestDispatcher login = request.getRequestDispatcher(RUTA_LOGIN);
		RequestDispatcher alta = request.getRequestDispatcher(RUTA_ALTA);

		// Lógica de la aplicación
		if (sinDatos) {

			session.setAttribute("error en alta",
					"Debes rellenar todos los campos");
			alta.forward(request, response);

		} else if (nombreDemasiadoLargo) {

			session.setAttribute("error en alta",
					"El nombre de usuario debe tener un máximo de 8 caracteres");
			alta.forward(request, response);

		} else if (usuarioExistente) {

			session.setAttribute("error en alta", "Usuario ya existente");
			alta.forward(request, response);

		} else if (!passIguales) {

			session.setAttribute("error en alta",
					"Las contraseñas no coinciden");
			alta.forward(request, response);

		} else if (esCorrecto) {

			session.removeAttribute("error en alta");

			usuarios.abrir();
			usuarios.insert(usuario);
			usuarios.cerrar();

			log.info("Usuario " + usuario.getUsername() + " dado de alta");
			login.forward(request, response);

		} else {

			session.setAttribute("error en alta",
					"Inténtalo de nuevo, por favor");
			alta.forward(request, response);
		}
	}
}
