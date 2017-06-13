package com.ipartek.formacion.tienda.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.ipartek.formacion.tienda.tipos.Producto;

public class ProductoDAOMySQL extends IpartekDAOMySQL implements ProductoDAO {

	private Map<String, Producto> productos = new HashMap<String, Producto>();

	private final static String FIND_ALL = "SELECT * FROM productos";
	private final static String FIND_BY_ID = "SELECT * FROM productos WHERE id = ?";
	private final static String INSERT = "INSERT INTO productos (id, nombre, descripcion, precio)"
			+ " VALUES (?, ?, ?, ?)";
	private final static String UPDATE = "UPDATE productos "
			+ "SET id = ?, nombre = ?, descripcion = ?, precio = ? "
			+ "WHERE id = ?";
	private final static String DELETE = "DELETE FROM productos WHERE id = ?";

	private PreparedStatement psFindAll, psFindById, psInsert, psUpdate,
			psDelete;

	public ProductoDAOMySQL(String url, String mysqlUser, String mysqlPass) {
		super(url, mysqlUser, mysqlPass);
	}

	public ProductoDAOMySQL() {

	}

	public void altaProducto(Producto producto) {
		if (productos.containsKey(producto.getNombre())) {

			throw new ProductoYaExistenteDAOException("Ya existe el producto "
					+ producto.getNombre());
		} else {
			productos.put(producto.getNombre(), producto);
		}
	}

	public void modificarProducto(Producto producto) {
		if (!productos.containsKey(producto.getNombre())) {

			throw new DAOException(
					String.format(
							"Error el  producto %s no existe por lo que no se puede modificar.",
							producto.getNombre()));
		} else {

			productos.put(producto.getNombre(), producto);
		}
	}

	public void borrarProducto(Producto producto) {
		if (!productos.containsKey(producto.getNombre())) {
			// Si no lo hay, lanzamos una exception.
			throw new DAOException(String.format(
					"Error por que el producto %s no existe.",
					producto.getNombre()));
		} else {
			// Borramos el producto por que existe.
			productos.remove(producto.getNombre());
		}
	}

	public Producto[] findAll() {
		ArrayList<Producto> productos = new ArrayList<Producto>();
		ResultSet rs = null;

		try {
			psFindAll = con.prepareStatement(FIND_ALL);

			rs = psFindAll.executeQuery();

			Producto producto;

			while (rs.next()) {
				// System.out.println(rs.getString("username"));
				producto = new Producto();

				producto.setId(rs.getString("id"));
				producto.setNombre(rs.getString("nombre"));
				producto.setDescripcion(rs.getString("descripcion"));
				producto.setPrecio(rs.getString("password"));

				productos.add(producto);
			}

		} catch (SQLException e) {
			throw new DAOException("Error en findAll", e);
		} finally {
			cerrar(psFindAll, rs);
		}
		return productos.toArray(new Producto[productos.size()]);
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

	public Producto findById(String id) {
		Producto producto = null;
		ResultSet rs = null;

		try {
			psFindById = con.prepareStatement(FIND_BY_ID);

			psFindById.setString(1, id);
			rs = psFindById.executeQuery();

			if (rs.next()) {
				producto = new Producto();

				producto.setId(rs.getString("id"));
				producto.setNombre(rs.getString("nombre"));
				producto.setDescripcion(rs.getString("descripcion"));
				producto.setPrecio(rs.getString("password"));
			}

		} catch (Exception e) {
			throw new DAOException("Error en findById", e);
		} finally {
			cerrar(psFindById, rs);
		}

		return producto;
	}

	public int insert(Producto producto) {
		ResultSet generatedKeys = null;

		try {
			psInsert = con.prepareStatement(INSERT,
					Statement.RETURN_GENERATED_KEYS);

			psInsert.setString(1, producto.getId());
			psInsert.setString(2, producto.getNombre());
			psInsert.setString(3, producto.getDescripcion());
			psInsert.setString(4, producto.getPrecio());

			int res = psInsert.executeUpdate();

			if (res != 1)
				throw new DAOException("La inserción ha devuelto un valor "
						+ res);

			generatedKeys = psInsert.getGeneratedKeys();

			if (generatedKeys.next())
				return generatedKeys.getInt(1);
			else
				throw new DAOException("No se ha recibido la clave generada");

		} catch (Exception e) {
			throw new DAOException("Error en insert", e);
		} finally {
			cerrar(psInsert, generatedKeys);
		}
	}

	public void update(Producto producto) {
		try {
			psUpdate = con.prepareStatement(UPDATE);

			psUpdate.setString(1, producto.getId());
			psUpdate.setString(2, producto.getNombre());
			psUpdate.setString(3, producto.getDescripcion());
			psUpdate.setString(4, producto.getPrecio());

			psUpdate.setString(5, producto.getId());

			int res = psUpdate.executeUpdate();

			if (res != 1)
				throw new DAOException(
						"La actualización ha devuelto un valor " + res);

		} catch (Exception e) {
			throw new DAOException("Error en update", e);
		} finally {
			cerrar(psUpdate);
		}
	}

	public void delete(Producto producto) {
		delete(producto.getId());
	}

	private void delete(String id) {

	}

	public void delete(int id) {
		try {
			psDelete = con.prepareStatement(DELETE);

			psDelete.setInt(1, id);

			int res = psDelete.executeUpdate();

			if (res != 1)
				throw new DAOException(
						"La actualización ha devuelto un valor " + res);

		} catch (Exception e) {
			throw new DAOException("Error en delete", e);
		} finally {
			cerrar(psDelete);
		}
	}

}
