package pe.edu.galaxy.training.java.service.inf.maestros;

import java.util.List;
import java.util.Optional;

import pe.edu.galaxy.training.java.bean.maestros.Cliente;
import pe.edu.galaxy.training.java.bean.maestros.ClienteVO;
import pe.edu.galaxy.training.java.service.ServicioException;
import pe.edu.galaxy.training.java.service.inf.generico.GenericoService;

public interface ClienteService extends GenericoService<Cliente>{

	List<ClienteVO> listarVO(ClienteVO clienteVO) throws  ServicioException;
	
	Optional<Cliente> buscarXNroDocumento(String nroDocumento) throws  ServicioException;
	
}
