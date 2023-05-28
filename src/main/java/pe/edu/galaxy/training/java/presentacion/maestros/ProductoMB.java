package pe.edu.galaxy.training.java.presentacion.maestros;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import pe.edu.galaxy.training.java.bean.maestros.Producto;
import pe.edu.galaxy.training.java.presentacion.generico.GenericoMB;
import pe.edu.galaxy.training.java.service.ServicioException;
import pe.edu.galaxy.training.java.service.impl.maestros.ProductoServiceImpl;
import pe.edu.galaxy.training.java.service.inf.maestros.ProductoService;

@ManagedBean(name = "productoMB")
@SessionScoped
public class ProductoMB extends GenericoMB{
	
	//Logger log

	private ProductoService productoService;
	
	private List<Producto> productos;

	private Producto producto;
	
	private Producto productoSeleccionado;

	public ProductoMB() {
		this.setProducto(new Producto());
		this.productoService= new ProductoServiceImpl();
		this.listar();
	}

	public void listar() {

		try {
			setProductos(productoService.listar(this.getProducto()));
		} catch (Exception e) {
			System.err.println(e);
		}

	}
	
	public void seleccionar(Producto producto) {
		this.productoSeleccionado=producto;
	}
	
	public void eliminar() {
		
		Boolean sw=false;
		
		try {
			
			super.setAuditoria(productoSeleccionado);
			
			sw=productoService.eliminar(productoSeleccionado);
			
			super.msgEliminar(sw);
			if (sw) {
				this.listar();
			}
		} catch (ServicioException e) {
			super.msgError();
		}

	}

	public String modificar(Producto producto) {
		this.setProducto(producto);
		return "producto_registro";
	}

	public String nuevo() {
		this.setProducto(new Producto());
		return "producto_registro";
	}
	
	public void limpiar() {
		this.setProducto(new Producto());
		this.listar();
	}

	public String cancelar() {
		this.setProducto(new Producto());
		return "producto_listado";
	}

	public void grabar() {
		Boolean sw=false;
		
		if (!this.validar()) {
			return;
		}
	
		try {

			// Registar los datos de auditoria
			
			super.setAuditoria(producto);
			
			if (this.getProducto().getId() > 0) {
				sw=productoService.actualizar(producto);
			} else {
				sw=productoService.insertar(producto);
			}
			super.msgGrabar(sw);

		} catch (ServicioException e) {
			super.msgError();
			System.err.println(e);
		}

	}
	
	private boolean validar() {
		String nombre=this.getProducto().getNombre();
		if (!(nombre!=null && nombre.trim().length()>5)) {
			super.msgAlert("El nombre es requerido y debe tener mínimo 5 caracteres");
			return false;
		}
		
		Double precio=this.getProducto().getPrecio();
		if (!(precio!=null && precio>0)) {
			super.msgAlert("El precio es requerido y debe ser mayor que cero");
			return false;
		}
		
		Integer stock=this.getProducto().getStock();
		if (!(stock!=null && stock>=0)) {
			super.msgAlert("El stock es requerido y debe se mayor o igual que cero");
			return false;
		}
		
		return true;
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

	public Producto getProductoSeleccionado() {
		return productoSeleccionado;
	}

	public void setProductoSeleccionado(Producto productoSeleccionado) {
		this.productoSeleccionado = productoSeleccionado;
	}
	
	
}
