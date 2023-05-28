package pe.edu.galaxy.training.java.bean.maestros;

import pe.edu.galaxy.training.java.bean.generico.GenericoBean;

public class Cargo extends GenericoBean {
	
	private String nombreCorto;
	private String nombreLargo;
	
	public Cargo() {
		super();
	}

	public String getNombreCorto() {
		return nombreCorto;
	}

	public void setNombreCorto(String nombreCorto) {
		this.nombreCorto = nombreCorto;
	}

	public String getNombreLargo() {
		return nombreLargo;
	}

	public void setNombreLargo(String nombreLargo) {
		this.nombreLargo = nombreLargo;
	}

	@Override
	public String toString() {
		return "Cargo [nombreCorto=" + nombreCorto + ", nombreLargo=" + nombreLargo + "]";
	}

	

}
