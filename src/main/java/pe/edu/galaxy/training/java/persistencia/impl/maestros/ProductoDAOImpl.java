package pe.edu.galaxy.training.java.persistencia.impl.maestros;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import pe.edu.galaxy.training.java.bean.maestros.Producto;
import pe.edu.galaxy.training.java.persistencia.PersistenciaException;
import pe.edu.galaxy.training.java.persistencia.inf.maestros.ProductoDAO;
import pe.edu.galaxy.training.java.persistencia.util.BD;

public class ProductoDAOImpl implements ProductoDAO{

	@Override
	public List<Producto> listar(Producto producto) throws PersistenciaException {

		try {
	
			Connection cn= BD.getConnection();
			
			String sql="SELECT PRODUCTO_ID, NOMBRE, PRECIO, STOCK FROM PRODUCTO"
					+ " WHERE UPPER(NOMBRE) LIKE ? AND ESTADO='1'";
			
			String nombre=producto.getNombre()==null?"":producto.getNombre().trim();
			
			PreparedStatement ps= cn.prepareStatement(sql);
			ps.setString(1,"%"+nombre.toUpperCase()+"%" );

			ResultSet rs= ps.executeQuery();
			
			List<Producto>  lst= new ArrayList<Producto>();
			//CategoriaDAO categoriaDAO= new CategoriaDAOImpl();
			while (rs.next()) {
				
				Producto oProducto= new Producto();
				oProducto.setId(rs.getLong("PRODUCTO_ID"));
				oProducto.setNombre(rs.getString("NOMBRE"));
				oProducto.setPrecio(rs.getDouble("PRECIO"));
				oProducto.setStock(rs.getInt("STOCK"));
				
				//Long idCategoria= rs.getInt("CATEGORIA_ID");
				//Categoria categoria= categoriaDAO.findById(idCategoria);
				//oProducto.setCategoria(categoria);
				/*
		         <p:column headerText="Categoria">
		            <h:outputText value="#{producto.categoria.nombre}" />
		        </p:column>
		        */
				
				lst.add(oProducto);
				
			}
			
			rs.close();
			ps.close();
			cn.close();
			
			return lst;
			
		} catch (Exception e) {
			System.err.println("Error al listar" + e.getMessage());
			return null;
		}
		
		
	
	}

	@Override
	public Producto buscarXId(Long id) throws PersistenciaException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insertar(Producto producto) throws PersistenciaException {
		
		try {
		
			Connection cn= BD.getConnection();
			
			String sql="INSERT INTO PRODUCTO (PRODUCTO_ID, NOMBRE, PRECIO, STOCK,"
					+ "AUD_TIPO,AUD_IDUSUARIO,AUD_SESION,AUD_IP,AUD_FECHA)" + 
					"VALUES(SEQ_PRODUCTO.NEXTVAL, ?,?,?,"
					+ "'I',?,?,?,SYSDATE)";
			
			PreparedStatement ps= cn.prepareStatement(sql);
			
			ps.setString(1, producto.getNombre());
			ps.setDouble(2, producto.getPrecio());
			ps.setInt(3, producto.getStock());
			
			ps.setLong(4, producto.getAudIdUsuario());
			ps.setString(5, producto.getAudSesion());
			ps.setString(6, producto.getAudIP());
			
			ps.executeUpdate();

			ps.close();
			cn.close();
			
			return true;
		} catch (Exception e) {
			System.err.println("Error al insertar" + e.getMessage());
			return false;
		}
		
	}

	@Override
	public boolean actualizar(Producto producto) throws PersistenciaException {
		
		try {
		
			Connection cn= BD.getConnection();
			
			String sql="UPDATE PRODUCTO SET NOMBRE=?, "
					+ " PRECIO=?, STOCK=?, "
					+ " AUD_TIPO='A', AUD_IDUSUARIO=?, AUD_SESION=?,AUD_IP=?, AUD_FECHA=SYSDATE"
					+ " WHERE PRODUCTO_ID=?" ;
			
			PreparedStatement ps= cn.prepareStatement(sql);
			
			ps.setString(1, producto.getNombre());
			ps.setDouble(2, producto.getPrecio());
			ps.setInt(3, producto.getStock());
			
			ps.setLong(4, producto.getAudIdUsuario());
			ps.setString(5, producto.getAudSesion());
			ps.setString(6, producto.getAudIP());
			
			ps.setLong(7, producto.getId());
			
			ps.executeUpdate();

			ps.close();
			cn.close();
			
			return true;
		} catch (Exception e) {
			System.err.println("Error al actualizar" + e.getMessage());
			return false;
		}
		
	}

	@Override
	public boolean eliminar(Producto producto) throws PersistenciaException {
		
		try {
		
			Connection cn= BD.getConnection();
			
			String sql="UPDATE PRODUCTO SET ESTADO='0',"
					+ " AUD_TIPO='E', AUD_IDUSUARIO=?, AUD_SESION=?,AUD_IP=?, AUD_FECHA=SYSDATE"
					+ " WHERE PRODUCTO_ID=?" ;
			
			PreparedStatement ps= cn.prepareStatement(sql);
			
			ps.setLong(1, producto.getAudIdUsuario());
			ps.setString(2, producto.getAudSesion());
			ps.setString(3, producto.getAudIP());
			
			ps.setLong(4, producto.getId());
			
			ps.executeUpdate();

			ps.close();
			cn.close();
			
			return true;
		} catch (Exception e) {
			System.err.println("Error al eliminar" + e.getMessage());
			return false;
		}
		
	}

	
}
