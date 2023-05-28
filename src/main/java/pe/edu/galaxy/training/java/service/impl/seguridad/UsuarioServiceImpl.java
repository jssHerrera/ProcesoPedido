package pe.edu.galaxy.training.java.service.impl.seguridad;

import java.util.List;

import pe.edu.galaxy.training.java.bean.seguridad.Usuario;
import pe.edu.galaxy.training.java.persistencia.PersistenciaException;
import pe.edu.galaxy.training.java.persistencia.impl.seguridad.UsuarioDAOImpl;
import pe.edu.galaxy.training.java.persistencia.inf.seguridad.UsuarioDAO;
import pe.edu.galaxy.training.java.service.ServicioException;
import pe.edu.galaxy.training.java.service.inf.seguridad.UsuarioService;

public class UsuarioServiceImpl implements UsuarioService{
	
	private UsuarioDAO usuarioDAO; // Data Access Object
	
	public UsuarioServiceImpl() {
		usuarioDAO= new UsuarioDAOImpl();
	}
	
	@Override
	public List<Usuario> listar(Usuario usuario) throws ServicioException {
		try {
			return usuarioDAO.listar(usuario);
		} catch (PersistenciaException e) {
			throw new ServicioException(e);
		}
	}

	@Override
	public Usuario buscarXId(Long id) throws ServicioException {
		try {
			return usuarioDAO.buscarXId(id);
		} catch (PersistenciaException e) {
			throw new ServicioException(e);
		}
	}

	@Override
	public boolean insertar(Usuario usuario) throws ServicioException {
		try {
			return usuarioDAO.insertar(usuario);
		} catch (PersistenciaException e) {
			throw new ServicioException(e);
		}
	}

	@Override
	public boolean actualizar(Usuario usuario) throws ServicioException {
		try {
			return usuarioDAO.actualizar(usuario);
		} catch (PersistenciaException e) {
			throw new ServicioException(e);
		}
	}

	@Override
	public boolean eliminar(Usuario usuario) throws ServicioException {
		try {
			return usuarioDAO.eliminar(usuario);
		} catch (PersistenciaException e) {
			throw new ServicioException(e);
		}
	}

	@Override
	public Usuario validarAcceso(Usuario usuario) throws ServicioException {
		try {
			return usuarioDAO.validarAcceso(usuario);
		} catch (PersistenciaException e) {
			throw new ServicioException(e);
		}
	}

}
