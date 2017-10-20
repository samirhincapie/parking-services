package co.com.ceiba.model;

public class ParqueoMoto extends Parqueo {
	private static final double VALOR_ADICIONAL = 2000d;

	public ParqueoMoto(Moto moto, double valorHora, double valorDia) {
		super(moto, valorHora, valorDia, VALOR_ADICIONAL);
	}
	
	public double getValorAdicional(){
		if(Moto.class.isInstance(super.getVehiculo()) && ((Moto)super.getVehiculo()).getCilindraje() > 500){
			return super.getValorAdicional();
		}
		
		return 0;
	}

}
