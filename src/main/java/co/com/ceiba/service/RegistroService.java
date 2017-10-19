package co.com.ceiba.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.ceiba.model.Ingreso;
import co.com.ceiba.model.RepositorioPersistenteException;
import co.com.ceiba.model.Salida;
import co.com.ceiba.persistencia.sistema.SistemaDePersistencia;
import co.com.ceiba.repositorio.RepositorioRegistro;

@Service
public class RegistroService {
	@Autowired
	private SistemaDePersistencia sistemaDePersistencia;
	
	@Autowired
	private RepositorioRegistro repositorioRegistro;
	
	public RegistroService(SistemaDePersistencia sistemaDePersistencia){
		this.sistemaDePersistencia = sistemaDePersistencia;
		
		this.repositorioRegistro = this.sistemaDePersistencia.obtenerRepositorioIngreso();
		
	}

	public Ingreso agrega(Ingreso nuevoIngreso) throws RepositorioPersistenteException {
		this.repositorioRegistro.agregar(nuevoIngreso);
		return this.repositorioRegistro.obtenerIngresoPorPlaca(nuevoIngreso.getVehiculo().getPlaca());
	}

	public Salida agrega(Salida nuevaSalida) {
		this.repositorioRegistro.agregar(nuevaSalida);
		return nuevaSalida;
	}

	public Ingreso consultarIngreso(String placa) throws RepositorioPersistenteException {
		return this.repositorioRegistro.obtenerIngresoPorPlaca(placa);
	}

}
