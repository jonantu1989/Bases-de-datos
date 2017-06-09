package com.ipartek.formacion.catalogo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ipartek.formacion.catalogo.dao.DAOException;
import com.ipartek.formacion.catalogo.dao.ProductoDAO;
import com.ipartek.formacion.catalogo.dao.ProductoDAOMySQL;
import com.ipartek.formacion.catalogo.dao.UsuarioDAO;
import com.ipartek.formacion.catalogo.dao.UsuarioDAOMySQL;
import com.ipartek.formacion.catalogo.tipos.Producto;
import com.ipartek.formacion.catalogo.tipos.Usuario;

public class App {
	public static UsuarioDAO daousuario = null;
	public static ProductoDAO daoproducto = null;

	public static void mainTransacciones(String[] args) {
		try {
			daousuario = new UsuarioDAOMySQL(); // "jdbc:mysql://localhost/ipartek",
			// "javierlete", "javipass");

			daousuario.abrir();

			daousuario.iniciarTransaccion();

			Usuario usuario;

			for (int i = 100; i < 200; i++) {
				usuario = new Usuario();
				usuario.setUsername("usuario" + i);
				usuario.setPassword("usuario" + i + "pass");
				usuario.setNombre_completo("Usuario" + i + " Usuariez");
				usuario.setId_roles(2);

				// if(i > 150)
				// throw new RuntimeException("CASQUE ACCIDENTAL");
				// else
				daousuario.insert(usuario);
			}

			daousuario.confirmarTransaccion();

		} catch (Exception e) {
			daousuario.deshacerTransaccion();
			System.out.println("HA CASCADO");
			e.printStackTrace();
		} finally {
			daousuario.cerrar();
		}

		try {
			daoproducto = new ProductoDAOMySQL(); // "jdbc:mysql://localhost/ipartek",
			// "javierlete", "javipass");

			daoproducto.abrir();

			daoproducto.iniciarTransaccion();

			Producto producto;

			for (int i = 100; i < 200; i++) {
				producto = new Producto();
				producto.setId("producto" + i);
				producto.setNombre("producto" + i + "nombre");
				producto.setDescripcion("Producto" + i + " Productos");
				producto.getPrecio();

				// if(i > 150)
				// throw new RuntimeException("CASQUE ACCIDENTAL");
				// else
				daoproducto.insert(producto);
			}

			daoproducto.confirmarTransaccion();

		} catch (Exception e) {
			daoproducto.deshacerTransaccion();
			System.out.println("HA CASCADO");
			e.printStackTrace();
		} finally {
			daoproducto.cerrar();
		}
	}

	public static void main(String[] args) {
		try {
			daousuario = new UsuarioDAOMySQL(
					"jdbc:mysql://localhost/catalogoapp?user=root&password=");

			daousuario.abrir();

			listado();

			Usuario usuario = new Usuario(0, 2, "Nuevo nuevez", "nuevopass",
					"nuevo100");

			int id = daousuario.insert(usuario);
			System.out.println("Se ha insertado un nuevo registro con el id "
					+ id);

			usuario = daousuario.findById(id);
			System.out.println("Usuario ID:" + id + "=" + usuario);

			listado();

			usuario.setNombre_completo("MODIFICADO");
			daousuario.update(usuario);
			System.out.println("Se ha modificado el registro " + id);

			listado();

			daousuario.delete(usuario);
			System.out.println("Se ha borrado el registro " + id);

			listado();

		} catch (DAOException e) {
			e.printStackTrace();
		} finally {
			daousuario.cerrar();
		}
	}

	private static void listado() {
		System.out.println("\nLISTADO\n=======");

		for (Usuario u : daousuario.findAll())
			System.out.println(u);

		System.out.println();
	}

	public static void mainBasico(String[] args) throws InstantiationException,
			IllegalAccessException, ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver").newInstance();

		String url = "jdbc:mysql://localhost/catalogoapp?user=root&password=";

		Connection con = DriverManager.getConnection(url);

		// Statement st = con.createStatement();

		String sql = "SELECT * FROM usuarios WHERE id=?";

		PreparedStatement pst = con.prepareStatement(sql);

		int id = 4;

		pst.setInt(1, id);

		// String sql = "SELECT * FROM usuarios WHERE id=" + id;

		ResultSet rs = pst.executeQuery(); // st.executeQuery(sql);

		while (rs.next())
			System.out.println(rs.getString("username") + ", "
					+ rs.getString("nombre_completo"));

		rs.close();
		// st.close();
		pst.close();

		String sqlInsert = "INSERT INTO usuarios (username, password, nombre_completo) "
				+ "VALUES (?, ?, ?)";

		PreparedStatement pstInsert = con.prepareStatement(sqlInsert);

		//
		String username = "jdbcnuevo", password = "jdbcnuevopass", nombre_completo = "JDBC";

		pstInsert.setString(1, username);
		pstInsert.setString(2, password);
		pstInsert.setString(3, nombre_completo);

		int res = pstInsert.executeUpdate();

		System.out.println("Se ha modificado " + res + " registros");
		//

		String sqlUpdate = "UPDATE usuarios SET username=?, password=?, nombre_completo=? "
				+ "WHERE id=?";

		PreparedStatement pstUpdate = con.prepareStatement(sqlUpdate);

		//
		username = "jdbcmodificado";
		password = "jdbcnuevopass";
		nombre_completo = "JDBC";

		pstUpdate.setString(1, username);
		pstUpdate.setString(2, password);
		pstUpdate.setString(3, nombre_completo);
		pstUpdate.setInt(4, id);

		res = pstUpdate.executeUpdate();

		System.out.println("Se ha modificado " + res + " registros");

		con.close();

		// https://dev.mysql.com/doc/connector-j/5.1/en/connector-j-usagenotes-statements.html
	}
}
