package pe.edu.galaxy.training.java.presentacion.filter;

import java.io.IOException;
import java.util.Date;

import javax.faces.application.ResourceHandler;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pe.edu.galaxy.training.java.bean.seguridad.Usuario;

@WebFilter("/*")
public class LoginFilter implements Filter, ServletContextListener {


	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws ServletException, IOException {

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;

		HttpSession session = request.getSession(false);
		
		//System.out.println("doFilter..."+ new Date());

	    Usuario usuario = (session != null) ? (Usuario) session.getAttribute("usuario") : null;
		
	    // Excepciones
	    
	   String loginURL = request.getContextPath()+ "/faces/login.xhtml";
	   //String loginURL = request.getContextPath()+ "/default.jsp";
	   String resourcesPropios = request.getContextPath()+ "/resources";
	   String resourcesPF=request.getContextPath() +"/faces"+ ResourceHandler.RESOURCE_IDENTIFIER;
	   
		boolean loginRequest = request.getRequestURI().startsWith(loginURL);
		boolean resourcesPropiosRequest = request.getRequestURI().startsWith(resourcesPropios);
		boolean resourcesPFRequest = request.getRequestURI().startsWith(resourcesPF);
		
		if (usuario != null || loginRequest || resourcesPropiosRequest||resourcesPFRequest) {
				chain.doFilter(request, response);// Quedate donde estas
		} else {

			response.sendRedirect(loginURL);
		}

	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {

	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {

	}

}
