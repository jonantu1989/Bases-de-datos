package com.ipartek.formacion.carrito.servlets;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.ipartek.formacion.carrito.dao.UsuarioDAO;
import com.ipartek.formacion.carrito.tipos.Usuario;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static Logger log = Logger.getLogger(LoginServlet.class);

	private final String RUTA = "/WEB-INF/vistas";
	private final String RUTA_LOGIN = RUTA + "/login.jsp";
	private final String RUTA_CATALOGO = RUTA + "/catalogo";

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		log.info("Comenzamos el POST");
		// Declaro los dispatcher aquí porque en un momento me dieron un extraño
		// error al declararlos en el momento de necesitarlos
		ServletContext application = request.getServletContext();
		RequestDispatcher login = request.getRequestDispatcher(RUTA_LOGIN);
		RequestDispatcher catalogo = request
				.getRequestDispatcher(RUTA_CATALOGO);

		// Recogida de datos de la request
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String op = request.getParameter("op");

		UsuarioDAO usuarios = (UsuarioDAO) application.getAttribute("usuarios");
		LinkedList<Usuario> usuariosLogueados = (LinkedList<Usuario>) application
				.getAttribute("usuariosLogueados");
		Usuario usuario;

		if (session.getAttribute("usuario") != null) {
			usuario = (Usuario) session.getAttribute("usuario");
		} else
			usuario = new Usuario(username, password);

		// DECLARACIÓN E INICIALIZACIÓN DE LAS BOOLEANAS SOBRE LAS QUE SE BASARÁ
		// LA LÓGICA DEL SERVLET

		boolean quiereSalir = ("logout").equals(op);
		boolean yaLogueado = ("si").equals(session.getAttribute("logueado"));
		boolean yaEnUsuariosLogueados = false;

		if (usuario.getUsername() != null) {
			if (usuarios != null && usuariosLogueados != null) {
				for (Usuario u : usuariosLogueados) {
					if (usuario.getUsername().equals(u.getUsername())) {
						yaEnUsuariosLogueados = true;
					}
				}
			}
		}
		// sinDatos puede significar que alguien ha intentado loguearse sin
		// datos o que es la primera vez
		// que llega al servlet sin que se le hayan pedido datos aún. Es la
		// condición de partida de llegada
		// al servlet
		boolean sinDatos = (username == null || username == ""
				|| password == "" || password == null);
		boolean usuarioInexistente = false;
		boolean esValido = false;

		if (usuarios != null) {
			usuarios.abrir();
			usuarioInexistente = usuarios.validarNombre(usuario);
			usuarios.cerrar();
		}
		if (usuarioInexistente) {
			session.setAttribute("mensaje",
					"El usuario no existe, date de alta");
			response.sendRedirect(RUTA + "alta.jsp");
			return;

		}

		// Declaración e inicialización de los dispatcher ya que en un
		// momento dado me daba problemas inicializarlos
		// directamente cuando son requeridos.
		// LÓGICA DEL SERVLET SEGÚN LOS VALORES DE LAS BOOLEANAS
		if (quiereSalir) {
			// Se invalida la sesión y se le envía al catálogo que es el
			// punto de partida de la aplicación
			session.invalidate();
			if (usuariosLogueados != null) {
				usuariosLogueados.remove(usuario);
			}
			session = request.getSession();
			session.setAttribute("usuariosLogueados", usuariosLogueados);
			catalogo.forward(request, response);

		} else if (yaLogueado) {
			// Si ya está logueado el login le deja pasar directamente a la
			// página principal, el catálogo
			session.removeAttribute("mensaje");
			catalogo.forward(request, response);

		} else if (sinDatos) {
			// Si no se rellenan los datos se le envía al jsp del login con
			// el mensaje de error. Da el fallo de que un usuario
			// que entra por primera vez a esta página no ha podido rellenar
			// aún ningún dato por lo que se le mostrará el mensaje
			// de error sin que haya interactuado con la página.
			session.setAttribute("mensaje", "Debes rellenar todos los campos");
			login.forward(request, response);

		} else if (usuarioInexistente) {
			// Si el username no existe en la base de datos se le reenvía a
			// la jsp de login con el correspondiente mensaje de error
			session.setAttribute("mensaje", "Usuario no encontrado");
			login.forward(request, response);

		} else if (esValido) {
			// Si el usuario ya está logueado no deja volver a loguearse con
			// el mismo usuario
			if (yaEnUsuariosLogueados) {
				session.setAttribute("mensaje", "Este usuario ya está logueado");
				login.forward(request, response);
				// Si nombre y contraseña son válidos se busca el usuario
				// correspondiente en la base de datos para rellenar el
				// resto de datos
				// como su id_roles y se almacena este usuario en el objeto
				// session.
			} else {
				log.info("Usuario " + usuario.getUsername() + " logueado");
				usuarios.abrir();
				usuario = usuarios.findByName(usuario.getUsername());
				usuarios.cerrar();
				usuariosLogueados.add(usuario);
				application
						.setAttribute("usuariosLogueados", usuariosLogueados);
				session.removeAttribute("mensaje");
				session.setAttribute("logueado", "si");
				session.setAttribute("usuario", usuario);
				// Se le envía al catálogo
				response.sendRedirect(RUTA_CATALOGO);
			}

		} else {
			// En principio, la posibilidad que queda es que el usuario
			// exista pero la password sea incorrecta
			session.setAttribute("mensaje",
					"Contraseña incorrecta, intentalo de nuevo");
			login.forward(request, response);
		}
	}

}