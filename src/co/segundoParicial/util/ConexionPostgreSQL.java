package co.segundoParicial.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConexionPostgreSQL {
	private Connection con = null;
	
	//para el patron singleton=>
	private static ConexionPostgreSQL db;
	
	private PreparedStatement preparedStatement;
	
	
	private static final String url= "jdbc:postgresql://fanny.db.elephantsql.com/";
	private static final String dbName = "jnvgnqqv";
	private static final String driver = "org.postgresql.Driver";
	private static final String userName= "jnvgnqqv";
	private static final String password= "aTo0Yykrx9nCmRavmYFsikv_usQtfOen";
	
	public ConexionPostgreSQL() {
		try
        {
            Class.forName(driver).newInstance();
             con = (Connection)DriverManager.getConnection(url+dbName,userName,password); 
        }catch(InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException  e){
            e.printStackTrace();
        }catch(Exception e) {
        	e.printStackTrace();
        }		
	}
	
	public void cerrarConexion() {
		try {
			con.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static ConexionPostgreSQL getConexion() {
		if(db == null) {
			db = new ConexionPostgreSQL(); 
		}
		return db;
	}
	
	//metodo de Consulta
	public ResultSet query() throws SQLException{
		ResultSet res = preparedStatement.executeQuery();
		return res;
	}
	//metodo de ejecucion
	public int execute() throws SQLException{
		int result = preparedStatement.executeUpdate();
		return result;
	}
	
	//metodo para obtener la conexion
	public Connection getCon() {
		return this.con;
	}
	
	//para inicializar el preparedStatement
	public PreparedStatement setPreparedStatement(String sql) throws SQLException{
		this.preparedStatement = con.prepareStatement(sql);
		return this.preparedStatement;
	}
}
