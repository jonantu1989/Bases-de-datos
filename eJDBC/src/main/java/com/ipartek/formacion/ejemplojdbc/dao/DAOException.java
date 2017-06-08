package com.ipartek.formacion.ejemplojdbc.dao;

public class DAOException extends RuntimeException {

	private static final long serialVersionUID = -5425320393583754106L;

	// Source /generate Constructors from superclass
	public DAOException() {
		super();

	}

	public DAOException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
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
