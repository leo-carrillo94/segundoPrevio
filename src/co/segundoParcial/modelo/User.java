package co.segundoParcial.modelo;

import java.io.Serializable;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //metodos SET Y GET
@NoArgsConstructor //constructor vacio
@AllArgsConstructor //constructor completo
public class User implements Serializable {

	private Integer id;
	private String username;
	private String pass;
	private String email;

}
