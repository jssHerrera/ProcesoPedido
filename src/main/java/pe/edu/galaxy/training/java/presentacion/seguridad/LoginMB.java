package pe.edu.galaxy.training.java.presentacion.seguridad;

import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import pe.edu.galaxy.training.java.bean.seguridad.Usuario;
import pe.edu.galaxy.training.java.presentacion.generico.GenericoMB;
import pe.edu.galaxy.training.java.service.impl.seguridad.UsuarioServiceImpl;
import pe.edu.galaxy.training.java.service.inf.seguridad.UsuarioService;
import pe.edu.galaxy.training.java.util.Encrypt;

@ManagedBean(name = "loginMB")
@SessionScoped
public class LoginMB extends GenericoMB{

	private Usuario usuario;
	
	private UsuarioService usuarioService;
	
	public LoginMB() {
		this.setUsuario(new Usuario());
		usuarioService= new UsuarioServiceImpl();
		this.getUsuario().setUsuario("ANOVOA");
		this.getUsuario().setClave("123");
	}

	public String validarAcceso() {
		
		try {
			// 123->069e47397a2921d15be267864b7a491cffe240ef3a52daf226e83b2a6ac2748c
			
			//System.out.println("Clave -> "+usuario.getClave());
			
			String claveEncrypt= Encrypt.encrypt(usuario.getClave());
			
			//System.out.println("Clave encriptada-> "+claveEncrypt);
			
			usuario.setClave(claveEncrypt);
			
			Usuario oUsuario= usuarioService.validarAcceso(usuario);
			
			
			if (oUsuario!=null) {
				
				HttpSession session = super.getRequest().getSession(true);
				
				System.out.println("session.getId() "+session.getId());
				
				session.setAttribute("ID", session.getId());
				
				//System.out.println("oUsuario "+oUsuario);
				
				session.setAttribute("usuario", oUsuario);
				
				return "panel";
				
			}else {
				super.msgAlert("Error de usuario y/o clave, intente nuevamente");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "login"; // login.xhtml
		
	}
	
	public void cerrarSesionx() {
		try {
			HttpSession session = super.getRequest().getSession();
			session.removeAttribute("ID");
			session.removeAttribute("usuario");
			session.invalidate();
			ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
			try {
			    ec.redirect(ec.getRequestContextPath()+ "/faces/seguridad/login.xhtml");
			} catch (IOException e) {
			    e.printStackTrace();
			}
		} catch (Exception e) {
			System.out.println("exception cerrar Sesion");
		}
	}
	
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
}
