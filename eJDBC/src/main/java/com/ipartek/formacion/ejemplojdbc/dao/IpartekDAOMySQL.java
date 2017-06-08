package com.ipartek.formacion.ejemplojdbc.dao;

import java.sql.Connection;

public class IpartekDAOMySQL implements IpartekDAO {

	protected Connection con;

	private String url = "jdbc:mysql://localhost/ipartek";
	private String mysqlUser = "root";
	private String mysqlPass = "";

	public void abrir() {
		// TODO Auto-generated method stub

	}

	public void cerrar() {
		// TODO Auto-generated method stub

	}

}
