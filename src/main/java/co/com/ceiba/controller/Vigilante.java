package co.com.ceiba.controller;

import java.util.List;

import org.springframework.stereotype.Component;

import co.com.ceiba.model.Carro;
import co.com.ceiba.model.Ingreso;
import co.com.ceiba.model.Parqueo;
import co.com.ceiba.model.VigilanteException;
import co.com.ceiba.service.ParqueoService;
import co.com.ceiba.service.RegistroService;

@Component
public class Vigilante {
	
	private static Vigilante VIGILANTE;
	
	private ParqueoService parqueoService;
	private RegistroService registroService;
	
	private Vigilante(ParqueoService parqueoService, RegistroService registroService){
		this.parqueoService = parqueoService;
		this.registroService = registroService;
	}
	
	public static Vigilante getInstance(ParqueoService parqueoService, RegistroService registroService){
		Vigilante.VIGILANTE = new Vigilante(parqueoService, registroService);
		return Vigilante.VIGILANTE;
	}

	public boolean recibirCarro(String placa) throws Exception {
		return true;
	}

	public boolean recibirMoto(String placa, String cilindraje) throws Exception {
		return true;
	}

	public Ingreso registrarIngreso(Ingreso ingreso) throws Exception {
		verificarDisponibilidad(ingreso.getCarro());
		return registroService.agrega(ingreso);
	}

	private void verificarDisponibilidad(Carro carro) throws Exception {
		List<Parqueo> parqueos = parqueoService.listarParqueos();
		
		if(parqueos.size() == 20){
			throw new VigilanteException(VigilanteException.NO_HAY_PARQUEO_DISPONIBLE_PARA_CARRO);
		}		
	}

}
