package com.ipartek.formacion.tienda.dao;

public class DAOUsuarioFactory {
	public static UsuarioDAO getUsuarioDAO() {
		return new UsuarioDAOMySQL();
	}

}
