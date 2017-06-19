package com.ipartek.formacion.carrito.dao;

import com.ipartek.formacion.carrito.tipos.Usuario;

public interface UsuarioDAO extends IpartekDAO {

	public void alta(Usuario usuario);

	public Usuario[] findAll();

	public Usuario findById(String id);

	public void insert(Usuario usuario);

	public void update(Usuario usuario);

	public void delete(Usuario usuario);

	public void delete(String id);

	public boolean validar(Usuario usuario);

}
