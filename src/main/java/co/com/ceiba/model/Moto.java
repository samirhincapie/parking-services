package co.com.ceiba.model;

public class Moto extends Vehiculo {
	
	private int cilindraje;

	protected Moto(String placa, int cilindraje) {
		super(placa);
		this.cilindraje = cilindraje;
	}

	public int getCilindraje() {
		return this.cilindraje;
	}
}
