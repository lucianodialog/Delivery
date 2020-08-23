package lanches;

import interfaces.MenuIngredientes;

public class Sanduiche extends Lanche implements MenuIngredientes{

	
	private String ingredientes[] = new String[10];
    private int tempo_preparo_sanduiche = 15;
       
    

	

	public int getTempo_preparo_sanduiche() {
		return tempo_preparo_sanduiche;
	}

	public void setTempo_preparo_sanduiche(int tempo_preparo_sanduiche) {
		this.tempo_preparo_sanduiche = tempo_preparo_sanduiche;
	}

	public Sanduiche(String[] ingredientes) {
		super();
		this.ingredientes = ingredientes;
	}

	public String[] getIngredientes() {
		return ingredientes;
	}

	public void setIngredientes(String[] ingredientes) {
		this.ingredientes = ingredientes;
	}
	
	public void addIngrediente(int index, String ingrediente) {
		this.ingredientes[index] = ingrediente;
	}
	
	public String getUmIngrediente(int index) {
		return this.ingredientes[index];
	}

	@Override
	public int exibirOpcoesIngredientes(String titulo, String divisor) {
		
		System.out.println(divisor);
		System.out.println(titulo);
		System.out.println(divisor);
		
		for (int index = 0; index < ingredientes.length; index++) {
			//como o array não foi totalmente populado, essa lógica exibe apenas os valores diferentes de null
		//	if(ingredientes[index] != null)
			   System.out.printf("[%d] " + ingredientes[index] + "\n", index);
		}
		
		System.out.printf("[%d] Finalizar escolha de ingredientes\n", ingredientes.length);
		
		System.out.println(divisor);
		
		return  ingredientes.length;
	}
	
	
	
	
}
