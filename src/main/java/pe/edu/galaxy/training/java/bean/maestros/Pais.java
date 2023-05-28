package pe.edu.galaxy.training.java.bean.maestros;

import pe.edu.galaxy.training.java.bean.generico.GenericoBean;

public class Pais extends GenericoBean {
	
	private String nombre;
	private String sigla;
	
	public Pais() {
		super();
	}

	public Pais(Long id, String nombre, String sigla) {
		super(id);
		this.nombre = nombre;
		this.sigla = sigla;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	@Override
	public String toString() {
		return "Pais [nombre=" + nombre + ", sigla=" + sigla + "]";
	}
	

}
