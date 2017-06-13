package com.ipartek.formacion.tienda.dao;

public class DAOProductoFactory {
	public static ProductoDAO getProductoDAO() {
		return new ProductoDAOMySQL();
	}
}
