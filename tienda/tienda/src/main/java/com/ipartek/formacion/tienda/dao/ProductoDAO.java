package com.ipartek.formacion.tienda.dao;

import com.ipartek.formacion.tienda.tipos.Producto;

public interface ProductoDAO extends IpartekDAO {

	public void altaProducto(Producto producto);

	public void modificarProducto(Producto producto);

	public void borrarProducto(Producto producto);

	public Producto[] findAll();

	public Producto findById(int id);

	public int insert(Producto producto);

	public void update(Producto producto);

	public void delete(Producto producto);

	public void delete(int id);
}
