package com.ipartek.formacion.carrito.servlets;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ipartek.formacion.carrito.dao.DAOUsuarioFactory;
import com.ipartek.formacion.carrito.dao.UsuarioDAO;
import com.ipartek.formacion.carrito.tipos.Usuario;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/* package */static final String RUTA = "/WEB-INF/vistas/";
	private static final String RUTA_PRINCIPAL = RUTA + "productoform.jsp";
	private static final String RUTA_LOGIN = RUTA + "login.jsp";

	public static final int TIEMPO_INACTIVIDAD = 30 * 60;

	/* package */static final int MINIMO_CARACTERES = 4;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// Recoger datos de vistas
		String nombre = request.getParameter("nombre");
		String pass = request.getParameter("pass");

		String opcion = request.getParameter("opcion");

		// Crear modelos en base a los datos
		Usuario usuario = new Usuario();
		usuario.setNombre_completo(nombre);
		usuario.setPassword(pass);

		// Llamada a lógica de negocio
		ServletContext application = getServletContext();

		UsuarioDAO usuariosDAO = (UsuarioDAO) application
				.getAttribute(AltaServlet.USUARIOS_DAO);

		if (usuariosDAO == null) {
			usuariosDAO = DAOUsuarioFactory.getUsuarioDAO();
		}

		// Sólo para crear una base de datos falsa con el
		// contenido de un usuario "jon", "antunano"
		// usuarioDAL.alta(new Usuario("jon", "antunano"));

		HttpSession session = request.getSession();
		session.setMaxInactiveInterval(TIEMPO_INACTIVIDAD);

		Cookie cookie = new Cookie("JSESSIONID", session.getId());
		cookie.setMaxAge(TIEMPO_INACTIVIDAD);
		response.addCookie(cookie);

		// for (Cookie cookie : request.getCookies()) {
		// if ("JSESSIONID".equals(cookie.getName())) {
		// cookie.setMaxAge(TIEMPO_INACTIVIDAD);
		// response.addCookie(cookie);
		// }
		// }

		// ESTADOS
		boolean esValido = usuariosDAO.validar(usuario);

		boolean sinParametros = usuario.getNombre_completo() == null;

		boolean esUsuarioYaRegistrado = session.getAttribute("usuario") != null;

		boolean quiereSalir = "logout".equals(opcion);

		boolean nombreValido = usuario.getNombre_completo() != null
				&& usuario.getNombre_completo().length() >= MINIMO_CARACTERES;
		boolean passValido = !(usuario.getPassword() == null || usuario
				.getPassword().length() < MINIMO_CARACTERES);

		// Redirigir a una nueva vista
		if (quiereSalir) {
			session.invalidate();
			request.getRequestDispatcher(RUTA_LOGIN).forward(request, response);
		} else if (esUsuarioYaRegistrado) {
			request.getRequestDispatcher(RUTA_PRINCIPAL).forward(request,
					response);
		} else if (sinParametros) {
			request.getRequestDispatcher(RUTA_LOGIN).forward(request, response);
		} else if (!nombreValido || !passValido) {

			request.setAttribute("usuario", usuario);
			request.getRequestDispatcher(RUTA_LOGIN).forward(request, response);
		} else if (esValido) {
			session.setAttribute("usuario", usuario);
			// response.sendRedirect("principal.jsp");
			request.getRequestDispatcher(RUTA_PRINCIPAL).forward(request,
					response);
		} else {

			request.setAttribute("usuario", usuario);
			request.getRequestDispatcher(RUTA_LOGIN).forward(request, response);
		}
	}
}
