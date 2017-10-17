package co.com.ceiba.model;

public class VigilanteException extends Exception {
	public static final String NO_HAY_PARQUEO_DISPONIBLE_PARA_CARRO = "No hay parqueo disponible para carro";
	public static final String NO_HAY_PARQUEO_DISPONIBLE_PARA_MOTO = "No hay parqueo disponible para moto";
	public static final String TIPO_VEHICULO_NO_PERMITIDO = "Tipo de Vehículo no permitido";
	public static final String NO_PUEDE_INGRESAR_NO_ES_DIA_HABIL = "No puede ingresar porque no está en un día hábil";
	public static final String VEHICULO_NO_ESTA_EN_EL_PARQUEADERO = "El vehículo no está en el parqueadero";
	
	public VigilanteException(String mensaje){
		super(mensaje);
	}
}
