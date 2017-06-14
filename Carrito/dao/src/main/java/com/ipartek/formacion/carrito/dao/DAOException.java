package com.ipartek.formacion.carrito.dao;

public class DAOException extends RuntimeException {

	// generate Serial ID sobre DAOException
	private static final long serialVersionUID = -5131550046781534664L;

	// Source / Generate Constructors from superclass
	public DAOException() {
		super();
		
	}	
	
	public DAOException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);

	}

	public DAOException(String message, Throwable cause) {
		super(message, cause);

	}

	public DAOException(String message) {
		super(message);

	}

	public DAOException(Throwable cause) {
		super(cause);

	}

}
