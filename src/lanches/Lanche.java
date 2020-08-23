package lanches;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import Constantes.Constantes;

public abstract class Lanche {
	/*
	 * A classe foi declarada como abstract porque não há a necessidade de ser
	 * instanciada.
	 */
	private double preco;
	private int tempo_por_km_em_minutos = 10;

	public int getTempo_por_km_em_minutos() {
		return tempo_por_km_em_minutos;
	}

	public void setTempo_por_km_em_minutos(int tempo_por_km_em_minutos) {
		this.tempo_por_km_em_minutos = tempo_por_km_em_minutos;
	}

	private String[] menu_opcoes = new String[Constantes.TOTAL_OPCOES_MENU];

	public double calculcarPreco(double preco, int distancia, double valor_km) {
		return preco + distancia * valor_km;
	}

	public void calculaTempoEntrega(int distancia, int tempo_por_km_em_minutos, int tempo_preparo) {

		int minutos = (distancia * tempo_por_km_em_minutos) + tempo_preparo;
		int tempo;

		SimpleDateFormat sdf = new SimpleDateFormat("mm");

		try {
			Date dt = sdf.parse(String.valueOf(minutos));
			sdf = new SimpleDateFormat("HH:mm");
			tempo = Integer.parseInt(sdf.format(dt).subSequence(0, 2).toString());
			
			if (tempo > 00)
				System.out.println("Tempo de entrega:...... " + sdf.format(dt) + " Hora(s)\n");
			else
				System.out.println("Tempo de entrega:...... " + sdf.format(dt) + " minuto(s)\n");
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

}
