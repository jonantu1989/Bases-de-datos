package com.ipartek.formacion.catalogo.dao;

public class DAOUsuarioFactory {
	public static UsuarioDAO getUsuarioDAL() {
		return new UsuarioDAOMySQL();
	}

}
