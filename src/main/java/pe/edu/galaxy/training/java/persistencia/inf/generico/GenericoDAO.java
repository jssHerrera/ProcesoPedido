package pe.edu.galaxy.training.java.persistencia.inf.generico;

import java.util.List;
import pe.edu.galaxy.training.java.persistencia.PersistenciaException;

public interface GenericoDAO<T> {

	List<T> listar(T t) throws PersistenciaException; 
	
	T buscarXId(Long id) throws PersistenciaException; 
	
	boolean insertar(T t) throws PersistenciaException; 
	
	boolean actualizar(T t) throws PersistenciaException; 
	
	boolean eliminar(T t) throws PersistenciaException; 
	
}
