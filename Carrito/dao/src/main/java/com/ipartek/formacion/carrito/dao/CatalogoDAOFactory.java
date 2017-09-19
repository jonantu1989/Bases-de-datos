package com.ipartek.formacion.carrito.dao;

public class CatalogoDAOFactory {

	public static CatalogoDAO getCatalogoDAO() {

		return new CatalogoDAOColeccion();

	}

}
