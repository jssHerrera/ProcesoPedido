package pe.edu.galaxy.training.java.bean.procesos;

import pe.edu.galaxy.training.java.bean.generico.GenericoBean;
import pe.edu.galaxy.training.java.bean.maestros.Producto;
import static java.util.Objects.isNull;

public class PedidoDetalle extends GenericoBean {
	
	private PedidoCabecera pedidoCabecera; 
	private Producto producto;
	private Integer cantidad;
	private Double 	precio;
	private Double 	subTotal;
	

	public PedidoDetalle() {
		super();
	}
	
	public void calcularSubTotal() {
		if (isNull(this.precio)) {
			this.precio=0.0;
		}
		if (isNull(this.cantidad)) {
			this.cantidad=0;
		}
		this.subTotal= this.precio*this.cantidad;
	}


	public PedidoCabecera getPedidoCabecera() {
		return pedidoCabecera;
	}

	public void setPedidoCabecera(PedidoCabecera pedidoCabecera) {
		this.pedidoCabecera = pedidoCabecera;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public Double getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(Double subTotal) {
		this.subTotal = subTotal;
	}

	@Override
	public String toString() {
		
		String strPedidoCabecera;
		
		if(isNull(pedidoCabecera)) {
			strPedidoCabecera="";
		}else {
			strPedidoCabecera= pedidoCabecera.toString();
		}
		
		return "PedidoDetalle [pedidoCabecera=" + strPedidoCabecera + ", producto=" + producto + ", cantidad=" + cantidad
				+ ", precio=" + precio + ", subTotal=" + subTotal + "]";
	}

}
