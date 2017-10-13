package co.com.ceiba.controller;

import java.util.Calendar;
import java.util.List;

import org.springframework.stereotype.Component;

import co.com.ceiba.model.Carro;
import co.com.ceiba.model.IRegla;
import co.com.ceiba.model.Ingreso;
import co.com.ceiba.model.Moto;
import co.com.ceiba.model.Parqueo;
import co.com.ceiba.model.ReglaTipoVehiculo;
import co.com.ceiba.model.Salida;
import co.com.ceiba.model.Vehiculo;
import co.com.ceiba.model.VigilanteException;
import co.com.ceiba.service.ParqueoService;
import co.com.ceiba.service.RegistroService;

@Component
public class Vigilante {	
	private static int LIMITE_PARQUEO_CARRO = 20;
	private static int LIMITE_PARQUEO_MOTO = 10;
	private ParqueoService parqueoService;
	private RegistroService registroService;
	private List<IRegla> reglas;
	
	public Vigilante(ParqueoService parqueoService, RegistroService registroService){
		this.parqueoService = parqueoService;
		this.registroService = registroService;
		CargarReglas();
	}
	
	private void CargarReglas() {
		CargarReglasTipoVehiculo();
		CargarReglasPlaca();
	}

	private void CargarReglasPlaca() {
		
	}

	private void CargarReglasTipoVehiculo() {
		ReglaTipoVehiculo reglaTipoVehiculo = new ReglaTipoVehiculo();
		reglaTipoVehiculo.getTiposVehiculosPermitidos().add(Moto.class);
		reglaTipoVehiculo.getTiposVehiculosPermitidos().add(Carro.class);
		reglas.add(reglaTipoVehiculo);
	}

	public Ingreso registrarIngreso(Ingreso ingreso) throws VigilanteException {
		verificarTipoVehiculo(ingreso.getVehiculo());
		verificarReglaPlaca(ingreso.getVehiculo().getPlaca(), ingreso.getFecha().get(Calendar.DAY_OF_WEEK));
		verificarDisponibilidad(ingreso.getVehiculo());
		return registroService.agrega(ingreso);
	}

	private void verificarReglaPlaca(String placa, int diaIngreso) throws VigilanteException {
		
		
		if(placa.toUpperCase().startsWith("A") 
				&& (diaIngreso != Calendar.MONDAY && diaIngreso != Calendar.SUNDAY)){
			throw new VigilanteException(VigilanteException.NO_PUEDE_INGRESAR_NO_ES_DIA_HABIL);			
		}
	}

	private void verificarTipoVehiculo(Vehiculo vehiculo) throws VigilanteException {
		if(!isMoto(vehiculo) && !isCarro(vehiculo)){
			throw new VigilanteException(VigilanteException.TIPO_VEHICULO_NO_PERMITIDO);
		}
	}

	private void verificarDisponibilidad(Vehiculo vehiculo) throws VigilanteException {
		List<Parqueo> parqueos = parqueoService.listarParqueos();
		
		int limite = CalcularLimiteParqueo(vehiculo);
		
		if(parqueos.size() == limite){
			indicarNoHayParqueo(vehiculo);
		}		
	}

	private void indicarNoHayParqueo(Vehiculo vehiculo) throws VigilanteException {
		String mensaje = "";
		
		if(isMoto(vehiculo)){
			mensaje = VigilanteException.NO_HAY_PARQUEO_DISPONIBLE_PARA_MOTO;
		}
		if(isCarro(vehiculo)){
			mensaje = VigilanteException.NO_HAY_PARQUEO_DISPONIBLE_PARA_CARRO;
		}
		
		throw new VigilanteException(mensaje);
	}

	private int CalcularLimiteParqueo(Vehiculo vehiculo) {		
		return isMoto(vehiculo) ? 10 : (isCarro(vehiculo) ? 20 : 0);
	}

	private boolean isCarro(Vehiculo vehiculo) {
		return Carro.class.isInstance(vehiculo);
	}

	private boolean isMoto(Vehiculo vehiculo) {
		return Moto.class.isInstance(vehiculo);
	}

	public Salida registrarSalida(Salida salida) throws Exception {
		parqueoService.LiberarParqueo(salida.getCarro().getPlaca());
		return registroService.agrega(salida);
	}

}
