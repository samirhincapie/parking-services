package co.com.ceiba.persistencia.sistema;

import javax.persistence.EntityManager;

import co.com.ceiba.persistencia.conexion.ConexionJPA;
import co.com.ceiba.persistencia.repositorio.RepositorioParqueoPersistente;
import co.com.ceiba.persistencia.repositorio.RepositorioRegistroPersistente;
import co.com.ceiba.repositorio.RepositorioParqueo;
import co.com.ceiba.repositorio.RepositorioRegistro;
import co.com.ceiba.repositorio.RepositorioVehiculo;

public class SistemaDePersistencia {

	private EntityManager entityManager;

	public SistemaDePersistencia() {
		this.entityManager = new ConexionJPA().createEntityManager();
	}

	public RepositorioRegistro obtenerRepositorioIngreso() {
		return new RepositorioRegistroPersistente(entityManager, obtenerRepositorioVehiculo());
	}
	
	public RepositorioRegistro obtenerRepositorioSalida() {
		return new RepositorioRegistroPersistente(entityManager, obtenerRepositorioVehiculo());
	}
	
	public RepositorioParqueo obtenerRepositorioParqueo() {
		return new RepositorioParqueoPersistente(entityManager, obtenerRepositorioVehiculo());
	}
	
	public RepositorioVehiculo obtenerRepositorioVehiculo() {
		return new RepositorioVehiculoPersistente(entityManager);
	}

	public void iniciar() {
		entityManager.getTransaction().begin();
	}

	public void terminar() {
		entityManager.getTransaction().commit();
	}
}
