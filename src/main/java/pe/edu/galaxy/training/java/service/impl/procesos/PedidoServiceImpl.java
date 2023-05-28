package pe.edu.galaxy.training.java.service.impl.procesos;

import java.util.List;
import pe.edu.galaxy.training.java.bean.procesos.PedidoCabecera;
import pe.edu.galaxy.training.java.persistencia.PersistenciaException;
import pe.edu.galaxy.training.java.persistencia.impl.procesos.PedidoDAOImpl;
import pe.edu.galaxy.training.java.persistencia.inf.procesos.PedidoDAO;
import pe.edu.galaxy.training.java.service.ServicioException;
import pe.edu.galaxy.training.java.service.inf.procesos.PedidoService;

public class PedidoServiceImpl implements PedidoService {

	private PedidoDAO pedidoDAO;
	
	public PedidoServiceImpl() {
		this.pedidoDAO= new PedidoDAOImpl();
	}

	@Override
	public List<PedidoCabecera> listar(PedidoCabecera t) throws ServicioException {
		
		return null;
	}

	@Override
	public PedidoCabecera buscarXId(Long id) throws ServicioException {
		
		return null;
	}

	@Override
	public boolean insertar(PedidoCabecera pedidoCabecera) throws ServicioException {
		try {
			return this.pedidoDAO.insertar(pedidoCabecera);
		} catch (PersistenciaException e) {
			throw new ServicioException(e);
		}
	}

	@Override
	public boolean actualizar(PedidoCabecera t) throws ServicioException {
		
		return false;
	}

	@Override
	public boolean eliminar(PedidoCabecera t) throws ServicioException {
		
		return false;
	}

}
