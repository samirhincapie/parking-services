package co.com.ceiba.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import co.com.ceiba.model.Parqueo;

@Component
public class ParqueoService {

	public List<Parqueo> listarParqueos() {
		return new ArrayList<Parqueo>();
	}

	public void LiberarParqueo(String placa) throws Exception {
		throw new Exception("TODO: liberar parqueo");
	}

}
