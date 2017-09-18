package com.ipartek.formacion.carrito.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ipartek.formacion.carrito.tipos.Producto;

public class ProductoDAOMySQL extends IpartekDAOMySQL implements ProductoDAO {

	private final static String FIND_ALL = "SELECT * FROM productos";
	private final static String FIND_BY_ID = "SELECT * FROM productos WHERE id = ?";
	private final static String INSERT = "INSERT INTO productos (nombre, precio)"
			+ " VALUES (?, ?)";
	private final static String UPDATE = "UPDATE productos "
			+ "SET nombre = ?, precio = ?" + "WHERE id = ?";
	private final static String DELETE = "DELETE FROM productos WHERE id = ?";

	private PreparedStatement psFindAll, psFindById, psInsert, psUpdate,
			psDelete;

	public ProductoDAOMySQL(String url, String mysqlUser, String mysqlPass) {
		super(url, mysqlUser, mysqlPass);
	}

	public ProductoDAOMySQL() {
		super();
	}

	public Producto[] findAll() {
		ArrayList<Producto> productos = new ArrayList<Producto>();
		ResultSet rs = null;

		try {
			if (psFindAll == null)

				psFindAll = con.prepareStatement(FIND_ALL); // NullPointerException

			rs = psFindAll.executeQuery(); // java.sql.SQLException: No
											// operations allowed after
											// statement closed.

			Producto producto;

			while (rs.next()) {

				producto = new Producto();

				producto.setId(rs.getInt("id"));
				producto.setNombre(rs.getString("nombre"));
				producto.setPrecio(rs.getDouble("precio"));

				productos.add(producto);
			}
			// com.ipartek.formacion.carrito.dao.DAOException: Error en findAll
		} catch (SQLException e) {
			throw new DAOException("Error en findAll", e);
		} finally {
			cerrar(psFindAll, rs);
		}
		return productos.toArray(new Producto[productos.size()]);
	}

	public Producto findById(int id) {
		Producto producto = null;
		ResultSet rs = null;

		try {
			psFindById = con.prepareStatement(FIND_BY_ID);

			psFindById.setInt(1, id);
			rs = psFindById.executeQuery();

			if (rs.next()) {
				producto = new Producto();

				producto.setId(rs.getInt("id"));
				producto.setNombre(rs.getString("nombre"));
				producto.setPrecio(rs.getDouble("precio"));
			}

		} catch (SQLException e) {
			throw new DAOException("Error en FindById", e);
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

			psInsert.setString(1, producto.getNombre());
			psInsert.setDouble(2, producto.getPrecio());

			int res = psInsert.executeUpdate();

			if (res != 1) {
				throw new DAOException("La inserción ha devuelto un valor "
						+ res);
			}
			generatedKeys = psInsert.getGeneratedKeys();

			if (generatedKeys.next()) {
				return generatedKeys.getInt(1);
			} else {
				throw new DAOException("No se ha recibido la clave generada");
			}

		} catch (SQLException e) {
			throw new DAOException("Error en el insert", e);
		} finally {
			cerrar(psInsert, generatedKeys);
		}

	}

	public void update(Producto producto) {

		try {
			psUpdate = con.prepareStatement(UPDATE);

			psUpdate.setString(1, producto.getNombre());
			psUpdate.setDouble(2, producto.getPrecio());
			psUpdate.setInt(3, producto.getId());

			int res = psUpdate.executeUpdate();

			if (res != 1) {
				throw new DAOException("La actualización ha devuelto un valor "
						+ res);
			}
		} catch (Exception e) {
			throw new DAOException("Error en el update", e);
		} finally {
			cerrar(psUpdate);
		}

	}

	public void delete(Producto producto) {
		delete(producto.getId());
	}

	public void delete(int id) {
		try {
			psDelete = con.prepareStatement(DELETE);

			psDelete.setInt(1, id);

			int res = psDelete.executeUpdate();

			if (res != 1) {
				throw new DAOException(
						"La eliminación por ID ha devuelto un valor " + res);
			}
		} catch (Exception e) {
			throw new DAOException("Error en el deleteID", e);
		} finally {
			cerrar(psDelete);
		}

	}

	public Producto[] getCatalogo() {

		Producto[] catalogo = new Producto[this.getAlmacen().size()]; // java.sql.SQLException:
																		// No
																		// operations
																		// allowed
																		// after
																		// statement
																		// closed.
																		// //com.ipartek.formacion.carrito.dao.DAOException:
																		// Error
																		// en
																		// findAll
		int i = 0;

		for (List<Producto> grupoProductos : this.getAlmacen().values()) {

			Producto muestra = grupoProductos.get(0);
			catalogo[i] = muestra;
			i++;

		}

		return catalogo;

	}

	public Map<Integer, List<Producto>> getAlmacen() {

		Map<Integer, List<Producto>> almacen = new HashMap<>();

		Producto[] productosArr = this.findAll(); // java.sql.SQLException: No
													// com.ipartek.formacion.carrito.dao.DAOException:
													// Error en findAll
													// operations allowed after
													// statement closed.

		for (Producto p : productosArr) {
			if (!almacen.containsKey(p.getId())) {
				List<Producto> grupo = new ArrayList<>();
				grupo.add(p);
				almacen.put(p.getId(), grupo);
			} else {
				List<Producto> grupo = almacen.get(p.getId());
				grupo.add(p);
				almacen.put(p.getId(), grupo);
			}
		}

		return almacen;
	}

	public boolean validar(Producto producto) {

		this.abrir();
		Producto[] ProductosArr = this.findAll();
		this.cerrar();

		for (Producto p : ProductosArr) {
			if (p.getId() == producto.getId()) {
				return true;
			}
		}
		return false;
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

}