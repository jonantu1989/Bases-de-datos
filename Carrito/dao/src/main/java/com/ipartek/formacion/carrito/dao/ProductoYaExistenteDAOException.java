package com.ipartek.formacion.carrito.dao;

public class ProductoYaExistenteDAOException extends DAOException {
	
	private static final long serialVersionUID = 5371466154809794327L;

	public ProductoYaExistenteDAOException() {
		super();
	}

	public ProductoYaExistenteDAOException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ProductoYaExistenteDAOException(String message, Throwable cause) {
		super(message, cause);
	}

	public ProductoYaExistenteDAOException(String message) {
		super(message);
	}

	public ProductoYaExistenteDAOException(Throwable cause) {
		super(cause);
	}
}
