package co.com.ceiba.service;

import org.springframework.stereotype.Component;

import co.com.ceiba.model.Ingreso;
import co.com.ceiba.model.Salida;
import co.com.ceiba.persistencia.sistema.SistemaDePersistencia;
import co.com.ceiba.repositorio.RepositorioRegistro;

@Component
public class RegistroService {
	
	private SistemaDePersistencia sistemaDePersistencia;
	
	private RepositorioRegistro repositorioRegistro;
	
	public RegistroService(SistemaDePersistencia sistemaDePersistencia){
		this.sistemaDePersistencia = sistemaDePersistencia;
		
		this.repositorioRegistro = this.sistemaDePersistencia.obtenerRepositorioIngreso();
		
	}

	public Ingreso agrega(Ingreso nuevoIngreso) {
		this.repositorioRegistro.agregar(nuevoIngreso);
		return this.repositorioRegistro.obtenerIngresoPorPlaca(nuevoIngreso.getVehiculo().getPlaca());
	}

	public Salida agrega(Salida nuevaSalida) {
		this.repositorioRegistro.agregar(nuevaSalida);
		return nuevaSalida;
	}

	public Ingreso consultarIngreso(String placa) {
		return this.repositorioRegistro.obtenerIngresoPorPlaca(placa);
	}

}
