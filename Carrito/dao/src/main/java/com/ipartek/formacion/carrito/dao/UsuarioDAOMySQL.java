package com.ipartek.formacion.carrito.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.ipartek.formacion.carrito.tipos.Usuario;

public class UsuarioDAOMySQL extends IpartekDAOMySQL implements UsuarioDAO {

	private final static String FIND_ALL = "SELECT * FROM usuarios";
	private final static String FIND_BY_ID = "SELECT * FROM usuarios WHERE id = ?";
	private final static String INSERT = "INSERT INTO usuarios (id, username, password, nombre_completo, id_roles)"
			+ " VALUES (?, ?, ?, ?, ?)";
	private final static String UPDATE = "UPDATE usuarios "
			+ "SET id = ?, username = ?, password = ?, nombre_completo = ?, id_roles = ? "
			+ "WHERE id = ?";
	private final static String DELETE = "DELETE FROM usuarios WHERE id = ?";

	private PreparedStatement psFindAll, psFindById, psInsert, psUpdate,
			psDelete;

	public UsuarioDAOMySQL(String url, String mysqlUser, String mysqlPass) {
		super(url, mysqlUser, mysqlPass);
	}

	public UsuarioDAOMySQL() {

	}

	public Usuario[] findAll() {

		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();

		ResultSet rs = null;

		try {
			psFindAll = con.prepareStatement(FIND_ALL);

			rs = psFindAll.executeQuery();

			Usuario usuario;

			while (rs.next()) {
				// System.out.println(rs.getString("username"));
				usuario = new Usuario();

				usuario.setId(rs.getString("id"));
				usuario.setId_roles(rs.getString("id_roles"));
				usuario.setNombre(rs.getString("nombre_completo"));
				usuario.setPass(rs.getString("password"));
				usuario.setUsername(rs.getString("username"));

				usuarios.add(usuario);
			}

		} catch (SQLException e) {
			throw new DAOException("Error en findAll", e);
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			cerrar(psFindAll, rs);
		}
		return usuarios.toArray(new Usuario[usuarios.size()]);

	}

	public Usuario findById(String id) {

		Usuario usuario = null;
		ResultSet rs = null;

		try {
			psFindById = con.prepareStatement(FIND_BY_ID);

			psFindById.setString(1, id);
			rs = psFindById.executeQuery();

			if (rs.next()) {
				usuario = new Usuario();

				usuario.setId(rs.getString("id"));
				usuario.setId_roles(rs.getString("id_roles"));
				usuario.setNombre(rs.getString("nombre_completo"));
				usuario.setPass(rs.getString("password"));
				usuario.setUsername(rs.getString("username"));
			}

		} catch (SQLException e) {
			throw new DAOException("Error en FindById", e);
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			cerrar(psFindById, rs);
		}

		return usuario;

	}

	public void insert(Usuario usuario) {

		ResultSet generatedKeys = null;

		try {
			psInsert = con.prepareStatement(INSERT,
					Statement.RETURN_GENERATED_KEYS);

			psInsert.setString(1, usuario.getUsername());
			psInsert.setString(2, usuario.getPass());
			psInsert.setString(3, usuario.getNombre());
			psInsert.setString(4, usuario.getId_roles());
			psInsert.setString(5, usuario.getId());

			int res = psInsert.executeUpdate();

			if (res != 1)
				throw new DAOException("La inserción ha devuelto un valor "
						+ res);

			generatedKeys = psInsert.getGeneratedKeys();

			if (generatedKeys.next())
				return;
			else
				throw new DAOException("No se ha recibido la clave generada");

		} catch (Exception e) {
			throw new DAOException("Error en insert", e);
		} finally {
			cerrar(psInsert, generatedKeys);
		}

	}

	public void update(Usuario usuario) {

		try {
			psUpdate = con.prepareStatement(UPDATE);

			psUpdate.setString(1, usuario.getUsername());
			psUpdate.setString(2, usuario.getPass());
			psUpdate.setString(3, usuario.getNombre());
			psUpdate.setString(4, usuario.getId_roles());

			psUpdate.setString(5, usuario.getId());

			int res = psUpdate.executeUpdate();

			if (res != 1)
				throw new DAOException("La actualización ha devuelto un valor "
						+ res);

		} catch (Exception e) {
			throw new DAOException("Error en update", e);
		} finally {
			cerrar(psUpdate);
		}

	}

	public void delete(Usuario usuario) {

		delete(usuario.getId());

	}

	public void delete(String id) {

		try {
			psDelete = con.prepareStatement(DELETE);

			psDelete.setString(1, id);

			int res = psDelete.executeUpdate();

			if (res != 1)
				throw new DAOException("La actualización ha devuelto un valor "
						+ res);

		} catch (Exception e) {
			throw new DAOException("Error en update", e);
		} finally {
			cerrar(psDelete);
		}

	}

	private void cerrar(PreparedStatement ps) {
		cerrar(ps, null);
	}

	private void cerrar(PreparedStatement ps, ResultSet rs) {
		try {
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
		} catch (Exception e) {
			throw new DAOException("Error en el cierre de ps o rs", e);
		}
	}

	public void alta(Usuario usuario) {

	}

	public boolean validar(Usuario usuario) {

		return false;
	}

}
