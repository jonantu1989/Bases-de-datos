package com.ipartek.formacion.carrito.dao;

public class CarritoDAOFactory {

	public static CarritoDAO getCarritoDAO() {
		return new CarritoDAOColeccion();
	}
}
