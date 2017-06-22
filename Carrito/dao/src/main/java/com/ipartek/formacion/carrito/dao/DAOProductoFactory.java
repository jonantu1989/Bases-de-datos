package com.ipartek.formacion.carrito.dao;

public class DAOProductoFactory {

	public static ProductoDAO getProductoDAO() {
		ProductoDAO productos = new ProductoDAOMySQL();

		return productos;
	}

}
