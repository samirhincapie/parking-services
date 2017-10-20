package co.com.ceiba.testdatabuilder;

import co.com.ceiba.model.Carro;

public class CarroTestDataBuilder {
	private static final String PLACA = "AAA000"; 
	
	private String placa; 
	
	public CarroTestDataBuilder(){
		this.placa = PLACA;
	}

	public CarroTestDataBuilder withPlaca(String placa) {
		this.placa = placa;
		return this;
	}
	
	public Carro Build(){
		return new Carro(this.placa);
	}

}
