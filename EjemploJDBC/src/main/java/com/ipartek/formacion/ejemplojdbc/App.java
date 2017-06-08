package com.ipartek.formacion.ejemplojdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ipartek.formacion.ejemplojdbc.dao.DAOException;
import com.ipartek.formacion.ejemplojdbc.dao.UsuarioDAO;
import com.ipartek.formacion.ejemplojdbc.dao.UsuarioDAOMySQL;
import com.ipartek.formacion.ejemplojdbc.tipos.Usuario;

//import java.sql.Statement;

public class App {
	public static void main(String[] args) {
		try {
			UsuarioDAO dao = new UsuarioDAOMySQL();

			// for (Usuario u : dao.findAll())
			// System.out.println(u);
			//
			// int id = 5;
			// Usuario usuario = dao.findById(id);
			// System.out.println("Usuario ID:" + id + " = " + usuario);

			Usuario usuarioInsert = new Usuario(0, 2, "Nuevo nuevez",
					"nuevopass", "nuevo");

			dao.insert(usuarioInsert);

		} catch (DAOException e) {
			e.printStackTrace();
			e.getCause().printStackTrace();

			if (e.getCause() != null)
				e.getCause().printStackTrace();
		}
	}

	public static void mainBasico(String[] args) throws InstantiationException,
			IllegalAccessException, ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		// Se carga el driver
		String url = "jdbc:mysql://localhost/ipartek?user=root&password=";
		// Es a la base de datos que se va a conectar y donde esta esa base de
		// datos
		Connection con = DriverManager.getConnection(url);
		// Lo que representa la conexion a la base de datos
		// Statement st = con.createStatement();
		String sql = "SELECT * FROM usuarios WHERE id=?";
		// La consulta que se va ha hacer
		PreparedStatement pst = con.prepareStatement(sql);
		// Quiere decir que se prepare la base de datos para hacer una o varias
		// veces para que se haga esta consulta
		int id = 4;
		pst.setInt(1, id);
		// Para que se guarde el dato en la posicion 1 del id pero con el valor
		// '4'
		// String sql = "SELECT * FROM usuarios WHERE id=" + id;
		// Es la consulta que se va a realizar
		ResultSet rs = pst.executeQuery(); // st.executeQuery(sql);
		// Guarda el resultado de la consulta

		while (rs.next())
			// Da un booleano este pasa para delante hasta que no haya ningun
			// registro mas
			System.out.println(rs.getString("username"));
		// Se imprime la consulta que se ha hecho
		// CIERRES
		rs.close();
		// st.close();
		pst.close();
		con.close();

		String sqlUpdate = "UPDATE INTO usuarios (username,password, nombre_completo)"
				+ "VALUES (?, ?, ?)";
		PreparedStatement pstUpdate = con.prepareStatement(sqlUpdate);
		String username = "jdbcmodificado", password = "jdbcnuevopass", nombre_completo = "JDBC";

		pstUpdate.setString(1, username);
		pstUpdate.setString(1, password);
		pstUpdate.setString(1, nombre_completo);

		int res = pstUpdate.executeUpdate();
		System.out.println("Se ha modificado " + res + "registros");

		pst.close();
		con.close();
		// Los cierres se hacen del ultimo que se ha abierto hasta el primero
	}
}
