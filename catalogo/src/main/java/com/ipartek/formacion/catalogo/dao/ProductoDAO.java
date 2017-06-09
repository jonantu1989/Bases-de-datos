package com.ipartek.formacion.catalogo.dao;

import com.ipartek.formacion.catalogo.tipos.Producto;

public interface ProductoDAO {
	public Producto[] findAll();

	public Producto findById(int id);

	public int insert(Producto producto);

	public void update(Producto producto);

	public void delete(Producto producto);

	public void delete(int id);
}
