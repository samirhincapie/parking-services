package co.com.ceiba.model;

public class ParqueoCarro extends Parqueo {
	private static final double VALOR_ADICIONAL = 0d;

	public ParqueoCarro(Carro carro, double valorHora, double valorDia) {
		super(carro, valorHora, valorDia, VALOR_ADICIONAL);
	}

}
