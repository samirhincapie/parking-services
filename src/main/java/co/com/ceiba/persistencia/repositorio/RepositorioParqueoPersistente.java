package co.com.ceiba.persistencia.repositorio;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import co.com.ceiba.model.Parqueo;
import co.com.ceiba.persistencia.builder.ParqueoBuilder;
import co.com.ceiba.persistencia.entidad.ParqueoEntity;
import co.com.ceiba.persistencia.repositorio.jpa.RepositorioParqueoJPA;
import co.com.ceiba.repositorio.RepositorioParqueo;
import dominio.Libro;
import dominio.repositorio.RepositorioLibro;
import persistencia.builder.LibroBuilder;
import persistencia.entitad.LibroEntity;
import persistencia.repositorio.jpa.RepositorioLibroJPA;

public class RepositorioParqueoPersistente implements RepositorioParqueo, RepositorioParqueoJPA {

	private static final String PLACA = "placa";
	private static final String PARQUEO_FIND_BY_PLACA = "Parqueo.findByPlaca";
	
	private EntityManager entityManager;

	public RepositorioParqueoPersistente(EntityManager entityManager) {
		
		this.entityManager = entityManager;
	}	

	@Override
	public Parqueo obtenerPorPlaca(String placa) {
		
		ParqueoEntity parqueoEntity = obtenerParqueoEntityPorPlaca(placa);

		return ParqueoBuilder.convertirADominio(parqueoEntity);
	}

	@Override
	public void agregar(Parqueo parqueo) {
		
		entityManager.persist(ParqueoBuilder.convertirAEntity(parqueo));
	}

	@Override
	public ParqueoEntity obtenerParqueoEntityPorPlaca(String placa) {
		
		Query query = entityManager.createNamedQuery(PARQUEO_FIND_BY_PLACA);
		query.setParameter(PLACA, placa);

		return (ParqueoEntity) query.getSingleResult();
	}

}
