package pe.edu.galaxy.training.java.presentacion.procesos;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import static java.util.Objects.isNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import pe.edu.galaxy.training.java.bean.maestros.Cliente;
import pe.edu.galaxy.training.java.bean.maestros.Empleado;
import pe.edu.galaxy.training.java.bean.maestros.Producto;
import pe.edu.galaxy.training.java.bean.procesos.PedidoCabecera;
import pe.edu.galaxy.training.java.bean.procesos.PedidoDetalle;
import pe.edu.galaxy.training.java.presentacion.generico.GenericoMB;
import pe.edu.galaxy.training.java.service.ServicioException;
import pe.edu.galaxy.training.java.service.impl.maestros.ClienteServiceImpl;
import pe.edu.galaxy.training.java.service.impl.maestros.ProductoServiceImpl;
import pe.edu.galaxy.training.java.service.impl.procesos.PedidoServiceImpl;
import pe.edu.galaxy.training.java.service.inf.maestros.ClienteService;
import pe.edu.galaxy.training.java.service.inf.maestros.ProductoService;
import pe.edu.galaxy.training.java.service.inf.procesos.PedidoService;

@ManagedBean(name = "pedidoMB")
@SessionScoped
public class PedidoMB extends GenericoMB {

	private Cliente cliente;

	private PedidoCabecera pedidoCabecera;

	// private Empleado empleado;

	private ClienteService clienteService;

	private ProductoService productoService;

	private List<Producto> productos;

	private Producto producto;

	private PedidoDetalle pedidoDetalleSeleccionado;

	private PedidoService pedidoService;

	public void listar() {

		try {
			setProductos(productoService.listar(this.getProducto()));
		} catch (Exception e) {
			System.err.println(e);
		}

	}

	public void grabar() {
		Boolean sw = false;

		if (!this.validar()) {
			return;
		}

		try {

			// Registar los datos de auditoria

			super.setAuditoria(pedidoCabecera);

			// Asignaciones(Seteos)

			// Empleado
			Empleado empleado = new Empleado();
			empleado.setId(1L);
			this.pedidoCabecera.setEmpleado(empleado);

			System.out.println("Pedido Cabecera ");
			System.out.println(pedidoCabecera);

			// Cliente
			pedidoCabecera.setCliente(this.cliente);

			if (this.getPedidoCabecera().getId() > 0) {
				sw = pedidoService.actualizar(pedidoCabecera);
			} else {
				sw = pedidoService.insertar(pedidoCabecera);
			}
			super.msgGrabar(sw);

		} catch (ServicioException e) {
			super.msgError();
			System.err.println(e);
		}

	}

	public PedidoMB() {

		this.cliente = new Cliente();

		this.pedidoCabecera = new PedidoCabecera();

		this.clienteService = new ClienteServiceImpl();

		this.cliente.setNroDocumento("20100017491");

		PedidoDetalle pedidoDetalle1 = new PedidoDetalle();
		Producto producto1 = new Producto();
		producto1.setId(1L);
		producto1.setNombre("LAPTOP ASUS VivoBook Pro");
		producto1.setPrecio(3500.0D);

		pedidoDetalle1.setProducto(producto1);
		pedidoDetalle1.setCantidad(2);
		pedidoDetalle1.setPrecio(producto1.getPrecio());

		pedidoDetalle1.calcularSubTotal();

		this.pedidoCabecera.getListaPedidoDetalle().add(pedidoDetalle1);

		PedidoDetalle pedidoDetalle2 = new PedidoDetalle();

		Producto producto2 = new Producto();
		producto2.setId(6L);
		producto2.setNombre("CELULAR LG -X24");
		producto2.setPrecio(2500.0D);

		pedidoDetalle2.setProducto(producto2);
		pedidoDetalle2.setCantidad(4);
		pedidoDetalle2.setPrecio(producto2.getPrecio());
		pedidoDetalle2.calcularSubTotal();

		this.pedidoCabecera.getListaPedidoDetalle().add(pedidoDetalle2);

		this.pedidoCabecera.setDescuento(120D);
		this.pedidoCabecera.calcularTotales();
		this.pedidoCabecera.setGlosa("Glosa demo " + LocalDateTime.now());

		this.setProducto(new Producto());

		this.productoService = new ProductoServiceImpl();

		this.pedidoService = new PedidoServiceImpl();

		this.listar();
	}

	public void actualizar() {
		for (PedidoDetalle objPedidoDetalle : pedidoCabecera.getListaPedidoDetalle()) {
			System.out.println("objPedidoDetalle =>" + objPedidoDetalle);
			objPedidoDetalle.calcularSubTotal();
		}
		this.pedidoCabecera.calcularTotales();
	}

	public void seleccionarDetallePedido(PedidoDetalle pedidoDetalle) {
		this.pedidoDetalleSeleccionado = pedidoDetalle;
	}

	public void eliminarDetalle() {
		for (Iterator<PedidoDetalle> iterator = pedidoCabecera.getListaPedidoDetalle().iterator(); iterator
				.hasNext();) {
			PedidoDetalle objPedidoDetalle = iterator.next();

			if (objPedidoDetalle.getProducto().getNombre()
					.equals(this.pedidoDetalleSeleccionado.getProducto().getNombre())) {
				iterator.remove();
			}
		}
		pedidoCabecera.calcularTotales();
	}

	public void seleccionarProducto(Producto producto) {

		PedidoDetalle pedidoDetalle = new PedidoDetalle();
		pedidoDetalle.setProducto(producto);
		pedidoDetalle.setCantidad(1);
		pedidoDetalle.setPrecio(producto.getPrecio());
		pedidoDetalle.calcularSubTotal();
		this.pedidoCabecera.getListaPedidoDetalle().add(pedidoDetalle);

		this.pedidoCabecera.calcularTotales();
	}

	public void buscarClienteXNroDocumento() {
		try {

			String nroDocumento = this.cliente.getNroDocumento();

			if (this.clienteService == null) {
				System.out.println("clienteService is null");
			} else {
				System.out.println("clienteService is not null");
			}

			Optional<Cliente> optCliente = this.clienteService.buscarXNroDocumento(nroDocumento);

			if (optCliente.isEmpty()) {
				super.msgAlert("No existe cliente con el ruc: " + nroDocumento + " especificado");
			}

			this.cliente = optCliente.get();

			if (this.getCliente().getRazonSocial() == null) {
				super.msgAlert("No existe cliente con el ruc: " + nroDocumento + " especificado");
			}

			System.out.println("cliente =>" + cliente);
		} catch (Exception e) {
			e.printStackTrace();
			super.msgError("Error la realizar la búsqueda");
		}
	}

	private boolean validar() {
		Long clienteId = this.cliente.getId();
		if (clienteId==0L) {
			super.msgAlert("El cliente es requerido, por favor ingrese el nro de RUC y presione el boton buscar");
			return false;
		}

		String glosa = this.getPedidoCabecera().getGlosa();
		if (isNull(glosa) || glosa.trim().length() <= 5) {
			super.msgAlert("La glosa es requerida y debe tener como mínimo 5 caracteres");
			return false;
		}
		return true;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public PedidoCabecera getPedidoCabecera() {
		return pedidoCabecera;
	}

	public void setPedidoCabecera(PedidoCabecera pedidoCabecera) {
		this.pedidoCabecera = pedidoCabecera;
	}

	public ClienteService getClienteService() {
		return clienteService;
	}

	public void setClienteService(ClienteService clienteService) {
		this.clienteService = clienteService;
	}

	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public PedidoDetalle getPedidoDetalleSeleccionado() {
		return pedidoDetalleSeleccionado;
	}

	public void setPedidoDetalleSeleccionado(PedidoDetalle pedidoDetalleSeleccionado) {
		this.pedidoDetalleSeleccionado = pedidoDetalleSeleccionado;
	}
}
