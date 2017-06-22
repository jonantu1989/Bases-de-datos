package com.ipartek.formacion.carrito.dao;

public class DAOUsuarioFactory {

	public static UsuarioDAO getUsuarioDAO() {

		UsuarioDAO usuarios = new UsuarioDAOMySQL();

		return usuarios;
	}

}
