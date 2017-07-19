package com.ipartek.formacion.carrito.listeners;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.ipartek.formacion.carrito.dao.DAOProductoFactory;
import com.ipartek.formacion.carrito.dao.DAOUsuarioFactory;
import com.ipartek.formacion.carrito.dao.ProductoDAO;
import com.ipartek.formacion.carrito.dao.UsuarioDAO;
import com.ipartek.formacion.carrito.tipos.Producto;
import com.ipartek.formacion.carrito.tipos.Usuario;

@WebListener
public class Inicializacion implements ServletContextListener {

	private static Logger log = Logger.getLogger(Inicializacion.class);

	public Inicializacion() {

	}

	public void contextDestroyed(ServletContextEvent agr0) {

	}

	public void contextInitialized(ServletContextEvent servletContextEvent) {

		ServletContext application = servletContextEvent.getServletContext();

		String path = application.getContextPath();
		application.setAttribute("Ruta", path);
		log.info("Almacenada la ruta relativa de la aplicación: " + path);

		String realPath = application.getRealPath("/WEB-INF/db/driver.db");
		log.info("Almacenada la ruta real de la base de datos: " + realPath);

		// Configuro Log4j

		PropertyConfigurator.configure(Inicializacion.class.getClassLoader()
				.getResource("log4j.properties"));

		// Inicializo el DAO de usuarios y hacerlo accesible a través del
		// ServletContext

		UsuarioDAO usuarios = DAOUsuarioFactory.getUsuarioDAO();

		application.setAttribute("usuarios", usuarios);

		// Creo un array con todos los usuarios y dejarlo disponible en el
		// ServletContext

		Usuario[] usuariosArray = null;

		usuarios.abrir();

		try {
			usuariosArray = usuarios.findAll();
		} catch (Exception e) {
			log.info(e.getMessage());
			log.info("No se pudo crear la lista de usuarios");
		}

		usuarios.cerrar();

		application.setAttribute("usuariosArr", usuariosArray);

		// Inicializo el DAO de productos y hacerlo accesible a través del
		// ServletContext

		ProductoDAO productos = DAOProductoFactory.getProductoDAO();

		application.setAttribute("productos", productos);

		// Creo un array con todos los productos y dejarlo disponible en el
		// ServletContext
		// Es necesario en producción para extraer el primer array de la base de
		// datos
		// el catálogo ya lo crea

		Producto[] productosArray = null;

		productos.abrir();

		try {
			productosArray = productos.findAll();
		} catch (Exception e) {
			log.info(e.getMessage());
			log.info("No se pudo crear la lista de productos");
		}

		usuarios.cerrar();

		application.setAttribute("productosArr", productosArray);

	}

}
