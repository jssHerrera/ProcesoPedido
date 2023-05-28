package pe.edu.galaxy.training.java.bean.maestros;

public class ClienteVO {

	private Long 	id;
	private String 	cliente;
	private String 	pais;
	
	public ClienteVO() {
		super();
	}
	public ClienteVO(Long id, String cliente, String pais) {
		super();
		this.id = id;
		this.cliente = cliente;
		this.pais = pais;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCliente() {
		return cliente;
	}
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}

	
	
}
