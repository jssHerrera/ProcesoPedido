package pe.edu.galaxy.training.java.service.impl.maestros;

import java.util.List;

import pe.edu.galaxy.training.java.bean.maestros.Producto;
import pe.edu.galaxy.training.java.persistencia.PersistenciaException;
import pe.edu.galaxy.training.java.persistencia.impl.maestros.ProductoDAOImpl;
import pe.edu.galaxy.training.java.persistencia.inf.maestros.ProductoDAO;
import pe.edu.galaxy.training.java.service.ServicioException;
import pe.edu.galaxy.training.java.service.inf.maestros.ProductoService;

public class ProductoServiceImpl implements ProductoService{
	
	private ProductoDAO productoDAO;
	
	public ProductoServiceImpl() {
		productoDAO= new ProductoDAOImpl();
	}
	
	@Override
	public List<Producto> listar(Producto producto) throws ServicioException {
		try {
			return productoDAO.listar(producto);
		} catch (PersistenciaException e) {
			throw new ServicioException(e);
		}
	}

	@Override
	public Producto buscarXId(Long id) throws ServicioException {
		try {
			return productoDAO.buscarXId(id);
		} catch (PersistenciaException e) {
			throw new ServicioException(e);
		}
	}

	@Override
	public boolean insertar(Producto producto) throws ServicioException {
		try {
			return productoDAO.insertar(producto);
		} catch (PersistenciaException e) {
			throw new ServicioException(e);
		}
	}

	@Override
	public boolean actualizar(Producto producto) throws ServicioException {
		try {
			return productoDAO.actualizar(producto);
		} catch (PersistenciaException e) {
			throw new ServicioException(e);
		}
	}

	@Override
	public boolean eliminar(Producto producto) throws ServicioException {
		try {
			return productoDAO.eliminar(producto);
		} catch (PersistenciaException e) {
			throw new ServicioException(e);
		}
	}

}
