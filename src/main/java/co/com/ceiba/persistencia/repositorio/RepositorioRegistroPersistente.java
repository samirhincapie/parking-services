package co.com.ceiba.persistencia.repositorio;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import co.com.ceiba.model.Ingreso;
import co.com.ceiba.model.Salida;
import co.com.ceiba.persistencia.builder.IIngresoBuilder;
import co.com.ceiba.persistencia.builder.IngresoCarroBuilder;
import co.com.ceiba.persistencia.builder.IngresoMotoBuilder;
import co.com.ceiba.persistencia.entidad.CarroEntity;
import co.com.ceiba.persistencia.entidad.IngresoEntity;
import co.com.ceiba.persistencia.entidad.MotoEntity;
import co.com.ceiba.persistencia.entidad.SalidaEntity;
import co.com.ceiba.persistencia.entidad.VehiculoEntity;
import co.com.ceiba.persistencia.repositorio.jpa.RepositorioVehiculoJPA;
import co.com.ceiba.repositorio.RepositorioRegistro;
import co.com.ceiba.repositorio.RepositorioVehiculo;

public class RepositorioRegistroPersistente implements RepositorioRegistro {

	private static final String PLACA = "placa";
	private static final String INGRESO_FIND_BY_PLACA = "Ingreso.findByPlaca";
	
	private EntityManager entityManager;
	private RepositorioVehiculoJPA repositorioVehiculoJPA;

	public RepositorioRegistroPersistente(EntityManager entityManager, RepositorioVehiculo repositorioVehiculo) {
		
		this.entityManager = entityManager;
		this.repositorioVehiculoJPA = (RepositorioVehiculoJPA) repositorioVehiculo;
	}

	@Override
	public Ingreso obtenerIngresoPorPlaca(String placa) {
		IngresoEntity ingresoEntity = obtenerIngresoEntityPorPlaca(placa);
		
		IIngresoBuilder ingresoBuilder = obtenerInstanciaIngresoBuilder(ingresoEntity.getVehiculo());
		
		return ingresoBuilder.convertirADominio(ingresoEntity);
	}

	private IIngresoBuilder obtenerInstanciaIngresoBuilder(VehiculoEntity vehiculoEntity) {
		if(CarroEntity.class.isInstance(vehiculoEntity.getClass())){
			return new IngresoCarroBuilder();
		}
		else if(MotoEntity.class.isInstance(vehiculoEntity.getClass())){
			return new IngresoMotoBuilder();
		}
		
		return null;
	}

	@SuppressWarnings("rawtypes")
	private IngresoEntity obtenerIngresoEntityPorPlaca(String placa) {

		Query query = entityManager.createNamedQuery(INGRESO_FIND_BY_PLACA);
		query.setParameter(PLACA, placa);

		List resultList = query.getResultList();

		return !resultList.isEmpty() ? (IngresoEntity) resultList.get(0) : null;
	}

	@Override
	public void agregar(Ingreso ingreso) {

		IngresoEntity ingresoEntity = buildIngresoEntity(ingreso);

		entityManager.persist(ingresoEntity);
	}

	private IngresoEntity buildIngresoEntity(Ingreso ingreso) {

		VehiculoEntity vehiculoEntity = this.repositorioVehiculoJPA.obtenerVehiculoEntityPorPlaca(ingreso.getVehiculo().getPlaca());

		IngresoEntity ingresoEntity = new IngresoEntity();
		ingresoEntity.setVehiculo(vehiculoEntity);
		ingresoEntity.setFecha(ingreso.getFecha());

		return ingresoEntity;
	}

	@Override
	public void agregar(Salida salida) {
		SalidaEntity salidaEntity = buildSalidaEntity(salida);

		entityManager.persist(salidaEntity);		
	}

	private SalidaEntity buildSalidaEntity(Salida salida) {

		VehiculoEntity vehiculoEntity = this.repositorioVehiculoJPA.obtenerVehiculoEntityPorPlaca(salida.getVehiculo().getPlaca());

		SalidaEntity salidaEntity = new SalidaEntity();
		salidaEntity.setVehiculo(vehiculoEntity);
		salidaEntity.setFecha(salida.getFecha());

		return salidaEntity;
	}

}
