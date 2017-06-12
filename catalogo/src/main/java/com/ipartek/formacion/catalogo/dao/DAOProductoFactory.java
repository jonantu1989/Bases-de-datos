package com.ipartek.formacion.catalogo.dao;

public class DAOProductoFactory {
	public static ProductoDAO getProductosDAL() {
		return new ProductoDAOMySQL();
	}
}
