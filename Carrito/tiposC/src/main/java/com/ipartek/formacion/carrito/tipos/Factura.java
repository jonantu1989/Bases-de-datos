package com.ipartek.formacion.carrito.tipos;

import java.util.Date;

public class Factura {

	private int id;
	private String número_factura;
	private int id_usuarios;
	private Date fecha;

	public Factura(int id, String número_factura, int id_usuarios, Date fecha) {
		super();
		this.id = id;
		this.número_factura = número_factura;
		this.id_usuarios = id_usuarios;
		this.fecha = fecha;
	}

	public Factura() {

	}

	public int getId() {
		return id;
	}

	public String getNúmero_factura() {
		return número_factura;
	}

	public int getId_usuarios() {
		return id_usuarios;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setNúmero_factura(String número_factura) {
		this.número_factura = número_factura;
	}

	public void setId_usuarios(int id_usuarios) {
		this.id_usuarios = id_usuarios;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	@Override
	public String toString() {
		return "Factura [id=" + id + ", número_factura=" + número_factura
				+ ", id_usuarios=" + id_usuarios + ", fecha=" + fecha + "]";
	}

}
