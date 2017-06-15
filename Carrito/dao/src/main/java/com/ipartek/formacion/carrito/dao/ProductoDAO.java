package com.ipartek.formacion.carrito.dao;

import com.ipartek.formacion.carrito.tipos.Producto;

public interface ProductoDAO extends IpartekDAO {

	public Producto[] findAll();

	public Producto findById(String id);

	public int insert(Producto producto);

	public void update(Producto producto);

	public void delete(Producto producto);

	public void delete(String id);

	public void alta(Producto producto);
}
