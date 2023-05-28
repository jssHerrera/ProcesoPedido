package pe.edu.galaxy.training.java.persistencia.impl.procesos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import pe.edu.galaxy.training.java.bean.procesos.PedidoCabecera;
import pe.edu.galaxy.training.java.bean.procesos.PedidoDetalle;
import pe.edu.galaxy.training.java.persistencia.PersistenciaException;
import pe.edu.galaxy.training.java.persistencia.inf.procesos.PedidoDAO;
import pe.edu.galaxy.training.java.persistencia.util.BD;

public class PedidoDAOImpl implements PedidoDAO {

	public PedidoDAOImpl() {

	}

	@Override
	public List<PedidoCabecera> listar(PedidoCabecera t) throws PersistenciaException {

		return null;
	}

	@Override
	public PedidoCabecera buscarXId(Long id) throws PersistenciaException {

		return null;
	}

	@Override
	public boolean insertar(PedidoCabecera pedidoCabecera) throws PersistenciaException {

		/*
		 * // Registrar cabecera if (this.insertarCabecera(pedidoCabecera)) { //
		 * Registrar detalle for (PedidoDetalle pedidoDetalle :
		 * pedidoCabecera.getListaPedidoDetalle()) {
		 * pedidoDetalle.setPedidoCabecera(pedidoCabecera);
		 * 
		 * pedidoDetalle.setAudIdUsuario(pedidoCabecera.getAudIdUsuario());
		 * pedidoDetalle.setAudSesion(pedidoCabecera.getAudSesion());
		 * pedidoDetalle.setAudIP(pedidoCabecera.getAudIP());
		 * 
		 * if (!this.insertarDetalle(pedidoDetalle)) {
		 * 
		 * return false; } }
		 * 
		 * return true; } return false;
		 */

		Connection cn = BD.getConnection();

		try {
			cn.setAutoCommit(false); // Registrar cabecera
			if (this.insertarCabecera(pedidoCabecera, cn)) {
				// Registrar detalle
				for (PedidoDetalle pedidoDetalle : pedidoCabecera.getListaPedidoDetalle()) {
					pedidoDetalle.setPedidoCabecera(pedidoCabecera);

					pedidoDetalle.setAudIdUsuario(pedidoCabecera.getAudIdUsuario());
					pedidoDetalle.setAudSesion(pedidoCabecera.getAudSesion());
					pedidoDetalle.setAudIP(pedidoCabecera.getAudIP());

					if (!this.insertarDetalle(pedidoDetalle, cn)) {
						cn.rollback();
						return false;
					}
				}
				cn.commit();
				return true;
			}
			return false;
		} catch (Exception e) {
			try {
				cn.rollback();
			} catch (SQLException e1) {
				throw new PersistenciaException(e1);
			}
			throw new PersistenciaException(e);
		}

	}

	@Override
	public boolean actualizar(PedidoCabecera t) throws PersistenciaException {

		return false;
	}

	@Override
	public boolean eliminar(PedidoCabecera t) throws PersistenciaException {

		return false;
	}

	@Override
	public boolean insertarCabecera(PedidoCabecera pedidoCabecera, Connection cn) throws PersistenciaException {

		try {

			// Connection cn = BD.getConnection();

			String sql = "{call PKG_PEDIDO.SP_INSERTAR_CABECERA(?,?,?,?,?,?,?,?, ?,?,?)}";

			CallableStatement cs = cn.prepareCall(sql);

			cs.registerOutParameter(1, oracle.jdbc.OracleTypes.INTEGER); // P_PEDIDO_CABECERA_ID
			cs.setLong(2, pedidoCabecera.getCliente().getId());// P_CLIENTE_ID
			cs.setLong(3, pedidoCabecera.getEmpleado().getId()); // P_EMPLEADO_ID
			cs.setString(4, pedidoCabecera.getGlosa());// P_GLOSA
			cs.setDouble(5, pedidoCabecera.getSubTotal());// P_SUB_TOTAL
			cs.setDouble(6, pedidoCabecera.getDescuento());// P_DESCUENTO
			cs.setDouble(7, pedidoCabecera.getIgv());// P_IGV
			cs.setDouble(8, pedidoCabecera.getTotal());// P_TOTAL

			// Auditoria

			cs.setLong(9, pedidoCabecera.getAudIdUsuario());// P_AUD_IDUSUARIO
			cs.setString(10, pedidoCabecera.getAudSesion());// P_AUD_SESION
			cs.setString(11, pedidoCabecera.getAudIP());// P_AUD_IP
			cs.execute();

			Long id = cs.getLong(1);

			cs.close();
			// cn.close();
			pedidoCabecera.setId(id);
			return (id > 0);

		} catch (Exception e) {
			System.err.println("Error al registrar Pedido Cabecera" + e.getMessage());
			throw new PersistenciaException(e);
		}

	}

	@Override
	public boolean insertarDetalle(PedidoDetalle pedidoDetalle, Connection cn) throws PersistenciaException {

		try {

			// System.out.println(pedidoDetalle);
			// Connection cn = BD.getConnection();

			String sql = "{call PKG_PEDIDO.SP_INSERTAR_DETALLE(?,?,?,?,?,?, ?,?,?)}";

			CallableStatement cs = cn.prepareCall(sql);

			cs.registerOutParameter(1, oracle.jdbc.OracleTypes.INTEGER); // P_PEDIDO_DETALLE_ID
			cs.setLong(2, pedidoDetalle.getPedidoCabecera().getId());// P_PEDIDO_CABECERA_ID
			cs.setLong(3, pedidoDetalle.getProducto().getId()); // P_PRODUCTO_ID
			cs.setLong(4, pedidoDetalle.getCantidad());// P_CANTIDAD
			cs.setDouble(5, pedidoDetalle.getPrecio());// P_PRECIO
			cs.setDouble(6, pedidoDetalle.getSubTotal());// P_SUB_TOTAL

			// Auditoria

			cs.setLong(7, pedidoDetalle.getAudIdUsuario());// P_AUD_IDUSUARIO
			cs.setString(8, pedidoDetalle.getAudSesion());// P_AUD_SESION
			cs.setString(9, pedidoDetalle.getAudIP());// P_AUD_IP
			cs.execute();

			Integer id = cs.getInt(1);

			cs.close();
			// cn.close();

			return (id > 0);

		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Error al registrar Pedido Detalle" + e.getMessage());
			throw new PersistenciaException(e);
		}

	}

	/*
	 * public boolean insertarCabecera(PedidoCabecera pedidoCabecera) throws
	 * PersistenciaException {
	 * 
	 * try {
	 * 
	 * Connection cn = BD.getConnection();
	 * 
	 * String sql =
	 * "{call PKG_PEDIDO.SP_INSERTAR_CABECERA(?,?,?,?,?,?,?,?, ?,?,?)}";
	 * 
	 * CallableStatement cs = cn.prepareCall(sql);
	 * 
	 * cs.registerOutParameter(1, oracle.jdbc.OracleTypes.INTEGER); //
	 * P_PEDIDO_CABECERA_ID cs.setLong(2, pedidoCabecera.getCliente().getId());//
	 * P_CLIENTE_ID cs.setLong(3, pedidoCabecera.getEmpleado().getId()); //
	 * P_EMPLEADO_ID cs.setString(4, pedidoCabecera.getGlosa());// P_GLOSA
	 * cs.setDouble(5, pedidoCabecera.getSubTotal());// P_SUB_TOTAL cs.setDouble(6,
	 * pedidoCabecera.getDescuento());// P_DESCUENTO cs.setDouble(7,
	 * pedidoCabecera.getIgv());// P_IGV cs.setDouble(8,
	 * pedidoCabecera.getTotal());// P_TOTAL
	 * 
	 * // Auditoria
	 * 
	 * cs.setLong(9, pedidoCabecera.getAudIdUsuario());// P_AUD_IDUSUARIO
	 * cs.setString(10, pedidoCabecera.getAudSesion());// P_AUD_SESION
	 * cs.setString(11, pedidoCabecera.getAudIP());// P_AUD_IP cs.execute();
	 * 
	 * Long id = cs.getLong(1);
	 * 
	 * cs.close(); cn.close(); pedidoCabecera.setId(id); return (id > 0);
	 * 
	 * } catch (Exception e) {
	 * System.err.println("Error al registrar Pedido Cabecera" + e.getMessage());
	 * throw new PersistenciaException(e); }
	 * 
	 * }
	 * 
	 * public boolean insertarDetalle(PedidoDetalle pedidoDetalle) throws
	 * PersistenciaException {
	 * 
	 * try {
	 * 
	 * // System.out.println(pedidoDetalle); Connection cn = BD.getConnection();
	 * 
	 * String sql = "{call PKG_PEDIDO.SP_INSERTAR_DETALLE(?,?,?,?,?,?, ?,?,?)}";
	 * 
	 * CallableStatement cs = cn.prepareCall(sql);
	 * 
	 * cs.registerOutParameter(1, oracle.jdbc.OracleTypes.INTEGER); //
	 * P_PEDIDO_DETALLE_ID cs.setLong(2,
	 * pedidoDetalle.getPedidoCabecera().getId());// P_PEDIDO_CABECERA_ID
	 * cs.setLong(3, pedidoDetalle.getProducto().getId()); // P_PRODUCTO_ID
	 * cs.setLong(4, pedidoDetalle.getCantidad());// P_CANTIDAD cs.setDouble(5,
	 * pedidoDetalle.getPrecio());// P_PRECIO cs.setDouble(6,
	 * pedidoDetalle.getSubTotal());// P_SUB_TOTAL
	 * 
	 * // Auditoria
	 * 
	 * cs.setLong(7, pedidoDetalle.getAudIdUsuario());// P_AUD_IDUSUARIO
	 * cs.setString(8, pedidoDetalle.getAudSesion());// P_AUD_SESION cs.setString(9,
	 * pedidoDetalle.getAudIP());// P_AUD_IP cs.execute();
	 * 
	 * Integer id = cs.getInt(1);
	 * 
	 * cs.close(); cn.close();
	 * 
	 * return (id > 0);
	 * 
	 * } catch (Exception e) { e.printStackTrace();
	 * System.err.println("Error al registrar Pedido Detalle" + e.getMessage());
	 * throw new PersistenciaException(e); }
	 * 
	 * }
	 */

}
