package co.com.ceiba.model;

public class ParqueoMoto extends Parqueo {

	public ParqueoMoto(Moto moto, double valorHora, double valorDia) {
		super(moto, 500, 600, 2000);
	}
	
	public double getValorAdicional(){
		if(Moto.class.isInstance(super.getVehiculo()) && ((Moto)super.getVehiculo()).getCilindraje() > 500){
			return super.getValorAdicional();
		}
		
		return 0;
	}

}
