package co.com.ceiba.testdatabuilder;

import java.util.Calendar;

import co.com.ceiba.model.Carro;
import co.com.ceiba.model.Ingreso;

public class IngresoTestDataBuilder {
	private int id;
	private Calendar fecha;
	private Carro carro;
	
	public IngresoTestDataBuilder(){
		this.id = 0;
		this.fecha = new CalendarTestDataBuilder().withYear(1900).withMonth(0).withDay(1)
				.withHour(0).withMinute(0).Build();
		this.carro = new CarroTestDataBuilder().withPlaca("AAA000").Build();
	}
	
	public IngresoTestDataBuilder withId(int id){
		this.id = id;
		return this;
	}	

	public IngresoTestDataBuilder withFecha(Calendar fecha) {
		this.fecha = fecha;
		return this;
	}

	public IngresoTestDataBuilder withCarro(Carro carro) {
		this.carro = carro;
		return this;
	}

	public Ingreso Build(){
		return new Ingreso(this.id, this.fecha, this.carro);
	}
}
