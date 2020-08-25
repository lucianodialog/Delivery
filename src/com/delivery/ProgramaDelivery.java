package com.delivery;

import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;

import lanches.Bolo;
import lanches.Massa;
import lanches.Sanduiche;
import menu.Menu;
import tabelas.TabelaPrecos;
import tabelas.TabelaTipoTransporte;

public class ProgramaDelivery {

	public static void main(String[] args) {

		Menu menu;// Para criar o menu principal
		// Op��es do menu principal
		String opcoes_menu[] = { "Sandu�ches", "Massas", "Bolos", "Sair" };

		// Para op��es do menu de ingredientes
		String sanduiche_ingredientes[] = { "Bacon", "Calabresa", "Molho Especial", "Presunto com Queijo", "Mussarela",
				"Molho Especial", "Salada", "Cheddar", "Molho Rose", "Ovo" };
		
		//Para op��es do menu de ingredientes
				String massa_ingredientes[] = { "Farinha de Trigo", "Fermento Comum", "Sal", "A��car", "Azeite", "�gua", "Manteiga",
						"�leo", "Fermento Biol�gico", "Or�gano" };
		//Para op��es do menu de ingredientes
		String bolo_ingredientes[] = { "A��car", "Farinha de Trigo", "Fermento Comum", "Sal", "Coco", "Agua", "Manteiga",
						"Achocolatado", "Fermento Biologico", "Leite" };

		int menu_opcao;// Para armazenar a op��o digitada pelo usu�rio
		int flag_sair;// Utilizada para controlar os la�os do{}while;
		int distancia = 0;// Armazena a dist�ncia digitada pelo usu�rio
		int tamanhoVetor = 10;// Determina o tamnho do vetor
		int total_ingredientes = 0;// Necess�rio para evitar o estouro do vetor e apresentar os itens corretamente
		Sanduiche sanduiche_personalizado;
        Massa massa_personalizada;
        Bolo bolo_personalizado;
		// Apenas para deixar a apresenta��o dos menus mais organizadas
		String divisor_escolhidos = "****************************************************";
		String divisor = "----------------------------------------------------";

		// Cria o objeto sanduiche j� com um vetor de ingredientes para o menu de
		// ingredientes
		Sanduiche sanduiche = new Sanduiche(sanduiche_ingredientes);
		Massa massa = new Massa(massa_ingredientes);
		Bolo bolo = new Bolo(bolo_ingredientes);

		/*
		 * O do while garante que seja exibido primeiro o menu e depois solicite uma
		 * entrada do usu�rio, para depois ser feita a verifica��o da op��o digitada e
		 * finalmente faz a checagem se foi digitada a op��o para sair. Facilitando e
		 * escrevendo menos c�digo do que se fosse feito apenas com o while();
		 * 
		 */
		//limpaTela();//No eclipse n�o funciona, s� quando roda no console(cmd por exemplo)
		do {
			
			menu = new Menu(opcoes_menu);// Cria o objeto menu

			/*
			 * Cria o menu e retorna o n�mero da op�ao sair. L�gica utillizada para a op�ao
			 * sair seja sempre o n�mero consecutivo do total de op��es do menu. Assim, caso
			 * o vetor de op��es aumente ou diminua ser� evitado muitas altera��es, sendo
			 * necess�rio apenas alterar as quantidades de op��es do switch() abaixo.
			 */

			flag_sair = menu.exibirOpcoes("Menu de Op��es", divisor);// Ecibe o menu principal

			menu_opcao = Menu.getOpcaoMenu();// Recebe o n�mero da op��o digitado pelo usu�rio

			switch (menu_opcao) {
//-----------------------------------------------------------------------------------------------------------------------------------------------
			//Menu de Bolos
//-----------------------------------------------------------------------------------------------------------------------------------------------

			case 0:
				
			
				// Armazena os ingredientes escolhidos. Depois o vetor �
				// passado para o sanduiche_pedido0
				String sanduiche_pedido[] = new String[tamanhoVetor];

				do {
					
					// A mesma l�gica aplicada no menu de op��es se aplica aqui no menu de
					// ingredientes
					flag_sair = sanduiche.exibirOpcoesIngredientes("Menu de Ingredientes", divisor);

					menu_opcao = Menu.getOpcaoMenu();// Recebe o n�mero da op��o digitado pelo usu�rio

					// A l�gica abaixo garante que s� entra no if se a op��o escolhida estiver no
					// menu de ingredientes e que n�o seja a op��o de sair
					// A op��o sair ser� sempre o n�mero consecutivo do total de op��es do menu
					// ingredientes

// *********************************Garante que eu tenha uma op�ao v�lida****************************************************************

					if (menu_opcao >= 0 && menu_opcao < sanduiche.getIngredientes().length
							&& menu_opcao != sanduiche.getIngredientes().length) {
						
						/*
						 * Esse if comentado permite apenas a escolha da quantidade de ingredientes
						 * existente no menu de ingredientes. J� o if(total_ingredientes <
						 * sanduiche.getIngredientes().length), permite a da quantidade de ingredientes
						 * poss�veis no vetor
						 */
						// if(total_ingredientes < sanduiche.getIngredientes().length)

//************************Determina quantos itens posso escolher********************************************************************************
						if (total_ingredientes < sanduiche.getIngredientes().length) {
                            //Esse for � apenas para mostrar a msg que e o ingrediente j� foi escolhido
							for (int i = 0; i < sanduiche_pedido.length; i++) {
								if (sanduiche.getUmIngrediente(menu_opcao).equals(sanduiche_pedido[i])) {
									System.out.println("Voc� j� escolheu esse ingrediente!");
								}
							}

							sanduiche_pedido[total_ingredientes] = sanduiche.getUmIngrediente(menu_opcao);
							sanduiche_personalizado = new Sanduiche(sanduiche_pedido);

							System.out.println(divisor_escolhidos);
							System.out.println("Ingrediente escolhido = " + sanduiche.getUmIngrediente(menu_opcao));
							System.out.println("Total de Ingrediente(s) Escolhido(s) = " + (total_ingredientes + 1));
							System.out.println(divisor_escolhidos);

							for (int i = 0; i < sanduiche_personalizado.getIngredientes().length; i++) {
								// Garante que n�o sejam exibidos os valores null caso o vetor n�o esteja todo
								// populado.
								if (sanduiche_personalizado.getUmIngrediente(i) != null)
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
//A l�gica evita que ao sair do menu de ingredientes sem selecionar nenhum item seja exibida apenas a msg "Voc� n�o escolheu nenhum ingrediente."
					} else if (menu_opcao != sanduiche.getIngredientes().length
							&& total_ingredientes < sanduiche.getIngredientes().length) {

						System.out.println("Voc� selecionou uma op��o inv�lida!\n");

					}

				} while (menu_opcao != flag_sair);

//****************************Inicia o Processamento do Pedido*********************************************************************************li	
			
				
				if (total_ingredientes > 0) {
					System.out.println(divisor);
					System.out.println("Por favor, digite a dist�ncia em Km.");

					try {
						distancia = Menu.getOpcaoMenu();
					} catch (InputMismatchException e) {
						System.out.print("O valor informado n�o � um n�mero!");
					}

					//sanduiche.setPreco(19.99);
					//System.out.println(sanduiche.getPreco());
					//double total_pedido = sanduiche.calculcarPreco(TabelaPrecos.SANDUICHE, distancia,
							//TabelaDistancia.KM_Moto);// Calcula o valor total do pedido
					//DecimalFormat df = new DecimalFormat("###,##0.00");// Formata uma valor de acordo com a
																		// m�scara
																		// passada -> "###,##0.00".

					String status_progresso = ">";// S�mbola para indicar o processamento
					double limite_status = 20;// Quantidade de de caracteres que ser�o exibidos

					// Bloco para simular o status do progresso do pedido
					try {
						System.out.println("   Processando o Pedido");
						System.out.println(divisor);
						System.out.println("          Aguarde");
						System.out.printf("0%% ");
						for (int i = 1; i <= limite_status; i++) {// comecei i = 1 caso precise realizar c�lculo
																	// de
																	// porcetagem do preocessamento do pedido.
							System.out.print(status_progresso);// Exibie o progresso
							status_progresso.concat(status_progresso);
							Thread.sleep(200);// intervalo entre uma cada itera��o do la�o
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
					System.out.println("        Tipo de Lanche: Sandu�che");					
					System.out.println("     " + getDataHora()+"\n");
					System.out.println("          Lista de Ingredientes");
					System.out.println(divisor_escolhidos);
                    
					//Exibe os dados do Pedido
					for (int i = 0; i < sanduiche_pedido.length; i++) {
						if (sanduiche_pedido[i] != null)
							System.out.printf("[%d]........ " + sanduiche_pedido[i] + "\n", i);
					}
					
					// Formata uma valor de acordo com a m�scara passada -> "###,##0.00"
					DecimalFormat df = new DecimalFormat("###,##0.00");					
					//Calcula o Pre�o
					double total_pedido = sanduiche.calculcarPreco(TabelaPrecos.SANDUICHE, distancia, TabelaTipoTransporte.KM_Moto);// Calcula o valor total do pedido
					//Exibe o Total do Pedido
					System.out.println("\nTotal do Pedido:....... R$" + df.format(total_pedido));
					
					//Calcula o tempo de entrega
				 sanduiche.calculaTempoEntrega(distancia, sanduiche.getTempo_por_km_em_minutos(), sanduiche.getTempo_preparo_sanduiche());
					
					System.out.println("Obrigado pela prefer�ncia.\n");
					System.out.println(divisor_escolhidos+ "\n");
					
					total_ingredientes = 0; //Necess�rio pq as outras op��es de menu a utilizam
					
				} else {
					System.out.println("Voc� n�o escolheu nenhum ingrediente.");
				}

				/*
				 * Como estou usando as mesmas vari�veis para para receber as op��es de menu e
				 * submenu, tenho que setar menu_opcao e flag_sair com valores diferentes e que
				 * n�o estejam em um intervalo v�lido do menu. Isso permite voltar ao Menu
				 * Principal. Caso n�o fa�a isso, ao terminar o processamento do pedido, o
				 * programa ser� encerrado. Fica a seu crit�rio se quer voltar ao menu Principal
				 * ou encerrar o programa ao processar o pedido. Pode ser declarada vari�veis
				 * deiferentes para menu e submenu como op��o para n�o usar essa l�gica descrita
				 * acima
				 */

				menu_opcao = -1;
				// flag_sair = -2;

				break;
//-----------------------------------------------------------------------------------------------------------------------------------------------
				//Menu de Massas
//-----------------------------------------------------------------------------------------------------------------------------------------------


			case 1:

				// Armazena os ingredientes escolhidos. Depois o vetor �
				// passado para o sanduiche_pedido
				String massa_pedido[] = new String[tamanhoVetor];

				do {
					
					// A mesma l�gica aplicada no menu de op��es se aplica aqui no menu de
					// ingredientes
					flag_sair = massa.exibirOpcoesIngredientes("Menu de Ingredientes", divisor);

					menu_opcao = Menu.getOpcaoMenu();// Recebe o n�mero da op��o digitado pelo usu�rio

					// A l�gica abaixo garante que s� entra no if se a op��o escolhida estiver no
					// menu de ingredientes e que n�o seja a op��o de sair
					// A op��o sair ser� sempre o n�mero consecutivo do total de op��es do menu
					// ingredientes

// *********************************Garante que eu tenha uma op�ao v�lida****************************************************************

					if (menu_opcao >= 0 && menu_opcao < massa.getIngredientes_massa().length
							&& menu_opcao !=  massa.getIngredientes_massa().length) {
						
						/*
						 * Esse if comentado permite apenas a escolha da quantidade de ingredientes
						 * existente no menu de ingredientes. J� o if(total_ingredientes <
						 * getIngredientes_massa().length), permite a quantidade de ingredientes
						 * poss�veis no vetor
						 */
						// if(total_ingredientes < massa.getIngredientes_massa().length)

//************************Determina quantos itens posso escolher********************************************************************************
						if (total_ingredientes < massa.getIngredientes_massa().length) {
                            //Esse for � apenas para mostrar a msg que e se o ingrediente j� foi escolhido
							for (int i = 0; i < massa_pedido.length; i++) {
								if (massa.getUmIngredienteMassa(menu_opcao).equals(massa_pedido[i])) {
									System.out.println("Voc� j� escolheu esse ingrediente!");
								}
							}

							massa_pedido[total_ingredientes] = massa.getUmIngredienteMassa(menu_opcao);
							massa_personalizada = new Massa(massa_pedido);
							

							System.out.println(divisor_escolhidos);
							System.out.println("Ingrediente escolhido = " + massa.getUmIngredienteMassa(menu_opcao));
							System.out.println("Total de Ingrediente(s) Escolhido(s) = " + (total_ingredientes + 1));
							System.out.println(divisor_escolhidos);

							for (int i = 0; i < massa_personalizada.getIngredientes_massa().length; i++) {
								// Garante que n�o sejam exibidos os valores null caso o vetor n�o esteja todo
								// populado.
								if (massa_personalizada.getUmIngredienteMassa(i) != null)
									System.out.printf("[%d]........ " + massa_pedido[i] + "\n", i);
							}

							System.out.println(divisor_escolhidos);
							
							
							

//*************************************************Fim Menu Ingredientes Escolhidos**********************************************************************	
							total_ingredientes++;

							// Sai do menu de ingredientes automaticamente
							if (total_ingredientes == massa.getIngredientes_massa().length) {
								menu_opcao = massa.getIngredientes_massa().length;
							}

						}
//A l�gica evita que ao sair do menu de ingredientes sem selecionar nenhum item seja exibida apenas a msg "Voc� n�o escolheu nenhum ingrediente."
					} else if (menu_opcao != massa.getIngredientes_massa().length
							&& total_ingredientes < massa.getIngredientes_massa().length) {

						System.out.println("Voc� selecionou uma op��o inv�lida!\n");

					}

				} while (menu_opcao != flag_sair);

//****************************Inicia o Processamento do Pedido********************************************************************	
			
				
				if (total_ingredientes > 0) {
					System.out.println(divisor);
					System.out.println("Por favor, digite a dist�ncia em Km.");

					try {
						distancia = Menu.getOpcaoMenu();
					} catch (InputMismatchException e) {
						System.out.print("O valor informado n�o � um n�mero!");
					}

				
					String status_progresso = ">";// S�mbola para indicar o processamento
					double limite_status = 20;// Quantidade de de caracteres que ser�o exibidos

					// Bloco para simular o status do progresso do pedido
					try {
						System.out.println("   Processando o Pedido");
						System.out.println(divisor);
						System.out.println("          Aguarde");
						System.out.printf("0%% ");
						// comecei i = 1 caso precise realizar c�lculo	de porcentagem do preocessamento do pedido.
						for (int i = 1; i <= limite_status; i++) {																	
																	
							System.out.print(status_progresso);// Exibie o progresso
							status_progresso.concat(status_progresso);
							Thread.sleep(200);// intervalo entre cada itera��o do la�o
						}
						System.out.printf(" 100%%");
						Thread.sleep(1000);// apenas para visualizar o t�rmino do processamento

					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

//**********************************Dados do Pedido********************************************************************************		
					
					
					System.out.println();
					System.out.println(divisor_escolhidos);
					System.out.println("             FAST DELIVERY\n");
					System.out.println("       Pedido Realizado com Sucesso");
					System.out.println("        Tipo de Lanche: Massa");					
					System.out.println("     " + getDataHora()+"\n");
					System.out.println("          Lista de Ingredientes");
					System.out.println(divisor_escolhidos);
                    
					//Exibe os dados do Pedido
					for (int i = 0; i < massa_pedido.length; i++) {
						if (massa_pedido[i] != null)
							System.out.printf("[%d]........ " + massa_pedido[i] + "\n", i);
					}
					
					// Formata um valor de acordo com a m�scara passada -> "###,##0.00"
					DecimalFormat df = new DecimalFormat("###,##0.00");					
					//Calcula o Pre�o
					double total_pedido = massa.calculcarPreco(TabelaPrecos.MASSA, distancia, TabelaTipoTransporte.KM_Moto);// Calcula o valor total do pedido
					//Exibe o Total do Pedido
					System.out.println("\nTotal do Pedido:....... R$" + df.format(total_pedido));
					
					//Calcula o tempo de entrega
				 massa.calculaTempoEntrega(distancia, sanduiche.getTempo_por_km_em_minutos(), sanduiche.getTempo_preparo_sanduiche());
					
					System.out.println("Obrigado pela prefer�ncia.\n");
					System.out.println(divisor_escolhidos+ "\n");
					
					total_ingredientes = 0;
					
				} else {
					System.out.println("Voc� n�o escolheu nenhum ingrediente.");
				}

				/*
				 * Como estou usando as mesmas vari�veis para para receber as op��es de menu e
				 * submenu, tenho que setar menu_opcao e flag_sair com valores diferentes e que
				 * n�o estejam em um intervalo v�lido do menu. Isso permite voltar ao Menu
				 * Principal. Caso n�o fa�a isso, ao terminar o processamento do pedido, o
				 * programa ser� encerrado. Fica a seu crit�rio se quer voltar ao menu Principal
				 * ou encerrar o programa ao processar o pedido. Pode ser declarada vari�veis
				 * deiferentes para menu e submenu como op��o para n�o usar essa l�gica descrita
				 * acima
				 */

				menu_opcao = -1;
				// flag_sair = -2;


				break;
//-----------------------------------------------------------------------------------------------------------------------------------------------
				//Menu de Bolos
//-----------------------------------------------------------------------------------------------------------------------------------------------
			case 2:

				// Armazena os ingredientes escolhidos. Depois o vetor �
				// passado para o sanduiche_pedido
				String bolo_pedido[] = new String[tamanhoVetor];

				do {
					
					// A mesma l�gica aplicada no menu de op��es se aplica aqui no menu de
					// ingredientes
					flag_sair = bolo.exibirOpcoesIngredientes("       Menu de Ingredientes", divisor);

					menu_opcao = Menu.getOpcaoMenu();// Recebe o n�mero da op��o digitado pelo usu�rio

					// A l�gica abaixo garante que s� entra no if se a op��o escolhida estiver no
					// menu de ingredientes e que n�o seja a op��o de sair
					// A op��o sair ser� sempre o n�mero consecutivo do total de op��es do menu
					// ingredientes

// *********************************Garante que eu tenha uma op�ao v�lida****************************************************************

					if (menu_opcao >= 0 && menu_opcao < bolo.getIngredientes_bolo().length
							&& menu_opcao != sanduiche.getIngredientes().length) {
						
						/*
						 * Esse if comentado permite apenas a escolha da quantidade de ingredientes
						 * existente no menu de ingredientes. J� o if(total_ingredientes <
						 * sanduiche.getIngredientes().length), permite a quantidade de ingredientes
						 * poss�veis no vetor
						 */
						// if(total_ingredientes < sanduiche.getIngredientes().length)

//************************Determina quantos itens posso escolher********************************************************************************
						if (total_ingredientes < bolo.getIngredientes_bolo().length) {
                            //Esse for � apenas para mostrar a msg que e o ingrediente j� foi escolhido
							for (int i = 0; i < bolo_pedido.length; i++) {
								if (bolo.getUmIngredienteBolo(menu_opcao).equals(bolo_pedido[i])) {
									System.out.println("Voc� j� escolheu esse ingrediente!");
								}
							}

							bolo_pedido[total_ingredientes] = bolo.getUmIngredienteBolo(menu_opcao);
							bolo_personalizado = new Bolo(bolo_pedido);

							System.out.println(divisor_escolhidos);
							System.out.println("Ingrediente escolhido = " + bolo.getUmIngredienteBolo(menu_opcao));
							System.out.println("Total de Ingrediente(s) Escolhido(s) = " + (total_ingredientes + 1));
							System.out.println(divisor_escolhidos);

							for (int i = 0; i < bolo_personalizado.getIngredientes_bolo().length; i++) {
								// Garante que n�o sejam exibidos os valores null caso o vetor n�o esteja todo
								// populado.
								if (bolo_personalizado.getUmIngredienteBolo(i) != null)
									System.out.printf("[%d]........ " + bolo_pedido[i] + "\n", i);
							}

							System.out.println(divisor_escolhidos);
							
							
							

//*************************************************Fim Menu Ingredientes Escolhidos**********************************************************************	
							total_ingredientes++;

							// Sai do menu de ingredientes automaticamente
							if (total_ingredientes == bolo.getIngredientes_bolo().length) {
								menu_opcao = bolo.getIngredientes_bolo().length;
							}

						}
                        //A l�gica evita que ao sair do menu de ingredientes sem selecionar nenhum item seja exibida
						//apenas a msg "Voc� n�o escolheu nenhum ingrediente."
					} else if (menu_opcao != bolo.getIngredientes_bolo().length
							&& total_ingredientes < bolo.getIngredientes_bolo().length) {

						System.out.println("Voc� selecionou uma op��o inv�lida!\n");

					}

				} while (menu_opcao != flag_sair);

//****************************Inicia o Processamento do Pedido********************************************************************	
			
				
				if (total_ingredientes > 0) {
					System.out.println(divisor);
					System.out.println("Por favor, digite a dist�ncia em Km.");

					try {
						distancia = Menu.getOpcaoMenu();
					} catch (InputMismatchException e) {
						System.out.print("O valor informado n�o � um n�mero!");
					}

					
					String status_progresso = ">";// S�mbola para indicar o processamento
					double limite_status = 20;// Quantidade de de caracteres que ser�o exibidos

					// Bloco para simular o status do progresso do pedido
					try {
						System.out.println("   Processando o Pedido");
						System.out.println(divisor);
						System.out.println("          Aguarde");
						System.out.printf("0%% ");
						for (int i = 1; i <= limite_status; i++) {// comecei i = 1 caso precise realizar c�lculo
																	// de
																	// porcetagem do preocessamento do pedido.
							System.out.print(status_progresso);// Exibie o progresso
							status_progresso.concat(status_progresso);
							Thread.sleep(200);// intervalo entre uma cada itera��o do la�o
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
					System.out.println("        Tipo de Lanche: Bolo");					
					System.out.println("     " + getDataHora()+"\n");
					System.out.println("          Lista de Ingredientes");
					System.out.println(divisor_escolhidos);
                    
					//Exibe os dados do Pedido
					for (int i = 0; i < bolo_pedido.length; i++) {
						if (bolo_pedido[i] != null)
							System.out.printf("[%d]........ " + bolo_pedido[i] + "\n", i);
					}
					
					// Formata uma valor de acordo com a m�scara passada -> "###,##0.00"
					DecimalFormat df = new DecimalFormat("###,##0.00");					
					//Calcula o Pre�o
					double total_pedido = sanduiche.calculcarPreco(TabelaPrecos.BOLO, distancia, TabelaTipoTransporte.KM_Moto);
					System.out.println("\nTotal do Pedido:....... R$" + df.format(total_pedido));
					
					//Calcula o tempo de entrega
				 sanduiche.calculaTempoEntrega(distancia, sanduiche.getTempo_por_km_em_minutos(), sanduiche.getTempo_preparo_sanduiche());
					
					System.out.println("Obrigado pela prefer�ncia.\n");
					System.out.println(divisor_escolhidos+ "\n");
					
					total_ingredientes = 0; //Necess�rio pq as outras op��es de menu a utilizam
					
				} else {
					System.out.println("Voc� n�o escolheu nenhum ingrediente.");
				}

				/*
				 * Como estou usando as mesmas vari�veis para para receber as op��es de menu e
				 * submenu, tenho que setar menu_opcao e flag_sair com valores diferentes e que
				 * n�o estejam em um intervalo v�lido do menu. Isso permite voltar ao Menu
				 * Principal. Caso n�o fa�a isso, ao terminar o processamento do pedido, o
				 * programa ser� encerrado. Fica a seu crit�rio se quer voltar ao menu Principal
				 * ou encerrar o programa ao processar o pedido. Pode ser declarada vari�veis
				 * deiferentes para menu e submenu como op��o para n�o usar essa l�gica descrita
				 * acima
				 */

				menu_opcao = -1;
				// flag_sair = -2;
				break;

			default:
				// Essa verifica��o impede exibir a mensagem abaixo quando for escolhida a op��o
				// de sair. Pois sair � uma op��o v�lida
				if (menu_opcao != flag_sair)
					System.out.println("Voc� selecionou uma op��o inv�lida!\n");
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
	
	

	//S� funciona no cmd
	    public static void limpaTela() {
	      
	        //Limpa a tela no windows, no linux e no MacOS
	    	System.out.println(System.getProperty("os.name").contains("Windows"));
	        if (System.getProperty("os.name").contains("Windows"))
				try {
					new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			else
				try {
					Runtime.getRuntime().exec("clear");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    
	}

}
