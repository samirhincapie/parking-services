package co.com.ceiba.testdatabuilder;

import co.com.ceiba.model.Carro;

public class CarroTestDataBuilder {
	private String placa; 
	
	public CarroTestDataBuilder(){
		this.placa = "";
	}

	public CarroTestDataBuilder withPlaca(String placa) {
		this.placa = placa;
		return this;
	}
	
	public Carro Build(){
		return new Carro(this.placa);
	}

}
