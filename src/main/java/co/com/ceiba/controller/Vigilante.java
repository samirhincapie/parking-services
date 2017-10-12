package co.com.ceiba.controller;

import java.util.List;

import org.springframework.stereotype.Component;

import co.com.ceiba.model.Ingreso;
import co.com.ceiba.model.Parqueo;
import co.com.ceiba.model.VigilanteException;
import co.com.ceiba.service.ParqueoService;
import co.com.ceiba.service.RegistroService;

@Component
public class Vigilante {	
	private ParqueoService parqueoService;
	private RegistroService registroService;
	
	public Vigilante(ParqueoService parqueoService, RegistroService registroService){
		this.parqueoService = parqueoService;
		this.registroService = registroService;
	}
	
	public Ingreso registrarIngreso(Ingreso ingreso) throws Exception {
		verificarDisponibilidad();
		return registroService.agrega(ingreso);
	}

	private void verificarDisponibilidad() throws Exception {
		List<Parqueo> parqueos = parqueoService.listarParqueos();
		
		if(parqueos.size() == 20){
			throw new VigilanteException(VigilanteException.NO_HAY_PARQUEO_DISPONIBLE_PARA_CARRO);
		}
	}

}
