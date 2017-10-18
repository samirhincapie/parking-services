package co.com.ceiba.service;

import java.util.Calendar;

import org.springframework.stereotype.Component;

import co.com.ceiba.model.Carro;
import co.com.ceiba.model.Ingreso;
import co.com.ceiba.model.Salida;

@Component
public class RegistroService {

	public Ingreso agrega(Ingreso nuevoIngreso) {
		return new Ingreso(Calendar.getInstance(), new Carro(""));
	}

	public Salida agrega(Salida nuevaSalida) {
		return new Salida(new Carro(""), Calendar.getInstance());
	}

	public Ingreso consultarIngreso(String placa) {
		return new Ingreso(Calendar.getInstance(), new Carro(""));
	}

}
