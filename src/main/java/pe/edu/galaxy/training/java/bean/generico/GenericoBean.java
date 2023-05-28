package pe.edu.galaxy.training.java.bean.generico;

public class GenericoBean {

	private Long id=0l;
	
	private String 	AudTipo;
	private Long 	AudIdUsuario;
	private String 	AudSesion;
	private String 	AudIP;
	private String 	AudFecha;
	
	public GenericoBean() {
		
	}

	public GenericoBean(Long id) {
		this.id = id;
	}
	
	public String getAudTipo() {
		return AudTipo;
	}
	public void setAudTipo(String audTipo) {
		AudTipo = audTipo;
	}
	public Long getAudIdUsuario() {
		return AudIdUsuario;
	}
	public void setAudIdUsuario(Long audIdUsuario) {
		AudIdUsuario = audIdUsuario;
	}
	public String getAudSesion() {
		return AudSesion;
	}
	public void setAudSesion(String audSesion) {
		AudSesion = audSesion;
	}
	public String getAudIP() {
		return AudIP;
	}
	public void setAudIP(String audIP) {
		AudIP = audIP;
	}
	public String getAudFecha() {
		return AudFecha;
	}
	public void setAudFecha(String audFecha) {
		AudFecha = audFecha;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
}
