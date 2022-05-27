package co.segundoParcial.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.segundoParcial.modelo.Bill;
import co.segundoParcial.modelo.User;
import co.segundoParicial.util.ConexionPostgreSQL;




public class BillDao {
	
	
	private ConexionPostgreSQL conexion;
	
	private static final String INSERT_BILL_SQL = "INSERT INTO bill (date_bill, user_id, value, type, observation) VALUES (?, ?, ?, ?,?);";
	private static final String DELETE_BILL_SQL = "DELETE FROM bill WHERE id = ?;";
	private static final String UPDATE_BILL_SQL = "UPDATE bill SET  date_bill=?, user_id, value=?, type=?, observation  WHERE id=?;";
	private static final String SELECT_BILL_BY_ID = "SELECT * FROM bill WHERE user_id=?;";
	private static final String SELECT_ALL_BILLS = "SELECT * FROM bill;";

	public BillDao() {
		this.conexion =  ConexionPostgreSQL.getConexion();
	}
	
	
	public void insert(Bill bill) throws SQLException {
		try {
			PreparedStatement preparedStatement = (PreparedStatement) conexion.setPreparedStatement(INSERT_BILL_SQL);
			
			preparedStatement.setDate(1, (Date) bill.getDate_bill());
			preparedStatement.setInt(2, bill.getUser_id());
			preparedStatement.setDouble(3, bill.getValue());
			preparedStatement.setInt(4, bill.getType());
			preparedStatement.setString(5, bill.getObservation());
			conexion.execute();
		}catch(SQLException e) {
		}
	}

	public Bill select(int id){
		Bill bill = null;
		try {
			PreparedStatement preparedStatement = (PreparedStatement)conexion.setPreparedStatement(SELECT_BILL_BY_ID);
			preparedStatement.setInt(1, id);
			ResultSet rs = conexion.query();
			while(rs.next()){
				Date date = rs.getDate("date_bill");
				Integer user_id = rs.getInt("user_id");
				Double value = rs.getDouble("value");
				Integer type = rs.getInt("type");
				String observation = rs.getString("observation");
		
				bill = new Bill(id, date, user_id, value, type, observation);
			}
		}catch(SQLException e) {
			
		}	
		return bill;	
	}

	public List<Bill> selectAll(){
		List <Bill> bills = new ArrayList<>();
		try {
			PreparedStatement preparedStatement = (PreparedStatement)conexion.setPreparedStatement(SELECT_ALL_BILLS);
			ResultSet rs = conexion.query();
			
			while(rs.next()){
				Integer id = rs.getInt("id");
				Date date = rs.getDate("date_bill");
				Integer user_id = rs.getInt("user_id");
				Double value = rs.getDouble("value");
				Integer type = rs.getInt("type");
				String observation = rs.getString("observation");
				
				
				bills.add(new Bill(id,date, user_id, value, type, observation));
			}
		}catch(SQLException e) {
			
		}
		return bills;
	}

	public void delete(int id) throws SQLException {
		try {
		PreparedStatement preparedStatement = (PreparedStatement)conexion.setPreparedStatement(DELETE_BILL_SQL);
		preparedStatement.setInt(1, id);
		conexion.execute();
		}catch(SQLException e){
			
		}
	}

	public void update(Bill bill) throws SQLException {
		try {
			PreparedStatement preparedStatement = (PreparedStatement) conexion.setPreparedStatement(UPDATE_BILL_SQL);
			preparedStatement.setDate(1, (Date) bill.getDate_bill());
			preparedStatement.setInt(2, bill.getUser_id());
			preparedStatement.setDouble(3, bill.getValue());
			preparedStatement.setString(4, bill.getObservation());
			
			preparedStatement.setInt(5, bill.getId());

			conexion.execute();
		}catch(SQLException e) {
		}
	}

	

}
