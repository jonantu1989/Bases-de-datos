package com.ipartek.formacion.tienda.tipos;

public class Usuario {
	// Contructores(normal y vacio), getters y setters, hashCode y equals y
	// toString
	private String nombre;
	private String pass;
	private String errores;

	public Usuario(String nombre, String pass, String errores) {
		super();
		this.nombre = nombre;
		this.pass = pass;
		this.errores = errores;
	}

	public Usuario() {

	}

	public String getNombre() {
		return nombre;
	}

	public String getPass() {
		return pass;
	}

	public String getErrores() {
		return errores;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public void setErrores(String errores) {
		this.errores = errores;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((errores == null) ? 0 : errores.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((pass == null) ? 0 : pass.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (errores == null) {
			if (other.errores != null)
				return false;
		} else if (!errores.equals(other.errores))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (pass == null) {
			if (other.pass != null)
				return false;
		} else if (!pass.equals(other.pass))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Usuario [nombre=" + nombre + ", pass=" + pass + ", errores="
				+ errores + "]";
	}

}