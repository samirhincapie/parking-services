package co.com.ceiba.persistencia.repositorio;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import co.com.ceiba.model.Parqueo;
import co.com.ceiba.persistencia.builder.ParqueoBuilder;
import co.com.ceiba.persistencia.builder.ParqueoCarroBuilder;
import co.com.ceiba.persistencia.builder.ParqueoMotoBuilder;
import co.com.ceiba.persistencia.entidad.CarroEntity;
import co.com.ceiba.persistencia.entidad.MotoEntity;
import co.com.ceiba.persistencia.entidad.ParqueoEntity;
import co.com.ceiba.persistencia.entidad.VehiculoEntity;
import co.com.ceiba.persistencia.repositorio.jpa.RepositorioVehiculoJPA;
import co.com.ceiba.repositorio.RepositorioParqueo;
import co.com.ceiba.repositorio.RepositorioVehiculo;

public class RepositorioParqueoPersistente implements RepositorioParqueo {

	private static final String PLACA = "placa";
	private static final String PARQUEO_FIND_BY_PLACA = "Parqueo.findByPlaca";
	private static final String PARQUEO_LIST = "Parqueo.list";
	
	private EntityManager entityManager;
	private RepositorioVehiculoJPA repositorioVehiculoJPA;

	public RepositorioParqueoPersistente(EntityManager entityManager, RepositorioVehiculo repositorioVehiculo) {
		
		this.entityManager = entityManager;
		this.repositorioVehiculoJPA = (RepositorioVehiculoJPA) repositorioVehiculo;
	}

	@Override
	public Parqueo obtenerParqueoPorPlaca(String placa) {
		ParqueoEntity parqueoEntity = obtenerParqueoEntityPorPlaca(placa);
		
		ParqueoBuilder parqueoBuilder = obtenerInstanciaParqueoBuilder(parqueoEntity.getVehiculo());
		
		return parqueoBuilder.convertirADominio(parqueoEntity);
	}

	private ParqueoBuilder obtenerInstanciaParqueoBuilder(VehiculoEntity vehiculo) {
		if(CarroEntity.class.isInstance(vehiculo.getClass())){
			return new ParqueoCarroBuilder();
		}
		else if(MotoEntity.class.isInstance(vehiculo.getClass())){
			return new ParqueoMotoBuilder();
		}
		
		return null;
	}

	@SuppressWarnings("rawtypes")
	private ParqueoEntity obtenerParqueoEntityPorPlaca(String placa) {

		Query query = entityManager.createNamedQuery(PARQUEO_FIND_BY_PLACA);
		query.setParameter(PLACA, placa);

		List resultList = query.getResultList();

		return !resultList.isEmpty() ? (ParqueoEntity) resultList.get(0) : null;
	}

	@Override
	public void agregar(Parqueo parqueo) {

		ParqueoEntity parqueoEntity = buildParqueoEntity(parqueo);

		entityManager.persist(parqueoEntity);
	}

	private ParqueoEntity buildParqueoEntity(Parqueo parqueo) {

		VehiculoEntity vehiculoEntity = this.repositorioVehiculoJPA.obtenerVehiculoEntityPorPlaca(parqueo.getVehiculo().getPlaca());

		ParqueoEntity parqueoEntity = new ParqueoEntity();
		parqueoEntity.setVehiculo(vehiculoEntity);
		parqueoEntity.setValorAdicional(parqueo.getValorAdicional());
		parqueoEntity.setValorDia(parqueo.getValorDia());
		parqueoEntity.setValorHora(parqueo.getValorHora());

		return parqueoEntity;
	}

	@Override
	public List<Parqueo> obtenerParqueos() {

		Query query = entityManager.createNamedQuery(PARQUEO_LIST);

		List resultList = query.getResultList();

		return !resultList.isEmpty() ? (List<Parqueo>) resultList : null;
	}

}
