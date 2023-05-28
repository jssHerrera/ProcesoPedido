package pe.edu.galaxy.training.java.bean.maestros;

import pe.edu.galaxy.training.java.bean.generico.GenericoBean;

public class Cliente extends GenericoBean {
	
	private String razonSocial;
	private String nroDocumento;
	private String correo;
	private String direccion;

	private Pais   pais;
	
	public Cliente() {
		this.pais=new Pais();
	}
	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public String getNroDocumento() {
		return nroDocumento;
	}

	public void setNroDocumento(String nroDocumento) {
		this.nroDocumento = nroDocumento;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}
	@Override
	public String toString() {
		return "Cliente [razonSocial=" + razonSocial + ", nroDocumento=" + nroDocumento + ", correo=" + correo
				+ ", direccion=" + direccion + ", pais=" + pais + "]";
	}
	
	
}
