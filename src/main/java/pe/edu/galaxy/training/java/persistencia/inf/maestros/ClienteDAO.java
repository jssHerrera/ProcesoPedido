package pe.edu.galaxy.training.java.persistencia.inf.maestros;

import java.util.List;
import java.util.Optional;

import pe.edu.galaxy.training.java.bean.maestros.Cliente;
import pe.edu.galaxy.training.java.bean.maestros.ClienteVO;
import pe.edu.galaxy.training.java.persistencia.PersistenciaException;
import pe.edu.galaxy.training.java.persistencia.inf.generico.GenericoDAO;

public interface ClienteDAO extends GenericoDAO<Cliente>{

	public List<ClienteVO> listarVO(ClienteVO clienteVO) throws PersistenciaException;
	
	Optional<Cliente> buscarXNroDocumento(String nroDocumento) throws  PersistenciaException;
}
