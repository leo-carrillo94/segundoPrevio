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
	private String observacion;
	
	

}
