package com.ipartek.formacion.carrito.servlets;

//TODO Mirar en casa en mi repositorio de javaweb el proyecto de catalogoapp el alta, el login y todas las servlets.
// TODO En casa descargar no clonar el ejercicio de mikel
import java.io.IOException;

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
import com.ipartek.formacion.carrito.dao.UsuarioDAOMySQL;
import com.ipartek.formacion.carrito.tipos.Usuario;

@WebServlet("/alta")
public class AltaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static UsuarioDAO dao = null;

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

		dao = new UsuarioDAOMySQL("jdbc:mysql://localhost/catalogo", "root", "");

		HttpSession session = request.getSession();
		log.info("Comenzamos el POST");
		// Declaro los dispatcher aqu� porque en un momento me dieron un extra�o
		// error al declararlos en el momento de necesitarlos
		ServletContext application = request.getServletContext();
		RequestDispatcher login = request.getRequestDispatcher(RUTA_LOGIN);
		RequestDispatcher alta = request.getRequestDispatcher(RUTA_ALTA);
		// if (error==null){
		// session.setAttribute("mensaje",
		// "Int�ntalo de nuevo, por favor");
		// alta.forward(request, response);
		// return;
		// }

		// Se recogen los valores de los atributos de usuario introducidos en el
		// formulario de alta
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String password2 = request.getParameter("password2");
		String nombre_completo = request.getParameter("nombre_completo");
		// id_roles se asigna directamente como usuario est�ndar
		int id_roles = 2;
		// Se crea un objeto usuario con el que trabajar a partir de esos datos
		Usuario usuario = new Usuario(id_roles, 2, nombre_completo, password,
				username);
		// Se extrae el conjunto de usuarios extra�do de la BBDD e introducido
		// en el objeto application en el listener
		UsuarioDAO usuarios = (UsuarioDAO) application.getAttribute("usuarios");

		// Se declara e inicializan las booleanas a partir de las cuales se
		// desarrollar� la l�gica del servlet
		boolean nombreDemasiadoLargo = false;
		if (username != null) {
			nombreDemasiadoLargo = username.length() > 16;
		}
		boolean usuarioExistente = false;

		boolean sinDatos = (username == null || username == ""
				|| password == null || password == "" || password2 == null || password2 == "");
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

		// L�gica de la aplicaci�n
		if (sinDatos) {
			session.setAttribute("mensaje", "Debes rellenar todos los campos");
			alta.forward(request, response);
			return;

		} else if (nombreDemasiadoLargo) {

			session.setAttribute("mensaje",
					"El nombre de usuario debe tener un m�ximo de 8 caracteres");
			alta.forward(request, response);
			return;

		}
		// Se considera que el usuario ya existe s�lo con que coincida el
		// username, de ah� el m�todo validarNombre()
		if (usuarios != null) {
			usuarios.abrir();
			usuarioExistente = usuarios.validarNombre(usuario);
			usuarios.cerrar();
			if (usuarioExistente) {
				session.setAttribute("mensaje", "Usuario ya existente");
				alta.forward(request, response);
				return;

			}
		} else {
			session.setAttribute("mensaje",
					"No hemos podido contactar con la base.");
			alta.forward(request, response);
			return;
		}

		if (!passIguales) {

			session.setAttribute("mensaje", "Las contrase�as no coinciden");
			alta.forward(request, response);

		} else if (esCorrecto) {
			// session.removeAttribute("mensaje");
			session.setAttribute("mensaje", "todo correcto");
			if (usuarios != null) {
				usuarios.abrir();
				usuarios.insert(usuario);
				usuarios.cerrar();
				log.info("Usuario " + usuario.getUsername() + " dado de alta");
				response.sendRedirect(RUTA + login);
			}
		} else {
			session.setAttribute("mensaje", "Int�ntalo de nuevo");
			alta.forward(request, response);
		}
	}
}