package pe.edu.galaxy.training.java.presentacion.maestros;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import pe.edu.galaxy.training.java.bean.maestros.Cliente;
import pe.edu.galaxy.training.java.bean.maestros.ClienteVO;
import pe.edu.galaxy.training.java.bean.maestros.Pais;
import pe.edu.galaxy.training.java.presentacion.generico.GenericoMB;
import pe.edu.galaxy.training.java.service.ServicioException;
import pe.edu.galaxy.training.java.service.impl.maestros.ClienteServiceImpl;
import pe.edu.galaxy.training.java.service.impl.maestros.PaisServiceImpl;
import pe.edu.galaxy.training.java.service.inf.maestros.ClienteService;
import pe.edu.galaxy.training.java.service.inf.maestros.PaisService;

@ManagedBean(name = "clienteMB")
@SessionScoped
public class ClienteMB extends GenericoMB {

	private List<Pais> paises;

	private List<ClienteVO> clientesVO;

	private PaisService paisService;

	private ClienteService clienteService;

	private Cliente cliente;

	private ClienteVO clienteVO;

	private ClienteVO clienteSeleccionado;

	public ClienteMB() {
		paisService = new PaisServiceImpl();
		clienteService = new ClienteServiceImpl();

		this.setCliente(new Cliente());
		this.setClienteVO(new ClienteVO());
		this.inicializarDatos();
		// Listas
		this.listarPaises();
		this.listar();
	}
	
	public String nuevo() {
		return "clientes_registro";
	}

	public String modificar(ClienteVO clienteVO) {
		try {
			this.cliente=clienteService.buscarXId(clienteVO.getId());
			return "clientes_registro";
		} catch (Exception e) {
			System.err.println(e);
		}
		return "";
	}
	
	
	public void listar() {
		try {
			setClientesVO(clienteService.listarVO(this.clienteVO));
		} catch (Exception e) {
			System.err.println(e);
		}
	}

	public void seleccionar(ClienteVO clienteVO) {
		this.clienteSeleccionado = clienteVO;
	}

	public void grabar() {
		// System.out.println("Id pais =>" + this.cliente.getPais().getId());
		Boolean sw = false;

		if (!this.validar()) {
			return;
		}

		try {

			// Registar los datos de auditoria

			super.setAuditoria(cliente);

			if (this.getCliente().getId() > 0) {
				sw = clienteService.actualizar(cliente);
			} else {
				sw = clienteService.insertar(cliente);
			}
			super.msgGrabar(sw);

		} catch (ServicioException e) {
			super.msgError();
			System.err.println(e);
		}
	}

	public String cancelar() {
		return "clientes_listado";
	}

	public void listarPaises() {
		try {
			this.paises = paisService.listar(null);
			// this.paises.forEach(System.out::println);
		} catch (ServicioException e) {
			System.err.println(e.getMessage());
		}
	}

	private boolean validar() {
		String razonSocial = this.getCliente().getRazonSocial();
		if (!(razonSocial != null && razonSocial.trim().length() > 5)) {
			super.msgAlert("La razon social es requerida y debe tener mínimo 5 caracteres");
			return false;
		}

		Long idPais = this.getCliente().getPais().getId();
		if (!(idPais != null && idPais > 0)) {
			super.msgAlert("El país es requerido");
			return false;
		}
		return true;
	}

	public List<Pais> getPaises() {
		return paises;
	}

	public void setPaises(List<Pais> paises) {
		this.paises = paises;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	private void inicializarDatos() {
		cliente.setRazonSocial("TELEFONICA DEL ESPAÑA S.A.A");
		cliente.setNroDocumento("20100017492");
		cliente.setCorreo("contacto@movistar.com.es");
		cliente.setDireccion("Jirón Domingo Martínez Luján 1130 - Los Aites- España");
	}

	public List<ClienteVO> getClientesVO() {
		return clientesVO;
	}

	public void setClientesVO(List<ClienteVO> clientesVO) {
		this.clientesVO = clientesVO;
	}

	public ClienteVO getClienteVO() {
		return clienteVO;
	}

	public void setClienteVO(ClienteVO clienteVO) {
		this.clienteVO = clienteVO;
	}

	public ClienteVO getClienteSeleccionado() {
		return clienteSeleccionado;
	}

	public void setClienteSeleccionado(ClienteVO clienteSeleccionado) {
		this.clienteSeleccionado = clienteSeleccionado;
	}

}
