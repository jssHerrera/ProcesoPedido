package pe.edu.galaxy.training.java.persistencia.impl.seguridad;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import pe.edu.galaxy.training.java.bean.seguridad.Usuario;
import pe.edu.galaxy.training.java.persistencia.PersistenciaException;
import pe.edu.galaxy.training.java.persistencia.inf.seguridad.UsuarioDAO;
import pe.edu.galaxy.training.java.persistencia.util.BD;

public class UsuarioDAOImpl implements UsuarioDAO {

	@Override
	public Usuario validarAcceso(Usuario usuario) throws PersistenciaException {

		try {
			
			Usuario oUsuario=null;
			
			Connection cn= BD.getConnection();
			
			String sql="{call PKG_USUARIO.SP_VALIDAR_ACCESO(?,?,?)}"; // JDBC
			
			CallableStatement cs= cn.prepareCall(sql);
			
			cs.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR); // Resultado
			cs.setString(2,  usuario.getUsuario());
			cs.setString(3,  usuario.getClave());
			
			cs.execute();
			
			ResultSet rs= (ResultSet) cs.getObject(1);
			
			if (rs.next()) {
				
				oUsuario= new Usuario();
				
				oUsuario.setId(rs.getLong("ID_USUARIO"));
				oUsuario.setUsuario(rs.getString("USUARIO"));
				oUsuario.setClave(rs.getString("CLAVE"));
				oUsuario.setNombre(rs.getString("NOMBRE"));
				
			}
			
			rs.close();
			cs.close();
			cn.close();

			return oUsuario;
			
		} catch (Exception e) {
			System.err.println("Error al validar el usuario" + e.getMessage());
			throw new PersistenciaException(e);
		}

	}

	@Override
	public List<Usuario> listar(Usuario usuario) throws PersistenciaException {

		try {
			
			List<Usuario> usuarios=null;
			
			Connection cn= BD.getConnection();
			
			String sql="{call PKG_USUARIO.SP_LISTAR(?,?,?)}";
			
			CallableStatement cs= cn.prepareCall(sql);
			
			cs.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
			cs.setString(2,  usuario.getUsuario());
			cs.setString(3,  usuario.getNombre());
			
			cs.execute();
			
			ResultSet rs= (ResultSet) cs.getObject(1);
			usuarios= new ArrayList<Usuario>();
			while (rs.next()) {
				
				Usuario oUsuario= new Usuario();
				
				oUsuario.setId(rs.getLong("ID_USUARIO"));
				oUsuario.setUsuario(rs.getString("USUARIO"));
				oUsuario.setClave(rs.getString("CLAVE"));
				oUsuario.setNombre(rs.getString("NOMBRE"));
				
				usuarios.add(oUsuario);
				
			}
			
			rs.close();
			cs.close();
			cn.close();

			return usuarios;
			
		} catch (Exception e) {
			System.err.println("Error al listar usuarios" + e.getMessage());
			throw new PersistenciaException(e);
		}

	}

	@Override
	public Usuario buscarXId(Long id) throws PersistenciaException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insertar(Usuario usuario) throws PersistenciaException {

		try {
			
			Connection cn= BD.getConnection();
			
			String sql="{call PKG_USUARIO.SP_INSERTAR(?,?,?,?)}";
			
			CallableStatement cs= cn.prepareCall(sql);
			
			cs.registerOutParameter(1, oracle.jdbc.OracleTypes.INTEGER);
			cs.setString(2,  usuario.getUsuario());
			cs.setString(3,  usuario.getClave());
			cs.setString(4,  usuario.getNombre());
			
			cs.execute();
			
			Integer id= cs.getInt(1);
			
			cs.close();
			cn.close();

			return (id>0);
			
		} catch (Exception e) {
			System.err.println("Error al registrar usuario" + e.getMessage());
			throw new PersistenciaException(e);
		}

	}

	@Override
	public boolean actualizar(Usuario usuario) throws PersistenciaException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean eliminar(Usuario usuario) throws PersistenciaException {
		// TODO Auto-generated method stub
		return false;
	}

}
