package pe.edu.galaxy.training.java.persistencia.util;

public class QueryUtil {

	public static String getLike(String str) {
		
		if (str==null) {
			str="";
		}
		return "%"+str+"%";
	}
}
