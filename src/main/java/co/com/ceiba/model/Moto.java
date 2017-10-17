package co.com.ceiba.model;

public class Moto extends Vehiculo {
	
	private int cilindraje;

	public Moto(String placa, int cilindraje) {
		super(placa);
		this.cilindraje = cilindraje;
	}

	public int getCilindraje() {
		return this.cilindraje;
	}
}
