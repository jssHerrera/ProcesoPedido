package pe.edu.galaxy.training.java.service.inf.seguridad;

import pe.edu.galaxy.training.java.bean.seguridad.Usuario;
import pe.edu.galaxy.training.java.service.ServicioException;
import pe.edu.galaxy.training.java.service.inf.generico.GenericoService;

public interface UsuarioService extends GenericoService<Usuario> {

	Usuario validarAcceso(Usuario usuario) throws  ServicioException;
}
