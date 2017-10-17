package co.com.ceiba.persistencia.entidad;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;

@Entity(name = "ParqueoCarro")
@NamedQuery(name = "ParqueoCarro.findByPlaca", query = "SELECT parqueo FROM Parqueo parqueo WHERE parqueo.vehiculo.placa = :placa")
public class ParqueoCarroEntity extends ParqueoEntity {
	
}
