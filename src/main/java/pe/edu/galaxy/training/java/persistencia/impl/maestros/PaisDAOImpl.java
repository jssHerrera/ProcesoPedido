package pe.edu.galaxy.training.java.persistencia.impl.maestros;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import pe.edu.galaxy.training.java.bean.maestros.Pais;
import pe.edu.galaxy.training.java.bean.maestros.Producto;
import pe.edu.galaxy.training.java.persistencia.PersistenciaException;
import pe.edu.galaxy.training.java.persistencia.inf.maestros.PaisDAO;
import pe.edu.galaxy.training.java.persistencia.util.BD;

public class PaisDAOImpl implements PaisDAO{

	@Override
	public List<Pais> listar(Pais t) throws PersistenciaException {

		try {
	
			Connection cn= BD.getConnection();
			
			String sql="SELECT ID_PAIS, NOMBRE, SIGLA FROM PAIS WHERE ESTADO='1'";
			
			PreparedStatement ps= cn.prepareStatement(sql);

			ResultSet rs= ps.executeQuery();
			
			List<Pais>  lst= new ArrayList<Pais>();
			
			while (rs.next()) {
				
				Pais oPais= new Pais();
				oPais.setId(rs.getLong("ID_PAIS"));
				oPais.setNombre(rs.getString("NOMBRE"));
				oPais.setSigla(rs.getString("SIGLA"));
				lst.add(oPais);
			}
			
			rs.close();
			ps.close();
			cn.close();
			
			return lst;
			
		} catch (Exception e) {
			System.err.println("Error al listar paises" + e.getMessage());
			return null;
		}
		
		
	
	}

	@Override
	public Pais buscarXId(Long id) throws PersistenciaException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insertar(Pais t) throws PersistenciaException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean actualizar(Pais t) throws PersistenciaException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean eliminar(Pais t) throws PersistenciaException {
		// TODO Auto-generated method stub
		return false;
	}

}
