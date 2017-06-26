package com.ipartek.formacion.carrito.dao;

import org.apache.log4j.Logger;

public class DAOException extends RuntimeException {

	// generate Serial ID sobre DAOException
	private static final long serialVersionUID = -5131550046781534664L;
	private static Logger log = Logger.getLogger(DAOException.class);
	// Source / Generate Constructors from superclass
	public DAOException() {
		super();
		log.error(getLocalizedMessage());
	}	
	
	public DAOException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		log.error(getLocalizedMessage());

	}

	public DAOException(String message, Throwable cause) {
		super(message, cause);
		log.error(getLocalizedMessage());
	}

	public DAOException(String message) {
		super(message);
		log.error(getLocalizedMessage());
	}

	public DAOException(Throwable cause) {
		super(cause);
		log.error(getLocalizedMessage());
	}

}
