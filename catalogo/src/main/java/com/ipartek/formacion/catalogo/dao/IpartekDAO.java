package com.ipartek.formacion.catalogo.dao;

public interface IpartekDAO {
	public void abrir();

	public void cerrar();

	public void iniciarTransaccion();

	public void confirmarTransaccion();

	public void deshacerTransaccion();
}
