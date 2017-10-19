package co.com.ceiba.testdatabuilder;

import java.util.Calendar;

import org.mockito.Mockito;

import co.com.ceiba.model.Carro;
import co.com.ceiba.model.Ingreso;
import co.com.ceiba.model.Vehiculo;

public class IngresoTestDataBuilder {
	private static final int ID = 0;
	private static final Calendar FECHA = Mockito.mock(Calendar.class);
	private static final Vehiculo VEHICULO = Mockito.mock(Carro.class);
	
	private int id;
	private Calendar fecha;
	private Vehiculo vehiculo;
	
	public IngresoTestDataBuilder(){
		this.id = ID;
		this.fecha = FECHA;
		this.vehiculo = VEHICULO;
	}
	
	public IngresoTestDataBuilder withId(int id){
		this.id = id;
		return this;
	}	

	public IngresoTestDataBuilder withFecha(Calendar fecha) {
		this.fecha = fecha;
		return this;
	}

	public IngresoTestDataBuilder withVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
		return this;
	}

	public Ingreso Build(){
		return new Ingreso(this.id, this.fecha, this.vehiculo);
	}
}
