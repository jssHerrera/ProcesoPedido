package pe.edu.galaxy.training.java.bean.procesos;

import static java.util.Objects.isNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import pe.edu.galaxy.training.java.bean.generico.GenericoBean;
import pe.edu.galaxy.training.java.bean.maestros.Cliente;
import pe.edu.galaxy.training.java.bean.maestros.Empleado;
import pe.edu.galaxy.training.java.constantes.Constantes;

public class PedidoCabecera extends GenericoBean {
	
	private Cliente cliente;
	private Empleado empleado;
	
	private String glosa;
	
	private Date fechaRegistro;
	
	private Double subTotal;
	private Double descuento;
	private Double igv;
	private Double total;
	
	private List<PedidoDetalle> listaPedidoDetalle;
	
	public PedidoCabecera() {
		super();
		this.subTotal=0.0;
		this.descuento=0.0;
		this.igv=0.0;
		this.total=0.0;

		this.cliente= new Cliente();
		this.empleado= new Empleado();
		this.listaPedidoDetalle=new ArrayList<PedidoDetalle>();
	}

	
	public void calcularTotales() {
		
		this.subTotal=0.0;
		
		if (isNull(this.listaPedidoDetalle)) {
			this.subTotal=0.0; 	// 1,000
			this.descuento=0.0; // 100
			this.igv=0.0;		// (1,000 - 100)*0.18 => 162
			this.total=0.0;		// 900+162 = 1,062
		}
		
		for (PedidoDetalle objPedidoDetalle : listaPedidoDetalle) {
			if (isNull(objPedidoDetalle)) {
				break;
			}
			
			System.out.println("objPedidoDetalle =>"+objPedidoDetalle);
			this.subTotal+= objPedidoDetalle.getSubTotal();	
		}
		
		if (isNull(this.subTotal)) {
			this.subTotal=0.0;
		}
		
		double subTotalDescuento=(this.subTotal-this.descuento);
		
		this.igv= subTotalDescuento* Constantes.IGV;
		
		this.total=subTotalDescuento+ igv;
	}
	
	
	
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Empleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	public String getGlosa() {
		return glosa;
	}

	public void setGlosa(String glosa) {
		this.glosa = glosa;
	}

	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public Double getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(Double subTotal) {
		this.subTotal = subTotal;
	}

	public Double getDescuento() {
		return descuento;
	}

	public void setDescuento(Double descuento) {
		this.descuento = descuento;
	}

	public Double getIgv() {
		return igv;
	}

	public void setIgv(Double igv) {
		this.igv = igv;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public List<PedidoDetalle> getListaPedidoDetalle() {
		return listaPedidoDetalle;
	}

	public void setListaPedidoDetalle(List<PedidoDetalle> pedidoDetalle) {
		this.listaPedidoDetalle = pedidoDetalle;
	}

	@Override
	public String toString() {
		return "PedidoCabecera [cliente=" + cliente + ", empleado=" + empleado + ", glosa=" + glosa + ", fechaRegistro="
				+ fechaRegistro + ", subTotal=" + subTotal + ", descuento=" + descuento + ", igv=" + igv + ", total="
				+ total + ", listaPedidoDetalle =" + listaPedidoDetalle  + "]";
	}

	
}
