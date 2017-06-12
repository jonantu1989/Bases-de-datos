package com.ipartek.formacion.tienda.dao;

public class UsuarioYaExistenteDAOException extends DAOException {
	private static final long serialVersionUID = 5371466154809794327L;

	public UsuarioYaExistenteDAOException() {
		super();
	}

	public UsuarioYaExistenteDAOException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public UsuarioYaExistenteDAOException(String message, Throwable cause) {
		super(message, cause);
	}

	public UsuarioYaExistenteDAOException(String message) {
		super(message);
	}

	public UsuarioYaExistenteDAOException(Throwable cause) {
		super(cause);
	}

}
