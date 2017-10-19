package co.com.ceiba.persistencia.repositorio;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import co.com.ceiba.model.Parqueo;
import co.com.ceiba.model.RepositorioPersistenteException;
import co.com.ceiba.persistencia.builder.IParqueoBuilder;
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
	private static final String PARQUEO_REMOVE_BY_PLACA = "Parqueo.removeByPlaca";
	
	private EntityManager entityManager;
	private RepositorioVehiculoJPA repositorioVehiculoJPA;

	public RepositorioParqueoPersistente(EntityManager entityManager, RepositorioVehiculo repositorioVehiculo) {
		
		this.entityManager = entityManager;
		this.repositorioVehiculoJPA = repositorioVehiculo.getRepositorioVehiculoJPA();
	}

	@Override
	public Parqueo obtenerParqueoPorPlaca(String placa) throws RepositorioPersistenteException {
		ParqueoEntity parqueoEntity = obtenerParqueoEntityPorPlaca(placa);
		
		IParqueoBuilder parqueoBuilder = obtenerInstanciaParqueoBuilder(parqueoEntity.getVehiculo());
		
		return parqueoBuilder.convertirADominio(parqueoEntity);
	}

	private IParqueoBuilder obtenerInstanciaParqueoBuilder(VehiculoEntity vehiculoEntity) throws RepositorioPersistenteException {
		if(CarroEntity.class.isInstance(vehiculoEntity.getClass())){
			return new ParqueoCarroBuilder();
		}
		else if(MotoEntity.class.isInstance(vehiculoEntity.getClass())){
			return new ParqueoMotoBuilder();
		}
		
		throw new RepositorioPersistenteException(RepositorioPersistenteException.VEHICULO_ENTITY_DESCONOCIDO);
	}

	@SuppressWarnings("rawtypes")
	private ParqueoEntity obtenerParqueoEntityPorPlaca(String placa) {

		Query query = entityManager.createNamedQuery(PARQUEO_FIND_BY_PLACA);
		query.setParameter(PLACA, placa);

		List resultList = query.getResultList();

		return !resultList.isEmpty() ? (ParqueoEntity) resultList.get(0) : new ParqueoEntity();
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

		List<?> resultList = query.getResultList();

		return !resultList.isEmpty() ? (List<Parqueo>) resultList : null;
	}

	@Override
	public void liberar(String placa) {
		Query query = entityManager.createNamedQuery(PARQUEO_REMOVE_BY_PLACA);
		
		query.executeUpdate();
		
	}

}
