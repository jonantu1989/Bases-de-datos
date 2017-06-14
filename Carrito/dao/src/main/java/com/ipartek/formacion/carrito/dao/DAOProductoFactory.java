package com.ipartek.formacion.carrito.dao;

public class DAOProductoFactory {
	
	public static ProductoDAO getProductoDAO() {
		return new ProductoDAOMySQL();
	}

}
