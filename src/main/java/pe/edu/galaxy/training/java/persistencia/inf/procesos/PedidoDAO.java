package pe.edu.galaxy.training.java.persistencia.inf.procesos;

import java.sql.Connection;

import pe.edu.galaxy.training.java.bean.procesos.PedidoCabecera;
import pe.edu.galaxy.training.java.bean.procesos.PedidoDetalle;
import pe.edu.galaxy.training.java.persistencia.PersistenciaException;
import pe.edu.galaxy.training.java.persistencia.inf.generico.GenericoDAO;

public interface PedidoDAO extends GenericoDAO<PedidoCabecera>{

	// Cabecera
	//boolean insertarCabecera(PedidoCabecera pedidoCabecera) throws PersistenciaException; 
	
	boolean insertarCabecera(PedidoCabecera pedidoCabecera, Connection cn) throws PersistenciaException; 
	
	// Detalle
	
	//boolean insertarDetalle(PedidoDetalle pedidoDetalle) throws PersistenciaException;
	boolean insertarDetalle(PedidoDetalle pedidoDetalle, Connection cn) throws PersistenciaException; 
}
