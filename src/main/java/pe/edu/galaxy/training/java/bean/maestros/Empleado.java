package pe.edu.galaxy.training.java.bean.maestros;

import pe.edu.galaxy.training.java.bean.generico.GenericoBean;

public class Empleado extends GenericoBean {
	
	private String nombre;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private String nroDocumento;
	private String correo;
	private String telefono;
	private String direccion;

	private Cargo   cargo;
	
	public Empleado() {
		this.cargo=new Cargo();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidoPaterno() {
		return apellidoPaterno;
	}

	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}

	public String getApellidoMaterno() {
		return apellidoMaterno;
	}

	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
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

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	@Override
	public String toString() {
		return "Empleado [nombre=" + nombre + ", apellidoPaterno=" + apellidoPaterno + ", apellidoMaterno="
				+ apellidoMaterno + ", nroDocumento=" + nroDocumento + ", correo=" + correo + ", telefono=" + telefono
				+ ", direccion=" + direccion + ", cargo=" + cargo + "]";
	}
	
	
}
