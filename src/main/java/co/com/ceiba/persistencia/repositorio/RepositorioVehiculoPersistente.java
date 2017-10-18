package co.com.ceiba.persistencia.repositorio;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import co.com.ceiba.model.Carro;
import co.com.ceiba.model.Moto;
import co.com.ceiba.model.Vehiculo;
import co.com.ceiba.persistencia.builder.CarroBuilder;
import co.com.ceiba.persistencia.builder.MotoBuilder;
import co.com.ceiba.persistencia.builder.VehiculoBuilder;
import co.com.ceiba.persistencia.entidad.CarroEntity;
import co.com.ceiba.persistencia.entidad.MotoEntity;
import co.com.ceiba.persistencia.entidad.VehiculoEntity;
import co.com.ceiba.persistencia.repositorio.jpa.RepositorioVehiculoJPA;
import co.com.ceiba.repositorio.RepositorioVehiculo;

public class RepositorioVehiculoPersistente implements RepositorioVehiculo, RepositorioVehiculoJPA {

	private static final String PLACA = "placa";
	private static final String VEHICULO_FIND_BY_PLACA = "Ingreso.findByPlaca";
	
	private EntityManager entityManager;

	public RepositorioVehiculoPersistente(EntityManager entityManager) {
		
		this.entityManager = entityManager;
	}

	@Override
	public VehiculoEntity obtenerVehiculoEntityPorPlaca(String placa) {
		Query query = entityManager.createNamedQuery(VEHICULO_FIND_BY_PLACA);
		query.setParameter(PLACA, placa);

		return (VehiculoEntity) query.getSingleResult();
	}

	@Override
	public Vehiculo obtenerVehiculoPorPlaca(String placa) {
		VehiculoEntity vehiculoEntity = obtenerVehiculoEntityPorPlaca(placa);
		VehiculoBuilder vehiculoBuilder = obtenerInstanciaVehiculoBuilder(vehiculoEntity);
		return vehiculoBuilder.convertirADominio(vehiculoEntity);
	}

	private VehiculoBuilder obtenerInstanciaVehiculoBuilder(VehiculoEntity vehiculoEntity) {
		if(CarroEntity.class.isInstance(vehiculoEntity.getClass())){
			return new CarroBuilder();
		}
		else if(MotoEntity.class.isInstance(vehiculoEntity.getClass())){
			return new MotoBuilder();
		}
		
		return null;
	}

	@Override
	public void agregar(Vehiculo vehiculo) {
		VehiculoBuilder vehiculoBuilder = obtenerInstanciaVehiculo(vehiculo);
		entityManager.persist(vehiculoBuilder.convertirAEntity(vehiculo));		
	}

	private VehiculoBuilder obtenerInstanciaVehiculo(Vehiculo vehiculo) {
		if(Carro.class.isInstance(vehiculo.getClass())){
			return new CarroBuilder();
		}
		else if(Moto.class.isInstance(vehiculo.getClass())){
			return new MotoBuilder();
		}
		
		return null;
	}

}
