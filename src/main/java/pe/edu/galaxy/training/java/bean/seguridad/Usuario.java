package pe.edu.galaxy.training.java.bean.seguridad;

import pe.edu.galaxy.training.java.bean.generico.GenericoBean;

public class Usuario extends GenericoBean{

	private Long id=0l;
	private String usuario;
	private String clave;
	private String nombre;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}
