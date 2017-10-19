package co.com.ceiba.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class ReglaPlaca implements IRegla {
	private List<ReglaPlacaDia> reglasPlacaDia;

	public ReglaPlaca (){
		this.reglasPlacaDia = new ArrayList<>();
	}

	public List<ReglaPlacaDia> getReglasPlacaDia(){
		return this.reglasPlacaDia;
	}

	public boolean isValido(Ingreso ingreso) {
		List<ReglaPlacaDia> reglasPlacaDiaAplicables = obtenerReglasPlacaDiaAplicables(ingreso.getVehiculo().getPlaca());
		return verificarDiaValido(reglasPlacaDiaAplicables, ingreso.getFecha().get(Calendar.DAY_OF_WEEK));
	}

	private boolean verificarDiaValido(List<ReglaPlacaDia> reglasPlacaDiaAplicables, int diaIngreso) {
		if(!reglasPlacaDiaAplicables.isEmpty()){
			for(ReglaPlacaDia reglaPlacaDia : reglasPlacaDiaAplicables){
				if(reglaPlacaDia.getDia() == diaIngreso){
					return true;
				}
			}
			
			return false;
		}		

		return true;
	}

	private List<ReglaPlacaDia> obtenerReglasPlacaDiaAplicables(String placaIngreso) {
		List<ReglaPlacaDia> reglasPlacaDiaAplicables = new ArrayList<>();

		for(ReglaPlacaDia reglaPlacaDia : this.reglasPlacaDia){
			if(isReglaAplicablePlaca(reglaPlacaDia.getPlaca(), placaIngreso)){
				reglasPlacaDiaAplicables.add(new ReglaPlacaDia(reglaPlacaDia.getPlaca(), reglaPlacaDia.getDia()));
			}
		}

		return reglasPlacaDiaAplicables;
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
