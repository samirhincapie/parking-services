package co.com.ceiba.persistencia.entidad;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;

@Entity(name = "Carro")
@NamedQuery(name = "Carro.findByPlaca", query = "SELECT vehiculo FROM Vehiculo vehiculo WHERE vehiculo.placa = :placa")
public class CarroEntity extends VehiculoEntity {
	
}
