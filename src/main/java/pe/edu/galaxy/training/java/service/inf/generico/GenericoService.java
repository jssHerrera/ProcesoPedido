package pe.edu.galaxy.training.java.service.inf.generico;

import java.util.List;
import pe.edu.galaxy.training.java.service.ServicioException;

public interface GenericoService<T> {

	List<T> listar(T t) throws ServicioException; 
	
	T buscarXId(Long id) throws ServicioException; 
	
	boolean insertar(T t) throws ServicioException; 
	
	boolean actualizar(T t) throws ServicioException; 
	
	boolean eliminar(T t) throws ServicioException; 
	
}
