package com.ipartek.formacion.catalogo.dao;

import com.ipartek.formacion.catalogo.tipos.Usuario;

public interface UsuarioDAO extends IpartekDAO {

	public void alta(Usuario usuario);

	public void modificar(Usuario usuario);

	public void borrar(Usuario usuario);

	public Usuario[] findAll();

	public Usuario findById(int id);

	public int insert(Usuario usuario);

	public void update(Usuario usuario);

	public void delete(Usuario usuario);

	public void delete(int id);
}
