package co.com.ceiba.model;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;


public class ReglaPlaca implements IRegla {
	private Map<String, Integer> reglasPlacaDia;
	
	public ReglaPlaca (){
		reglasPlacaDia = new HashMap<String, Integer>();
	}
	
	public Map<String, Integer> getReglasPlacaDia(){
		return this.reglasPlacaDia;
	}
	
	public boolean isValido(Ingreso ingreso) {
		Map<String, Integer> reglasPlacaDiaAplicables = ObtenerReglasPlacaDiaAplicables(ingreso.getVehiculo().getPlaca());
		
		return verificarDiaValido(reglasPlacaDiaAplicables, ingreso.getFecha().get(Calendar.DAY_OF_WEEK));
	}

	private boolean verificarDiaValido(Map<String, Integer> reglasPlacaDiaAplicables, int diaIngreso) {
		if(reglasPlacaDiaAplicables.size() > 0){
			
			return reglasPlacaDiaAplicables.containsValue(diaIngreso);
		}
		
		return true;
	}

	private Map<String, Integer> ObtenerReglasPlacaDiaAplicables(String placaIngreso) {
		Map<String, Integer> reglasPlacaDiaAplicables = new HashMap<String, Integer>();
		
		reglasPlacaDia.forEach((placaRegla, diaRegla) -> {
			if(isReglaAplicablePlaca(placaRegla, placaIngreso)){
				reglasPlacaDiaAplicables.put(placaRegla, diaRegla);
			}
		});
		
		return null;
	}

	private boolean isReglaAplicablePlaca(String placaRegla, String placaIngreso) {
		
		if(placaIngreso.length() == placaRegla.length()){
			
			boolean isReglaPlacaAplicable = true; 
			
			for(char caracterPlacaRegla: placaRegla.toCharArray()){
							
				isReglaPlacaAplicable = isReglaPlacaAplicable 
						&& (isComodin(caracterPlacaRegla)
								|| (!isComodin(caracterPlacaRegla) && isMismoCaracterMismaPocision(placaRegla.indexOf(caracterPlacaRegla), placaRegla, placaIngreso))
							);
			}
			
			return isReglaPlacaAplicable;
		}
		
		return false;
	}

	private boolean isMismoCaracterMismaPocision(int indiceCaracter, String placaRegla, String placaIngreso) {
		return String.valueOf(placaRegla.charAt(indiceCaracter)).equalsIgnoreCase(String.valueOf(placaIngreso.charAt(indiceCaracter)));
	}

	private boolean isComodin(char caracterPlaca) {
		return caracterPlaca == '_';
	}
}
