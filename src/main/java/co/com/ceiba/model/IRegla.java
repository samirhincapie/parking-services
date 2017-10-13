package co.com.ceiba.model;

import org.springframework.stereotype.Component;

@Component
public interface IRegla {

	public boolean isValido(Ingreso ingreso);
}
