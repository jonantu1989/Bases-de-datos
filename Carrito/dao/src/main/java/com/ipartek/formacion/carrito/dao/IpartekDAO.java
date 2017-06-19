package com.ipartek.formacion.carrito.dao;

public interface IpartekDAO {
	public void abrir();

	public void cerrar();

	public void iniciarTransaccion();

	public void confirmarTransaccion();

	public void deshacerTransaccion();
}
