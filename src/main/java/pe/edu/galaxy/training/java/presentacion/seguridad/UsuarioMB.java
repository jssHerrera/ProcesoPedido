package pe.edu.galaxy.training.java.presentacion.seguridad;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import pe.edu.galaxy.training.java.bean.seguridad.Usuario;
import pe.edu.galaxy.training.java.presentacion.generico.GenericoMB;
import pe.edu.galaxy.training.java.service.ServicioException;
import pe.edu.galaxy.training.java.service.impl.seguridad.UsuarioServiceImpl;
import pe.edu.galaxy.training.java.service.inf.seguridad.UsuarioService;
import pe.edu.galaxy.training.java.util.Encrypt;

@ManagedBean(name = "usuarioMB")
@SessionScoped
public class UsuarioMB extends GenericoMB{

	private UsuarioService usuarioService;
	
	private List<Usuario> usuarios;

	private Usuario usuario;
	
	private Usuario usuarioSeleccionado;

	public UsuarioMB() {
		this.setUsuario(new Usuario());
		this.usuarioService= new UsuarioServiceImpl();
		this.listar();
	}

	public void listar() {

		try {
			setUsuarios(usuarioService.listar(this.getUsuario()));
		} catch (Exception e) {
			System.err.println(e);
		}

	}
	
	public void seleccionar(Usuario usuario) {
		this.usuarioSeleccionado=usuario;
	}
	
	public void eliminar() {
		
		Boolean sw=false;
		
		try {
			
			super.setAuditoria(usuarioSeleccionado);
			
			sw=usuarioService.eliminar(usuarioSeleccionado);
			
			super.msgEliminar(sw);
			if (sw) {
				this.listar();
			}
		} catch (ServicioException e) {
			super.msgError();
		}

	}

	public String modificar(Usuario usuario) {
		this.setUsuario(usuario);
		return "usuario_registro";
	}

	public String nuevo() {
		this.setUsuario(new Usuario());
		return "usuario_registro";
	}
	
	public void limpiar() {
		this.setUsuario(new Usuario());
		this.listar();
	}

	public String cancelar() {
		this.setUsuario(new Usuario());
		return "usuario_listado";
	}

	public void grabar() {
		Boolean sw=false;
		
		if (!this.validar()) {
			return;
		}
	
		try {

			// Registar los datos de auditoria
			
			super.setAuditoria(usuario);
			
			String claveEncrypt= Encrypt.encrypt(usuario.getClave());
			usuario.setClave(claveEncrypt);
			
			if (this.getUsuario().getId() > 0) {
				sw=usuarioService.actualizar(usuario);
			} else {
				
				sw=usuarioService.insertar(usuario);
			}
			super.msgGrabar(sw);

		} catch (ServicioException e) {
			super.msgError();
			System.err.println(e);
		}

	}
	
	private boolean validar() {
		String nombre=this.getUsuario().getNombre();
		if (!(nombre!=null && nombre.trim().length()>5)) {
			super.msgAlert("El nombre es requerido y debe tener mínimo 5 caracteres");
			return false;
		}
		
		String usuario=this.getUsuario().getUsuario();
		if (!(usuario!=null && nombre.trim().length()>3)) {
			super.msgAlert("El usuario es requerido y debe tener mínimo 3 caracteres");
			return false;
		}
		return true;
	}
	
	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Usuario getUsuarioSeleccionado() {
		return usuarioSeleccionado;
	}

	public void setUsuarioSeleccionado(Usuario usuarioSeleccionado) {
		this.usuarioSeleccionado = usuarioSeleccionado;
	}
	
	
}
