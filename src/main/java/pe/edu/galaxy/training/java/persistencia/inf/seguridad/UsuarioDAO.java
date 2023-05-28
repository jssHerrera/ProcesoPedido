package pe.edu.galaxy.training.java.persistencia.inf.seguridad;

import pe.edu.galaxy.training.java.bean.seguridad.Usuario;
import pe.edu.galaxy.training.java.persistencia.PersistenciaException;
import pe.edu.galaxy.training.java.persistencia.inf.generico.GenericoDAO;

public interface UsuarioDAO extends GenericoDAO<Usuario>{

	Usuario validarAcceso(Usuario usuario) throws PersistenciaException;
	
}
