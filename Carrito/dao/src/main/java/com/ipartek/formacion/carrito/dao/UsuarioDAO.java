package com.ipartek.formacion.carrito.dao;

import com.ipartek.formacion.carrito.tipos.Usuario;

public interface UsuarioDAO extends IpartekDAO {
	
	public Usuario[] findAll();

	public Usuario findById(int id);

	public int insert(Usuario usuario);

	public void update(Usuario usuario);

	public void delete(Usuario usuario);

	public void delete(int id);
}
