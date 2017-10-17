package co.com.ceiba.persistencia.repositorio;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import co.com.ceiba.model.Parqueo;
import co.com.ceiba.persistencia.builder.ParqueoBuilder;
import co.com.ceiba.persistencia.entidad.ParqueoEntity;
import co.com.ceiba.persistencia.repositorio.jpa.RepositorioVehiculoJPA;
import co.com.ceiba.repositorio.RepositorioParqueo;
import co.com.ceiba.repositorio.RepositorioVehiculo;

public class RepositorioParqueoPersistente implements RepositorioParqueo {

	private static final String PLACA = "placa";
	private static final String PARQUEO_FIND_BY_PLACA = "Parqueo.findByPlaca";
	
	private EntityManager entityManager;
	private RepositorioVehiculoJPA repositorioVehiculoJPA;

	public RepositorioParqueoPersistente(EntityManager entityManager, RepositorioVehiculo repositorioVehiculo) {
		
		this.entityManager = entityManager;
		this.repositorioVehiculoJPA = (RepositorioVehiculoJPA) repositorioVehiculo;
	}

	@Override
	public Parqueo obtenerParqueoPorPlaca(String placa) {

		ParqueoEntity parqueoEntity = obtenerParqueoEntityPorPlaca(placa);

		return ParqueoBuilder.convertirADominio(parqueoEntity);
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
		
		entityManager.persist(ParqueoBuilder.convertirAEntity(parqueo));
	}

}
