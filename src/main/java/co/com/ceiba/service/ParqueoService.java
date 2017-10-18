package co.com.ceiba.service;

import java.util.List;

import org.springframework.stereotype.Component;

import co.com.ceiba.model.Parqueo;
import co.com.ceiba.persistencia.sistema.SistemaDePersistencia;
import co.com.ceiba.repositorio.RepositorioParqueo;

@Component
public class ParqueoService {
	
	private SistemaDePersistencia sistemaDePersistencia;
	
	private RepositorioParqueo repositorioParqueo;
	
	public ParqueoService(SistemaDePersistencia sistemaDePersistencia){
		this.sistemaDePersistencia = sistemaDePersistencia;
		
		this.repositorioParqueo = this.sistemaDePersistencia.obtenerRepositorioParqueo();
		
	}

	public List<Parqueo> listarParqueos() {
		return this.repositorioParqueo.obtenerParqueos();
	}

	public void liberarParqueo(String placa) {
		this.repositorioParqueo.liberar(placa);
	}

	public Parqueo consultarParqueo(String placa) {
		return this.repositorioParqueo.obtenerParqueoPorPlaca(placa);
	}

}
