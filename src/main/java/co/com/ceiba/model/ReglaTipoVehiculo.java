package co.com.ceiba.model;

import java.util.ArrayList;
import java.util.List;

public class ReglaTipoVehiculo implements IRegla {
	private List<Class> tiposVehiculoPermitidos;
	
	public ReglaTipoVehiculo(){
		tiposVehiculoPermitidos = new ArrayList<Class>();
	}
	
	public List<Class> getTiposVehiculosPermitidos(){
		return this.tiposVehiculoPermitidos;
	}
	
	public boolean isValido(Ingreso ingreso){
		return tiposVehiculoPermitidos.contains(ingreso.getVehiculo().getClass());
	}
}
