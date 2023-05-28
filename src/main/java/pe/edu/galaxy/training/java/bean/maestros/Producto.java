package pe.edu.galaxy.training.java.bean.maestros;

import pe.edu.galaxy.training.java.bean.generico.GenericoBean;

public class Producto extends GenericoBean{
	
	private Long id=0L;
	private String nombre;
	private Double precio;
	private Integer stock;

	public Producto() {	
	}
	
	public Producto(Long id, String nombre, Double precio, Integer stock) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.precio = precio;
		this.stock = stock;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	@Override
	public String toString() {
		return "Producto [id=" + id + ", nombre=" + nombre + ", precio=" + precio + ", stock=" + stock + "]";
	}

}
