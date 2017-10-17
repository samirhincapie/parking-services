package co.com.ceiba.model;

import java.util.Calendar;

public class Salida {
	private Vehiculo vehiculo;
	private Calendar fecha;
	
	public Salida(Vehiculo vehiculo, Calendar fecha){
		this.vehiculo = vehiculo;
		this.fecha = fecha;
	}
	
	public Vehiculo getVehiculo() {
		return this.vehiculo;
	}
	
	public Calendar getFechaSalida(){
		return this.fecha;
	}
}
