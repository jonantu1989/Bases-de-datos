package com.ipartek.formacion.carrito.listeners;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.log4j.Logger;

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

	}

}
