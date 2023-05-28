package pe.edu.galaxy.training.java.persistencia.impl.maestros;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import pe.edu.galaxy.training.java.bean.maestros.Cliente;
import pe.edu.galaxy.training.java.bean.maestros.ClienteVO;
import pe.edu.galaxy.training.java.persistencia.PersistenciaException;
import pe.edu.galaxy.training.java.persistencia.inf.maestros.ClienteDAO;
import pe.edu.galaxy.training.java.persistencia.util.BD;
import pe.edu.galaxy.training.java.persistencia.util.QueryUtil;

public class ClienteDAOImpl implements ClienteDAO{

	public ClienteDAOImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Cliente> listar(Cliente t) throws PersistenciaException {
		
		return null;
	}

	@Override
	public Cliente buscarXId(Long id) throws PersistenciaException {
		String sql=" SELECT"
				+ "    CLIENTE_ID,"
				+ "    RAZON_SOCIAL,"
				+ "    NRO_DOCUMENTO,"
				+ "    CORREO,"
				+ "    DIRECCION,"
				+ "    ID_PAIS"
				+ " FROM"
				+ "    CLIENTE "
				+ " WHERE "
				+ "   CLIENTE_ID= ?";
		try {
			
		Connection cn= BD.getConnection();
		PreparedStatement ps= cn.prepareStatement(sql);
		
		ps.setLong(1, id);
		
		ResultSet rs= ps.executeQuery();
		
		Cliente cliente= new Cliente();
		
		while (rs.next()) {
			
			cliente.setId(rs.getLong("CLIENTE_ID"));
			cliente.setRazonSocial(rs.getString("RAZON_SOCIAL"));
			cliente.setNroDocumento(rs.getString("NRO_DOCUMENTO"));
			cliente.setCorreo(rs.getString("CORREO"));
			cliente.setDireccion(rs.getString("DIRECCION"));
			cliente.getPais().setId(rs.getLong("ID_PAIS")); // Tener cuidado
		}
		
		rs.close();
		ps.close();
		cn.close();
		
		return cliente;

		} catch (Exception e) {
			throw new PersistenciaException ("Error al buscar cliente por id" + e.getMessage());
		}
	}

	@Override
	public boolean insertar(Cliente cliente) throws PersistenciaException {
		
		try {
		
			Connection cn= BD.getConnection();
			
			String sql="INSERT INTO CLIENTE(CLIENTE_ID,RAZON_SOCIAL,NRO_DOCUMENTO,CORREO,DIRECCION,ID_PAIS,"
					+ "AUD_TIPO,AUD_IDUSUARIO,AUD_SESION,AUD_IP,AUD_FECHA)" + 
					"VALUES(SEQ_CLIENTE.NEXTVAL,?,?,?,?,?,"
					+ "'I',?,?,?,SYSDATE)";
			
			PreparedStatement ps= cn.prepareStatement(sql);
			
			ps.setString(1, cliente.getRazonSocial());
			ps.setString(2, cliente.getNroDocumento());
			ps.setString(3, cliente.getCorreo());
			ps.setString(4, cliente.getDireccion());
			
			ps.setLong(5, cliente.getPais().getId());
			
			// Auditoria
			ps.setLong(6, cliente.getAudIdUsuario());
			ps.setString(7, cliente.getAudSesion());
			ps.setString(8, cliente.getAudIP());
			
			ps.executeUpdate();

			ps.close();
			cn.close();
			
			return true;
		} catch (Exception e) {
			throw new PersistenciaException ("Error al insertar cliente" + e.getMessage());
		}
		
	}

	@Override
	public boolean actualizar(Cliente cliente) throws PersistenciaException {

		
		try {
		
			Connection cn= BD.getConnection();
			
			String sql="UPDATE PRODUCTO "
					+ " SET RAZON_SOCIAL=?, "
					+ " NRO_DOCUMENTO=?,"
					+ " CORREO=?, "
					+ " DIRECCION=?, "
					+ " ID_PAIS=?, "
					+ " AUD_TIPO='A', AUD_IDUSUARIO=?, AUD_SESION=?,AUD_IP=?, AUD_FECHA=SYSDATE"
					+ " WHERE "
					+ "		CLIENTE_ID=?" ;

			
			PreparedStatement ps= cn.prepareStatement(sql);
			
			ps.setString(1, cliente.getRazonSocial());
			ps.setString(2, cliente.getNroDocumento());
			ps.setString(3, cliente.getCorreo());
			ps.setString(4, cliente.getDireccion());
			ps.setLong(5,cliente.getPais().getId());
			
			// Auditoria
			
			ps.setLong(6, cliente.getAudIdUsuario());
			ps.setString(7, cliente.getAudSesion());
			ps.setString(8, cliente.getAudIP());
			
			ps.setLong(9, cliente.getId());
			
			ps.executeUpdate();

			ps.close();
			cn.close();
			
			return true;
		} catch (Exception e) {
			System.err.println("Error al actualizar cliente" + e.getMessage());
			return false;
		}
		
	
	}

	@Override
	public boolean eliminar(Cliente t) throws PersistenciaException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<ClienteVO> listarVO(ClienteVO clienteVO) throws PersistenciaException {

		try {
	
			Connection cn= BD.getConnection();
			
			String sql="SELECT CLIENTE_ID,NOMBRE,PAIS FROM VIE_CLIENTES WHERE "
					+ "    UPPER(NOMBRE||PAIS) LIKE UPPER(?)"; //Vista
			
			PreparedStatement ps= cn.prepareStatement(sql);
			
			ps.setString(1, QueryUtil.getLike(clienteVO.getCliente()));// "%"+clienteVO.getCliente()+"%"
			
			ResultSet rs= ps.executeQuery();
			
			List<ClienteVO>  lst= new ArrayList<>();
			
			while (rs.next()) {
				lst.add(new ClienteVO(rs.getLong("CLIENTE_ID"), rs.getString("NOMBRE"),rs.getString("PAIS")));
			}
			
			rs.close();
			ps.close();
			cn.close();
			
			return lst;
			
		} catch (Exception e) {
			throw new PersistenciaException ("Error al listar clientes" + e.getMessage());
		}
		
	}

	@Override
	public Optional<Cliente> buscarXNroDocumento(String nroDocumento) throws PersistenciaException {
		String sql=" SELECT"
				+ "    CLIENTE_ID,"
				+ "    RAZON_SOCIAL"
				+ " FROM"
				+ "    CLIENTE "
				+ " WHERE "
				+ "   NRO_DOCUMENTO= ?";
		try {
			
		Connection cn= BD.getConnection();
		PreparedStatement ps= cn.prepareStatement(sql);
		
		ps.setString(1, nroDocumento);
		
		ResultSet rs= ps.executeQuery();
		
		Cliente cliente= new Cliente();
		
		while (rs.next()) {
			
			cliente.setId(rs.getLong("CLIENTE_ID"));
			cliente.setRazonSocial(rs.getString("RAZON_SOCIAL"));
		}
		
		rs.close();
		ps.close();
		cn.close();
		
		return Optional.ofNullable(cliente);

		} catch (Exception e) {
			throw new PersistenciaException ("Error al buscar cliente por Nro. Documento" + e.getMessage());
		}
	}

}
