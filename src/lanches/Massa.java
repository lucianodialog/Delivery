package lanches;



public class Massa extends Lanche{
	
	private String molho;
	private String ingredientes_massa[] = new String[10];
    private int tempo_preparo_massa = 30;
	
	public String[] getIngredientes_massa() {
		return ingredientes_massa;
	}


	public void setIngredientes_massa(String[] ingredientes_massa) {
		this.ingredientes_massa = ingredientes_massa;
	}


	public Massa(String[] ingredientes_massa) {
		super();
		this.ingredientes_massa = ingredientes_massa;
	}


	
	
	
	public int exibirOpcoesIngredientes() {
		return 0;
		
	}
	
	public String getUmIngredienteMassa(int index) {
		return this.ingredientes_massa[index];
	}
	
	
	@Override
	public int exibirOpcoesIngredientes(String titulo, String divisor) {
		
		
		System.out.println(divisor);
		System.out.println("               Massa");
		System.out.println("         " + titulo);
		System.out.println(divisor);
		
		for (int index = 0; index < ingredientes_massa.length; index++) {
			//como o array não foi totalmente populado, essa lógica exibe apenas os valores diferentes de null
		//	if(ingredientes[index] != null)
			   System.out.printf("[%d] " + ingredientes_massa[index] + "\n", index);
		}
		
		System.out.printf("[%d] Finalizar escolha de ingredientes\n", ingredientes_massa.length);
		
		System.out.println(divisor);
		
		return  ingredientes_massa.length;
	}
	
	
	

}
