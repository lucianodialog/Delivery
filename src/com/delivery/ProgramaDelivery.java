package com.delivery;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

import lanches.Sanduiche;
import menu.Menu;
import tabelas.TabelaTipoTransporte;
import tabelas.TabelaPrecos;
import tabelas.TabelaTempo;

public class ProgramaDelivery {

	public static void main(String[] args) {

		Menu menu;// Para criar o menu principal
		// Opções do menu principal
		String opcoes_menu[] = { "Sanduíches", "Massas", "Bolos", "Sair" };

		// Para opções do menu de ingredientes
		String sanduiche_ingredientes[] = { "Bacon", "Calabresa", "Molho Especial", "Presunto com Queijo", "Mussarela",
				"Molho Especial", "Salada", "Cheddar", "Molho Rose", "Ovo" };

		int menu_opcao;// Para armazenar a opção digitada pelo usuário
		int flag_sair;// Utilizada para controlar os laços do{}while;
		int distancia = 0;// Armazena a distância digitada pelo usuário
		int tamanhoVetor = 10;// Determina o tamnho do vetor
		int total_ingredientes = 0;// Necessário para evitar o estouro do vetor e apresentar os itens corretamente

		// Apenas para deixar a apresentação dos menus mais organizadas
		String divisor_escolhidos = "****************************************************";
		String divisor = "----------------------------------------------------";

		// Cria o objeto sanduiche já com um vetor de ingredientes para o menu de
		// ingredientes
		Sanduiche sanduiche = new Sanduiche(sanduiche_ingredientes);

		/*
		 * O do while garante que seja exibido primeiro o menu e depois solicite uma
		 * entrada do usuário, para depois ser feita a verificação da opção digitada e
		 * finalmente faz a checagem se foi digitada a opção para sair. Facilitando e
		 * escrevendo menos código do que se fosse feito apenas com o while();
		 * 
		 */
		do {

			menu = new Menu(opcoes_menu);// Cria o objeto menu

			/*
			 * Cria o menu e retorna o número da opçao sair. Lógica utillizada para a opçao
			 * sair seja sempre o número consecutivo do total de opções do menu. Assim, caso
			 * o vetor de opções aumente ou diminua será evitado muitas alterações, sendo
			 * necessário apenas alterar as quantidades de opções do switch() abaixo.
			 */

			flag_sair = menu.exibirOpcoes("Menu de Opções", divisor);// Ecibe o menu principal

			menu_opcao = Menu.getOpcaoMenu();// Recebe o número da opção digitado pelo usuário

			switch (menu_opcao) {

			case 0:
				// Armazena os ingredientes escolhidos. Depois o vetor é
				// passado para o sanduiche_pedido
				String sanduiche_pedido[] = new String[tamanhoVetor];

				do {

					// A mesma lógica aplicada no menu de opções se aplica aqui no menu de
					// ingredientes
					flag_sair = sanduiche.exibirOpcoesIngredientes("Menu de Ingredientes", divisor);

					menu_opcao = Menu.getOpcaoMenu();// Recebe o número da opção digitado pelo usuário

					// A lógica abaixo garante que só entra no if se a opção escolhida estiver no
					// menu de ingredientes e que não seja a opção de sair
					// A opção sair será sempre o número consecutivo do total de opções do menu
					// ingredientes

// *********************************Garante que eu tenha uma opçao válida****************************************************************

					if (menu_opcao >= 0 && menu_opcao < sanduiche.getIngredientes().length
							&& menu_opcao != sanduiche.getIngredientes().length) {
						/*
						 * Esse if comentado permite apenas a escolha da quantidade de ingredientes
						 * existente no menu de ingredientes. Já o if(total_ingredientes <
						 * sanduiche.getIngredientes().length), permite a da quantidade de ingredientes
						 * possíveis no vetor
						 */
						// if(total_ingredientes < sanduiche.getIngredientes().length)

//************************Determina quantos itens posso escolher********************************************************************************
						if (total_ingredientes < sanduiche.getIngredientes().length) {
                            //Esse for é apenas para mostrar a msg que e o ingrediente já foi escolhido
							for (int i = 0; i < sanduiche_pedido.length; i++) {
								if (sanduiche.getUmIngrediente(menu_opcao).equals(sanduiche_pedido[i])) {
									System.out.println("Você já escolheu esse ingrediente!");
								}
							}

							sanduiche_pedido[total_ingredientes] = sanduiche.getUmIngrediente(menu_opcao);
							Sanduiche sanduiche_ingrediente = new Sanduiche(sanduiche_pedido);

							System.out.println(divisor_escolhidos);
							System.out.println("Ingrediente escolhido = " + sanduiche.getUmIngrediente(menu_opcao));
							System.out.println("Total de Ingrediente(s) Escolhido(s) = " + (total_ingredientes + 1));
							System.out.println(divisor_escolhidos);

							for (int i = 0; i < sanduiche_ingrediente.getIngredientes().length; i++) {
								// Garante que não sejam exibidos os valores null caso o vetor não esteja todo
								// populado.
								if (sanduiche_ingrediente.getUmIngrediente(i) != null)
									System.out.printf("[%d]........ " + sanduiche_pedido[i] + "\n", i);
							}

							System.out.println(divisor_escolhidos);

//*************************************************Fim Menu Ingredientes Escolhidos**********************************************************************	
							total_ingredientes++;

							// Sai do menu de ingredientes automaticamente
							if (total_ingredientes == sanduiche.getIngredientes().length) {
								menu_opcao = sanduiche.getIngredientes().length;
							}

						}
//A lógica evita que ao sair do menu de ingredientes sem selecionar nenhum item seja exibida apenas a msg "Você não escolheu nenhum ingrediente."
					} else if (menu_opcao != sanduiche.getIngredientes().length
							&& total_ingredientes < sanduiche.getIngredientes().length) {

						System.out.println("Você selecionou uma opção inválida!\n");

					}

				} while (menu_opcao != flag_sair);

//****************************Inicia o Processamento do Pedido********************************************************************				
				if (total_ingredientes > 0) {
					System.out.println(divisor);
					System.out.println("Por favor, digite a distância em Km.");

					try {
						distancia = Menu.getOpcaoMenu();
					} catch (InputMismatchException e) {
						System.out.print("O valor informado não é um número!");
					}

					//sanduiche.setPreco(19.99);
					//System.out.println(sanduiche.getPreco());
					//double total_pedido = sanduiche.calculcarPreco(TabelaPrecos.SANDUICHE, distancia,
							//TabelaDistancia.KM_Moto);// Calcula o valor total do pedido
					//DecimalFormat df = new DecimalFormat("###,##0.00");// Formata uma valor de acordo com a
																		// máscara
																		// passada -> "###,##0.00".

					String status_progresso = ">";// Símbola para indicar o processamento
					double limite_status = 20;// Quantidade de de caracteres que serão exibidos

					// Bloco para simular o status do progresso do pedido
					try {
						System.out.println("   Processando o Pedido");
						System.out.println(divisor);
						System.out.println("          Aguarde");
						System.out.printf("0%% ");
						for (int i = 1; i <= limite_status; i++) {// comecei i = 1 caso precise realizar cálculo
																	// de
																	// porcetagem do preocessamento do pedido.
							System.out.print(status_progresso);// Exibie o progresso
							status_progresso.concat(status_progresso);
							Thread.sleep(200);// intervalo entre uma cada iteração do laço
						}
						System.out.printf(" 100%%");
						Thread.sleep(1000);// apenas para visualizar o termino do processamento

					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

//**********************************Dados do Pedido********************************************************************************					
					System.out.println();
					System.out.println(divisor_escolhidos);
					System.out.println("             FAST DELIVERY\n");
					System.out.println("       Pedido Realizado com Sucesso");
					System.out.println("        Tipo de Lanche: Sanduíche");					
					System.out.println("     " + getDataHora()+"\n");
					System.out.println("          Lista de Ingredientes");
					System.out.println(divisor_escolhidos);
                    
					//Exibe os dados do Pedido
					for (int i = 0; i < sanduiche_pedido.length; i++) {
						if (sanduiche_pedido[i] != null)
							System.out.printf("[%d]........ " + sanduiche_pedido[i] + "\n", i);
					}
					
					// Formata uma valor de acordo com a máscara passada -> "###,##0.00"
					DecimalFormat df = new DecimalFormat("###,##0.00");					
					//Calcula o Preço
					double total_pedido = sanduiche.calculcarPreco(TabelaPrecos.SANDUICHE, distancia, TabelaTipoTransporte.KM_Moto);// Calcula o valor total do pedido
					//Exibe o Total do Pedido
					System.out.println("\nTotal do Pedido:....... R$" + df.format(total_pedido));
					
					//Calcula o tempo de entrega
				 sanduiche.calculaTempoEntrega(distancia, sanduiche.getTempo_por_km_em_minutos(), sanduiche.getTempo_preparo_sanduiche());
					
					System.out.println("Obrigado pela preferência.\n");
					System.out.println(divisor_escolhidos+ "\n");
					
				} else {
					System.out.println("Você não escolheu nenhum ingrediente.");
				}

				/*
				 * Como estou usando as mesmas variáveis para para receber as opções de menu e
				 * submenu, tenho que setar menu_opcao e flag_sair com valores diferentes e que
				 * não estejam em um intervalo válido do menu. Isso permite voltar ao Menu
				 * Principal. Caso não faça isso, ao terminar o processamento do pedido, o
				 * programa será encerrado. Fica a seu critério se quer voltar ao menu Principal
				 * ou encerrar o programa ao processar o pedido. Pode ser declarada variáveis
				 * deiferentes para menu e submenu como opção para não usar essa lógica descrita
				 * acima
				 */

				menu_opcao = -1;
				// flag_sair = -2;

				break;

			case 1:
				System.out.println("Ingredientes da Massa");
				break;

			case 2:
				System.out.println("Ingredientes da Bolo");
				break;

			default:
				// Essa verificação impede exibir a mensagem abaixo quando for escolhida a opção
				// de sair.
				// Pois sair é uma opção válida
				if (menu_opcao != flag_sair)
					System.out.println("Você selecionou uma opção inválida!\n");
				break;
			}

			if (menu_opcao == flag_sair)
				System.out.println("OBRIGADO POR ESCOLHER O MELHOR SISTEMA DELIVERY E VOLTE SEMPRE.");

		} while (menu_opcao != flag_sair);

	}
	
	
	private static String getDataHora() { 
		
		// data/hora atual
		LocalDateTime agora = LocalDateTime.now();

		// formatar a data
		DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("dd/MM/uuuu");
		String dataFormatada = formatterData.format(agora);

		// formatar a hora
		DateTimeFormatter formatterHora = DateTimeFormatter.ofPattern("HH:mm:ss");
		String horaFormatada = formatterHora.format(agora);
		
		return "Data: " + dataFormatada + " Hora: " + horaFormatada;
	
	}

}
