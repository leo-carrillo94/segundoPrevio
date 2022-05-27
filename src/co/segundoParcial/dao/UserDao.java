package co.segundoParcial.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.segundoParcial.modelo.User;
import co.segundoParicial.util.ConexionPostgreSQL;




public class UserDao {
	
	
	private ConexionPostgreSQL conexion;
	
	private static final String INSERT_USUARIO_SQL = "INSERT INTO user (id, username, pass, email) VALUES (?, ?, ?, ?);";
	private static final String DELETE_USUARIO_SQL = "DELETE FROM user WHERE id = ?;";
	private static final String UPDATE_USUARIO_SQL = "UPDATE user SET username=?, pass=?, email=?  WHERE id=?;";
	private static final String SELECT_USUARIO_BY_ID = "SELECT * FROM user WHERE id=?;";
	private static final String SELECT_ALL_USUARIOS = "SELECT * FROM user;";
	private static final String CONSULTA_LOGIN = "SELECT * FROM user WHERE (username=?) AND (pass=?)";

	public UserDao() {
		this.conexion =  ConexionPostgreSQL.getConexion();
	}
	
	
	public void insert(User user) throws SQLException {
		try {
			PreparedStatement preparedStatement = (PreparedStatement) conexion.setPreparedStatement(INSERT_USUARIO_SQL);
			preparedStatement.setInt(1, user.getId());
			preparedStatement.setString(2, user.getUsername());
			preparedStatement.setString(3, user.getPass());
			preparedStatement.setString(4, user.getEmail());
			conexion.execute();
		}catch(SQLException e) {
		}
	}

	public User select(int id){
		User user = null;
		try {
			PreparedStatement preparedStatement = (PreparedStatement)conexion.setPreparedStatement(SELECT_USUARIO_BY_ID);
			preparedStatement.setInt(1, id);
			ResultSet rs = conexion.query();
			while(rs.next()){
				String username = rs.getString("username");
				String pass = rs.getString("pass");
				String email = rs.getString("email");
				user = new User(id, username, pass, email);
			}
		}catch(SQLException e) {
			
		}	
		return user;	
	}

	public List<User> selectAll(){
		List <User> users = new ArrayList<>();
		try {
			PreparedStatement preparedStatement = (PreparedStatement)conexion.setPreparedStatement(SELECT_ALL_USUARIOS);
			ResultSet rs = conexion.query();
			
			while(rs.next()){
				Integer id = rs.getInt("id");
				String username = rs.getString("username");
				String pass = rs.getString("pass");
				String email = rs.getString("email");
				users.add(new User(id, username, pass, email));
			}
		}catch(SQLException e) {
			
		}
		return users;
	}

	public void delete(int id) throws SQLException {
		try {
		PreparedStatement preparedStatement = (PreparedStatement)conexion.setPreparedStatement(DELETE_USUARIO_SQL);
		preparedStatement.setInt(1, id);
		conexion.execute();
		}catch(SQLException e){
			
		}
	}

	public void update(User user) throws SQLException {
		try {
			PreparedStatement preparedStatement = (PreparedStatement) conexion.setPreparedStatement(UPDATE_USUARIO_SQL);
			preparedStatement.setString(1, user.getUsername());
			preparedStatement.setString(2, user.getPass());
			preparedStatement.setString(3, user.getEmail());
			preparedStatement.setInt(4, user.getId());
			conexion.execute();
		}catch(SQLException e) {
		}
	}
	
	//CONSULTA PARA EL LOGIN
	
	public User consultarUsuario(String username, String pass) {
		User user = null;
		try {
			PreparedStatement preparedStatement = (PreparedStatement) conexion.setPreparedStatement(CONSULTA_LOGIN);
			preparedStatement.setString(1, username);
			preparedStatement.setString(1, pass);
			ResultSet rs = conexion.query();
			
			if(rs.next() == true) {
				while(rs.next()){
					
					Integer id = rs.getInt("id");
					String email = rs.getString("email");
					user = new User(id, username, pass, email);
					
				}
				
			}			
		}catch(SQLException e) {
			
		}
		return user;
	}

	

}
