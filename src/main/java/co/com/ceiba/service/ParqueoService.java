package co.com.ceiba.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import co.com.ceiba.model.Carro;
import co.com.ceiba.model.Parqueo;
import co.com.ceiba.model.ParqueoCarro;

@Component
public class ParqueoService {

	public List<Parqueo> listarParqueos() {
		return new ArrayList<Parqueo>();
	}

	public void LiberarParqueo(String placa) {
		
	}

	public Parqueo consultarParqueo(String placa) {
		return new ParqueoCarro(new Carro(""), 0, 0);
	}

}
