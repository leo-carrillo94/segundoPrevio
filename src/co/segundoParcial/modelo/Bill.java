package co.segundoParcial.modelo;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //metodos SET Y GET
@NoArgsConstructor //constructor vacio
@AllArgsConstructor //constructor completo
public class Bill implements Serializable {

	private Integer id;
	private Date date_bill;
	private Integer user_id;
	private Double value;
	private Integer type;
	private String observation;
	
	
	public Bill(Date date_bill, Integer user_id, Double value, Integer type, String observation) {
		this.date_bill = date_bill;
		this.user_id = user_id;
		this.value = value;
		this.type = type;
		this.observation = observation;
	}
	
	
	
	
	

}
