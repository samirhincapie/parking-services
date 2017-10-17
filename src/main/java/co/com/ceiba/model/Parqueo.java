package co.com.ceiba.model;

public class Parqueo {
	private Vehiculo vehiculo;
	private double valorHora;
	private double valorDia;
	private double valorAdicional;
	
	public Parqueo(Vehiculo vehiculo, double valorHora, double valorDia, double valorAdicional) {
		this.vehiculo = vehiculo;
		this.valorHora = valorHora;
		this.valorDia = valorDia;
		this.valorAdicional = valorAdicional;
	}

	public Vehiculo getVehiculo() {
		return this.vehiculo;
	}
	
	public double getValorHora(){
		return this.valorHora;
	}
	
	public double getValorDia(){
		return this.valorDia;
	}
	
	public double getValorAdicional(){
		return this.valorAdicional;
	}
}
