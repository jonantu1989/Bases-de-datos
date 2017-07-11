package com.ipartek.formacion.carrito.dao;

public class CatalogoDAOFactory {

	public static CatalogoDAO getCarritoDAO() {

		return new CatalogoDAOColeccion();

	}

}
