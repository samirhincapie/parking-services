package co.com.ceiba.persistencia.sistema;

import javax.persistence.EntityManager;

import dominio.repositorio.RepositorioLibro;
import dominio.repositorio.RepositorioPrestamo;
import persistencia.conexion.ConexionJPA;
import persistencia.repositorio.RepositorioLibroPersistente;
import persistencia.repositorio.RepositorioPrestamoPersistente;

public class SistemaDePersistencia {

	private EntityManager entityManager;

	public SistemaDePersistencia() {
		this.entityManager = new ConexionJPA().createEntityManager();
	}

	public RepositorioIngreso obtenerRepositorioIngreso() {
		return new RepositorioIngresoPersistente(entityManager);
	}
	
	public RepositorioSalida obtenerRepositorioSalida() {
		return new RepositorioSalidaPersistente(entityManager);
	}
	
	public RepositorioParqueo obtenerRepositorioParqueo() {
		return new RepositorioParqueoPersistente(entityManager);
	}

	public void iniciar() {
		entityManager.getTransaction().begin();
	}

	public void terminar() {
		entityManager.getTransaction().commit();
	}
}
