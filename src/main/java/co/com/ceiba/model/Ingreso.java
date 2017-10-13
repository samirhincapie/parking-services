package co.com.ceiba.model;

import java.util.Calendar;

public class Ingreso {
	private int id;
	private Calendar fecha;
	private Vehiculo vehiculo;

	public Ingreso(Calendar fecha, Carro carro) {
		this.fecha = fecha;
		this.vehiculo = carro;
	}
	
	public Ingreso(int id, Calendar fecha, Carro carro){
		this(fecha, carro);
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
