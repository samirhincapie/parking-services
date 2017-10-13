package co.com.ceiba.model;

import java.security.KeyPair;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import scala.annotation.varargs;


public class ReglaPlaca implements IRegla {
	private Map<String, Integer> reglasPlacaDia;
	
	public ReglaPlaca (){
		reglasPlacaDia = new HashMap<String, Integer>();
	}
	
	public Map<String, Integer> getReglasPlacaDia(){
		return this.reglasPlacaDia;
	}
	
	public boolean verificar(Ingreso ingreso) {
		Map<String, Integer> reglasPlacaDiaAplicables = ObtenerReglasPlacaDiaAplicables(ingreso.getVehiculo().getPlaca());
		return false;
	}

	private Map<String, Integer> ObtenerReglasPlacaDiaAplicables(String placa) {
		Map<String, Integer> reglasPlacaDiaAplicables = new HashMap<String, Integer>();
		
		reglasPlacaDia.forEach((placaRegla, diaRegla) -> {
			if(isReglaAplicablePlaca(placaRegla, placa)){
				reglasPlacaDiaAplicables.put(placaRegla, diaRegla);
			}
		});
		
		return null;
	}

	private boolean isReglaAplicablePlaca(String placaRegla, String placa) {
		
		if(placa.length() == placaRegla.length()){
			
			for(char caracterPlaca: placaRegla.toCharArray()){
							
				if(!isComodin(caracterPlaca) && !isMismoCaracterMismaPocision(placaRegla.indexOf(caracterPlaca), placaRegla, placa)){
					return false
				}
			}
		}
		
		return false;
	}

	private boolean isMismoCaracterMismaPocision(int indice, String placaRegla, String placa) {
		return placaRegla.charAt(indice) == placa.charAt(indice);
	}

	private boolean isComodin(char caracterPlaca) {
		return caracterPlaca == '_';
	}
}
