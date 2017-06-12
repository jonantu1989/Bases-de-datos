package com.ipartek.formacion.tienda.dao;

public class DAOUsuarioFactory {
	public static UsuarioDAO getUsuarioDAL() {
		return new UsuarioDAOMySQL();
	}

}
