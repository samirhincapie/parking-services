package co.com.ceiba.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.ceiba.model.Parqueo;
import co.com.ceiba.model.RepositorioPersistenteException;
import co.com.ceiba.persistencia.sistema.SistemaDePersistencia;
import co.com.ceiba.repositorio.RepositorioParqueo;

@Service
public class ParqueoService {
	
	@Autowired
	private SistemaDePersistencia sistemaDePersistencia;
	
	@Autowired
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

	public Parqueo consultarParqueo(String placa) throws RepositorioPersistenteException {
		return this.repositorioParqueo.obtenerParqueoPorPlaca(placa);
	}

}
