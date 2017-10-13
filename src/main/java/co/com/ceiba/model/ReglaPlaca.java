package co.com.ceiba.model;

import java.security.KeyPair;
import java.util.ArrayList;
import java.util.List;


public class ReglaPlaca implements IRegla {
	private List<KeyPair> reglasPlacaDia;
	
	public ReglaPlaca (){
		reglasPlacaDia = new ArrayList<KeyPair>();
	}
	
	public List<KeyPair> getReglasPlacaDia(){
		return this.reglasPlacaDia;
	}
	
	public boolean verificar(Ingreso ingreso) {
		
		return false;
	}
}
