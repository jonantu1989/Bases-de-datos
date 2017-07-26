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
		// Declaro los dispatcher aqu� porque en un momento me dieron un extra�o
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

		// DECLARACI�N E INICIALIZACI�N DE LAS BOOLEANAS SOBRE LAS QUE SE BASAR�
		// LA L�GICA DEL SERVLET

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
		// que llega al servlet sin que se le hayan pedido datos a�n. Es la
		// condici�n de partida de llegada
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

		// Declaraci�n e inicializaci�n de los dispatcher ya que en un
		// momento dado me daba problemas inicializarlos
		// directamente cuando son requeridos.
		// L�GICA DEL SERVLET SEG�N LOS VALORES DE LAS BOOLEANAS
		if (quiereSalir) {
			// Se invalida la sesi�n y se le env�a al cat�logo que es el
			// punto de partida de la aplicaci�n
			session.invalidate();
			if (usuariosLogueados != null) {
				usuariosLogueados.remove(usuario);
			}
			session = request.getSession();
			session.setAttribute("usuariosLogueados", usuariosLogueados);
			catalogo.forward(request, response);

		} else if (yaLogueado) {
			// Si ya est� logueado el login le deja pasar directamente a la
			// p�gina principal, el cat�logo
			session.removeAttribute("mensaje");
			catalogo.forward(request, response);

		} else if (sinDatos) {
			// Si no se rellenan los datos se le env�a al jsp del login con
			// el mensaje de error. Da el fallo de que un usuario
			// que entra por primera vez a esta p�gina no ha podido rellenar
			// a�n ning�n dato por lo que se le mostrar� el mensaje
			// de error sin que haya interactuado con la p�gina.
			session.setAttribute("mensaje", "Debes rellenar todos los campos");
			login.forward(request, response);

		} else if (usuarioInexistente) {
			// Si el username no existe en la base de datos se le reenv�a a
			// la jsp de login con el correspondiente mensaje de error
			session.setAttribute("mensaje", "Usuario no encontrado");
			login.forward(request, response);

		} else if (esValido) {
			// Si el usuario ya est� logueado no deja volver a loguearse con
			// el mismo usuario
			if (yaEnUsuariosLogueados) {
				session.setAttribute("mensaje", "Este usuario ya est� logueado");
				login.forward(request, response);
				// Si nombre y contrase�a son v�lidos se busca el usuario
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
				// Se le env�a al cat�logo
				response.sendRedirect(RUTA_CATALOGO);
			}

		} else {
			// En principio, la posibilidad que queda es que el usuario
			// exista pero la password sea incorrecta
			session.setAttribute("mensaje",
					"Contrase�a incorrecta, intentalo de nuevo");
			login.forward(request, response);
		}
	}

}