package com.ipartek.formacion.carrito.dao;

public class DAOUsuarioFactory {
	
	public static UsuarioDAO getUsuarioDAO() {
		return new UsuarioDAOMySQL();
	}

}
