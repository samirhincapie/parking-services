package co.com.ceiba.model;

public class RepositorioPersistenteException extends Exception {
	public static final String VEHICULO_ENTITY_DESCONOCIDO = "El vehículo almacenado en la base es desconocido";
	
	public RepositorioPersistenteException(String mensaje){
		super(mensaje);
	}
}
