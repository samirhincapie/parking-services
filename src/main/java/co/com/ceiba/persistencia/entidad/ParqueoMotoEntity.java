package co.com.ceiba.persistencia.entidad;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;

@Entity(name = "ParqueoMoto")
@NamedQuery(name = "ParqueoMoto.findByPlaca", query = "SELECT parqueo FROM Parqueo parqueo WHERE parqueo.vehiculo.placa = :placa")
public class ParqueoMotoEntity extends ParqueoEntity {
	
}
