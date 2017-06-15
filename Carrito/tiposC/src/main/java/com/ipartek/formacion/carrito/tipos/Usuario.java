package com.ipartek.formacion.carrito.tipos;

public class Usuario {

	private String id;
	private String id_roles;
	private String nombre;
	private String pass;
	private String username;
	private String errores;

	public Usuario(String nombre, String pass) {
		super();
		this.nombre = nombre;
		this.pass = pass;
	}

	public Usuario() {
		super();
	}

	public String getId() {
		return id;
	}

	public String getId_roles() {
		return id_roles;
	}

	public String getNombre() {
		return nombre;
	}

	public String getPass() {
		return pass;
	}

	public String getUsername() {
		return username;
	}

	public String getErrores() {
		return errores;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setId_roles(String id_roles) {
		this.id_roles = id_roles;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setErrores(String errores) {
		this.errores = errores;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		return "Usuario [nombre=" + nombre + ", pass=" + pass + "]";
	}

}