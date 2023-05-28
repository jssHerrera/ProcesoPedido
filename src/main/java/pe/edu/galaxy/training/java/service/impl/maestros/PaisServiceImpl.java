package pe.edu.galaxy.training.java.service.impl.maestros;

import java.util.List;

import pe.edu.galaxy.training.java.bean.maestros.Pais;
import pe.edu.galaxy.training.java.persistencia.PersistenciaException;
import pe.edu.galaxy.training.java.persistencia.impl.maestros.PaisDAOImpl;
import pe.edu.galaxy.training.java.persistencia.inf.maestros.PaisDAO;
import pe.edu.galaxy.training.java.service.ServicioException;
import pe.edu.galaxy.training.java.service.inf.maestros.PaisService;

public class PaisServiceImpl implements PaisService{
	
	private PaisDAO paisDAO;
	
	public PaisServiceImpl() {
		this.paisDAO= new PaisDAOImpl();
	}

	@Override
	public List<Pais> listar(Pais t) throws ServicioException {
		try {
			return this.paisDAO.listar(null);
		} catch (PersistenciaException e) {
			throw new ServicioException(e);
		}
	}

	@Override
	public Pais buscarXId(Long id) throws ServicioException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insertar(Pais t) throws ServicioException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean actualizar(Pais t) throws ServicioException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean eliminar(Pais t) throws ServicioException {
		// TODO Auto-generated method stub
		return false;
	}

}
