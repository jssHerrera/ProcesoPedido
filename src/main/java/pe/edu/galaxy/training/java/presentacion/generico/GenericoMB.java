package pe.edu.galaxy.training.java.presentacion.generico;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pe.edu.galaxy.training.java.bean.generico.GenericoBean;
import pe.edu.galaxy.training.java.bean.seguridad.Usuario;
import pe.edu.galaxy.training.java.presentacion.constantes.Mensajes;
import pe.edu.galaxy.training.java.util.Net;

public class GenericoMB {

	public void addMessage(FacesMessage.Severity severity, String titulo, String msg) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, titulo, msg));
    }

	protected void msgInfo() {
		this.addMessage(FacesMessage.SEVERITY_INFO, Mensajes.MSG_TITULO_EXITO, Mensajes.MSG_MSG_EXITO);
	}
	
	protected void msgError() {
		this.addMessage(FacesMessage.SEVERITY_ERROR, Mensajes.MSG_TITULO_ERROR, Mensajes.MSG_MSG_ERROR);
	}
	
	protected void msgError(String msg) {
		this.addMessage(FacesMessage.SEVERITY_ERROR, Mensajes.MSG_TITULO_ERROR, msg);
	}
	
	protected void msgAlert(String msg) {
		this.addMessage(FacesMessage.SEVERITY_WARN, Mensajes.MSG_TITULO_ALERTA, msg);
	}
	
	protected void msgGrabar(boolean sw) {
		if (sw) {
			this.msgInfo();
		} else {
			this.msgError();
		}
	}

	protected void msgEliminar(Boolean sw) {
		if (sw) {
			this.addMessage(FacesMessage.SEVERITY_INFO, Mensajes.MSG_TITULO_EXITO, Mensajes.MSG_MSG_EXITO_ELIMINAR);
		} else {
			this.addMessage(FacesMessage.SEVERITY_ERROR, Mensajes.MSG_TITULO_ERROR, Mensajes.MSG_MSG_ERROR_ELIMINAR);
		}
	}
	
	protected HttpServletRequest getRequest(){	
		FacesContext fctx = FacesContext.getCurrentInstance();
		ExternalContext ectx = fctx.getExternalContext();
		HttpServletRequest request = (HttpServletRequest) ectx.getRequest();
		return request;
	}
	
	protected HttpServletResponse getResponse(){	
		FacesContext fctx = FacesContext.getCurrentInstance();
		ExternalContext ectx = fctx.getExternalContext();
		HttpServletResponse response = (HttpServletResponse) ectx.getResponse();
		return response;
	}
	
	
	protected void setAuditoria(GenericoBean ge){
		
		HttpSession session= this.getRequest().getSession();
		
		if (session!=null) {
			
			// Usuario
			Object obj=session.getAttribute("usuario");
			
			if (obj!=null) {
				Usuario oUsuario=(Usuario)obj;
				ge.setAudIdUsuario(oUsuario.getId());
			}else {
				System.out.println("obj is null");
			}
			
			// Sesion
			Object id=session.getAttribute("ID");
			if (id!=null) {
				String oID=(String)id;
				ge.setAudSesion(oID);
			}
		}
		// IP
		String ip= Net.getClientIPAddres(this.getRequest());
		ge.setAudIP(ip);
	}
}
