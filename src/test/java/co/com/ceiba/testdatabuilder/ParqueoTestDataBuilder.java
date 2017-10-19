package co.com.ceiba.testdatabuilder;

import static org.mockito.Mockito.mock;

import co.com.ceiba.model.Parqueo;
import co.com.ceiba.model.Vehiculo;

public class ParqueoTestDataBuilder {
	public static final Vehiculo VEHICULO = mock(Vehiculo.class);
	public static final double VALOR_HORA = 500d;
	public static final double VALOR_DIA = 600d;
	public static final double VALOR_ADICIONAL = 2000d;
	
	public Vehiculo vehiculo;
	public double valorHora;
	public double valorDia;
	public double valorAdicional;
	
	public ParqueoTestDataBuilder(){
		this.vehiculo = VEHICULO;
		this.valorAdicional = VALOR_ADICIONAL;
		this.valorDia = VALOR_DIA;
		this.valorHora = VALOR_HORA;
	}
	
	public ParqueoTestDataBuilder withVehiculo(Vehiculo vehiculo){
		this.vehiculo = vehiculo;
		return this;
	}
	
	public ParqueoTestDataBuilder withValorHora(double valorHora){
		this.valorHora = valorHora;
		return this;
	}
	
	public ParqueoTestDataBuilder withValorDia(double valorDia){
		this.valorDia = valorDia;
		return this;
	}
	
	public ParqueoTestDataBuilder withValorAdicional(double valorAdicional){
		this.valorAdicional = valorAdicional;
		return this;
	}
	
	public Parqueo Build(){
		return new Parqueo(this.vehiculo, this.valorHora, this.valorDia, this.valorAdicional);
	}
}
