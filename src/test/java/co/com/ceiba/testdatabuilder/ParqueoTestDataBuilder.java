package co.com.ceiba.testdatabuilder;

import co.com.ceiba.model.Carro;
import co.com.ceiba.model.Parqueo;

public class ParqueoTestDataBuilder {
	private Carro carro;
	
	public ParqueoTestDataBuilder(){
		this.carro = new CarroTestDataBuilder().withPlaca("AAA000").Build();
	}
	
	public Parqueo Build(){
		return new Parqueo(this.carro);
	}

	public CalendarTestDataBuilder withCarro(Carro mockCarro) {
		// TODO Auto-generated method stub
		return null;
	}
}
