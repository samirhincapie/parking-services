package co.com.ceiba.model;

public class VigilanteException extends Exception {
	public static final String NO_HAY_PARQUEO_DISPONIBLE_PARA_CARRO = "No hay parqueo disponible para carro";
	
	public VigilanteException(String mensaje){
		super(mensaje);
	}
}
