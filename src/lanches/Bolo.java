package lanches;



public class Bolo extends Lanche{

	
	private String massa;
	private String recheio;
	private String cobertura;
	
	private String ingredientes_bolo[] = new String[10];
    private int tempo_preparo_sanduiche = 15;
	
   
	
	@Override
	public int exibirOpcoesIngredientes(String titulo, String divisor) {
		
		
		System.out.println(divisor);
		System.out.println("                      Bolo");
		System.out.println("         " + titulo);
		System.out.println(divisor);
		
		for (int index = 0; index < ingredientes_bolo.length; index++) {
			//como o array não foi totalmente populado, essa lógica exibe apenas os valores diferentes de null
		//	if(ingredientes[index] != null)
			   System.out.printf("[%d] " + ingredientes_bolo[index] + "\n", index);
		}
		
		System.out.printf("[%d] Finalizar escolha de ingredientes\n", ingredientes_bolo.length);
		
		System.out.println(divisor);
		
		return  ingredientes_bolo.length;
	}



	public Bolo(String[] ingredientes_bolo) {
		super();
		this.ingredientes_bolo = ingredientes_bolo;
	}



	public String[] getIngredientes_bolo() {
		return ingredientes_bolo;
	}



	public void setIngredientes_bolo(String[] ingredientes_bolo) {
		this.ingredientes_bolo = ingredientes_bolo;
	}



	public String getUmIngredienteBolo(int index) {
		return this.ingredientes_bolo[index];
	}



	
	
}
