package co.com.ceiba.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.com.ceiba.model.Carro;
import co.com.ceiba.model.IRegla;
import co.com.ceiba.model.Ingreso;
import co.com.ceiba.model.Moto;
import co.com.ceiba.model.Parqueo;
import co.com.ceiba.model.ReglaPlaca;
import co.com.ceiba.model.ReglaPlacaDia;
import co.com.ceiba.model.ReglaTipoVehiculo;
import co.com.ceiba.model.RepositorioPersistenteException;
import co.com.ceiba.model.Salida;
import co.com.ceiba.model.Vehiculo;
import co.com.ceiba.model.VigilanteException;
import co.com.ceiba.service.ParqueoService;
import co.com.ceiba.service.RegistroService;

@Component
public class Vigilante {	
	private int limiteParqueoCarro;
	private int limiteParqueoMoto;
	
	@Autowired
	private ParqueoService parqueoService;
	
	@Autowired
	private RegistroService registroService;

	@Autowired
	private List<IRegla> reglas;
	
	protected Vigilante(){

		this.limiteParqueoMoto = 10;
		this.limiteParqueoCarro = 20;
	}

	public Vigilante(ParqueoService parqueoService, RegistroService registroService){
		this();
		this.parqueoService = parqueoService;
		this.registroService = registroService;
		this.reglas = new ArrayList<>();
		cargarReglas();
	}

	public Vigilante(ParqueoService parqueoService, RegistroService registroService, List<IRegla> reglas){
		this(parqueoService, registroService);
		this.reglas = reglas;
	}

	private void cargarReglas() {
		cargarReglasTipoVehiculo();
		cargarReglasPlaca();
	}

	private void cargarReglasPlaca() {
		ReglaPlaca reglaPlaca = new ReglaPlaca();
		reglaPlaca.getReglasPlacaDia().add(new ReglaPlacaDia("A_____", Calendar.MONDAY));
		reglaPlaca.getReglasPlacaDia().add(new ReglaPlacaDia("A_____", Calendar.SUNDAY));
		this.reglas.add(reglaPlaca);
	}

	private void cargarReglasTipoVehiculo() {
		ReglaTipoVehiculo reglaTipoVehiculo = new ReglaTipoVehiculo();
		reglaTipoVehiculo.getTiposVehiculosPermitidos().add(Moto.class);
		reglaTipoVehiculo.getTiposVehiculosPermitidos().add(Carro.class);
		this.reglas.add(reglaTipoVehiculo);
	}

	public Ingreso registrarIngreso(Ingreso ingreso) throws VigilanteException, RepositorioPersistenteException {
		verificarReglas(ingreso);
		verificarDisponibilidad(ingreso.getVehiculo());
		return this.registroService.agrega(ingreso);
	}

	private void verificarReglas(Ingreso ingreso) throws VigilanteException {
		for(IRegla regla: this.reglas){	
			if (!regla.isValido(ingreso)){
				if(ReglaPlaca.class.isInstance(regla)){
					throw new VigilanteException(VigilanteException.NO_PUEDE_INGRESAR_NO_ES_DIA_HABIL);
				}

				if(ReglaTipoVehiculo.class.isInstance(regla)){
					throw new VigilanteException(VigilanteException.TIPO_VEHICULO_NO_PERMITIDO);
				}
			}
		}
	}

	private void verificarDisponibilidad(Vehiculo vehiculo) throws VigilanteException {
		List<Parqueo> parqueos = this.parqueoService.listarParqueos();

		int limite = calcularLimiteParqueo(vehiculo);

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

	private int calcularLimiteParqueo(Vehiculo vehiculo) {	
		if(isMoto(vehiculo)){
			return this.limiteParqueoMoto;
		}
		else if(isCarro(vehiculo)){
			return this.limiteParqueoCarro;
		}
		else{
			return 0;
		}		
	}

	private boolean isCarro(Vehiculo vehiculo) {
		return Carro.class.isInstance(vehiculo);
	}

	private boolean isMoto(Vehiculo vehiculo) {
		return Moto.class.isInstance(vehiculo);
	}

	public Salida registrarSalida(Salida salida) {
		this.parqueoService.liberarParqueo(salida.getVehiculo().getPlaca());
		return registroService.agrega(salida);
	}

	public double indicarValorPorPagar(String placa, Calendar fechaSalida) throws VigilanteException, RepositorioPersistenteException {
		Parqueo parqueo = this.parqueoService.consultarParqueo(placa);
		Ingreso ingreso = this.registroService.consultarIngreso(placa);
		
		int horasParqueo = calcularHorasParqueo(ingreso.getFecha(), fechaSalida);
		
		return calcularValorPorPagar(horasParqueo, parqueo);
	}
	
	private double calcularValorPorPagar(int horasParqueo, Parqueo parqueo) {
		double valorPorPagar = 0;
		
		if(horasParqueo >= 9){
			int dias = horasParqueo / 24;
			valorPorPagar += parqueo.getValorDia() * dias;
			valorPorPagar += parqueo.getValorHora() * (horasParqueo - (dias * 24));
		}
		else{
			valorPorPagar += parqueo.getValorHora() * horasParqueo;
		}
		
		valorPorPagar += parqueo.getValorAdicional();

		return valorPorPagar;
	}

	private int calcularHorasParqueo(Calendar fechaIngreso, Calendar fechaSalida) {
		long diferenciaFechas = fechaSalida.getTimeInMillis() - fechaIngreso.getTimeInMillis();
		double horasTranscurridas = diferenciaFechas / 3600000.0;
		
		boolean isFraccion = (horasTranscurridas - ((int) horasTranscurridas)) > 0;
		
		if(isFraccion){
			return (int)horasTranscurridas + 1;
		}
		else {
			return (int)horasTranscurridas;
		}
	}

}
