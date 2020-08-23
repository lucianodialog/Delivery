package menu;
import java.util.Scanner;

import Constantes.Constantes;

public class Menu {
	
private String[] menu_opcoes = new String[Constantes.TOTAL_OPCOES_MENU];

public Menu(String[] menu_opcoes) {
	super();
	this.menu_opcoes = menu_opcoes;
}


public int exibirOpcoes(String titulo, String divisor) {
	
	System.out.println(divisor);
	System.out.println(titulo);
	System.out.println(divisor);
	
	for (int index = 0; index < menu_opcoes.length; index++) {
		System.out.printf("[%d] " + menu_opcoes[index] + "\n", index);
	}
	
	
	System.out.println(divisor);
	
	return  menu_opcoes.length - 1;
	
}


//M�todo que recebe a entrada do usu�rio e trata a exce��o caso seja digitado
	// uma caracter ou texto
public static int getOpcaoMenu()
{
    Scanner opcao = new Scanner(System.in);
    
    while (true)
    {	        
        try
        {
            int amount = opcao.nextInt();
            return amount;
        } 
        catch (java.util.InputMismatchException e)
        {
        	System.out.println("Por favor, Digite uma op��o v�lida do menu!");
            opcao.nextLine();
        }
    }
}









}
