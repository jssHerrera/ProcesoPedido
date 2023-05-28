package pe.edu.galaxy.training.java.persistencia.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class BD {

	private static String url = "jdbc:oracle:thin:@localhost:1521:XE";
	private static String driver = "oracle.jdbc.OracleDriver";
	private static String usuario = "PJM2040423V2";
	private static String clave = "123456";

	// Cargar Driver
	
	private  static boolean cargarDriver() {
		try {
			Class.forName(driver);
			//System.out.println("Exito al cargar el driver");
			return true;
		} catch (Exception e) {
			System.err.println("Error al cargar el driver " + e.getMessage());
			return false;
		}
	}

	public static boolean cargarDriver(String driver) {
		BD.setDriver(driver);
		return cargarDriver();
	}
	
	public static Connection getConnection() {
		if (!cargarDriver()) {
			return null;
		}
		try {
			Connection cn = DriverManager.getConnection(url, usuario, clave);
			//System.out.println("Exito al conectar DB");
			return cn;
		} catch (Exception e) {
			System.err.println("Error al conectar DB" + e.getMessage());
			return null;
		}
	}

	public static Connection getConnection(String url, String usuario, String clave) {
		
		BD.setUrl(url);
		BD.setUsuario(usuario);
		BD.setClave(clave);
		
		return getConnection();
	}
	
	public static String getUrl() {
		return url;
	}

	public static void setUrl(String url) {
		BD.url = url;
	}

	public static String getDriver() {
		return driver;
	}

	public static void setDriver(String driver) {
		BD.driver = driver;
	}

	public static String getUsuario() {
		return usuario;
	}

	public static void setUsuario(String usuario) {
		BD.usuario = usuario;
	}

	public static String getClave() {
		return clave;
	}

	public static void setClave(String clave) {
		BD.clave = clave;
	}
	
	
}
