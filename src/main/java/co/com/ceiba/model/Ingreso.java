package co.com.ceiba.model;

import java.util.Calendar;

public class Ingreso {
	private int id;
	private Calendar fecha;
	private Vehiculo vehiculo;

	public Ingreso(Calendar fecha, Vehiculo vehiculo) {
		this.fecha = fecha;
		this.vehiculo = vehiculo;
	}
	
	public Ingreso(int id, Calendar fecha, Vehiculo vehiculo){
		this(fecha, vehiculo);
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Vehiculo getVehiculo() {
		return this.vehiculo;
	}

	public Calendar getFecha() {
		return this.fecha;
	}
	
}
