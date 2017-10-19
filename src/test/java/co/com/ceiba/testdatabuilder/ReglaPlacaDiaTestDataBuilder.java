package co.com.ceiba.testdatabuilder;

import co.com.ceiba.model.ReglaPlacaDia;

public class ReglaPlacaDiaTestDataBuilder {
	private static final int DIA = 0; 
	private static final String PLACA = "A_____";
	
	private int dia;
	private String placa;
	
	public ReglaPlacaDiaTestDataBuilder(){
		this.dia = DIA;
		this.placa = PLACA;
	}
	
	public ReglaPlacaDiaTestDataBuilder withDia(int dia){
		this.dia = dia;
		return this;
	}
	
	public ReglaPlacaDiaTestDataBuilder withPlaca(String placa){
		this.placa = placa;
		return this;
	}
	
	public ReglaPlacaDia Build(){
		return new ReglaPlacaDia(this.placa, this.dia);
	}
}
