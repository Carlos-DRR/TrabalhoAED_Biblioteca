package models;

import java.util.Scanner;

public class Menu {
	private Controller c;
	
	private void printCarregarDados() {
		System.out.println("Digite o número correspondente da opção desejada");
		System.out.println("[1] - Ler estantes do arquivo texto");
		System.out.println("[2] - Carregar dados salvos");
		System.out.println("[-] - Sair");
	}
	private void printOpcoes() {
		System.out.println("Digite o número correspondente da opção desejada");
		System.out.println("[1] - Inserir livro");
		System.out.println("[2] - Retirar livro");
		System.out.println("[3] - Buscar endereço do livro");
		System.out.println("[4] - Locar sala");
		System.out.println("[5] - Liberar sala");
		System.out.println("[6] - Emprestar livro");
		System.out.println("[7] - Imprimir mapa de estantes");
		System.out.println("[8] - Imprimir mapa de salas");
		System.out.println("[9] - Imprimir fila de espera de sala");
		System.out.println("[10] - Salvar modificações em arquivo binário");
		System.out.println("[-] - Sair");
	}
	public Menu () {
		this.printCarregarDados();
		Scanner sc = new Scanner(System.in);
		int op = sc.nextInt();
		sc.nextLine();
		switch(op) {
			case 1:{
				System.out.println("Digite o endereço e o nome do arquivo texto no formato: \"endereco\\nome.txt\"");
				String path = sc.nextLine();
				this.c = new Controller(path);
				break;
			}
			case 2:{
				this.c = new Controller();
				break;
			}
			default:{
				System.exit(0);
				break;
			}
		}
	}
	public void limpaConsole(){
		/*
		try {
			Runtime.getRuntime().exec("C:\\WINDOWS\\system32\\cmd.exe cls");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
	public void executaOpcoes() {
		this.printOpcoes();
		Scanner sc = new Scanner(System.in);
		int op = sc.nextInt();
		sc.nextLine();
		while(op < 11) {
			switch(op) {
			case 1:{
				System.out.println("Digite o código, nome, autor, estante e prateleira do livro");
				int codigo = sc.nextInt();
				sc.nextLine();
				String nomeLivro = sc.nextLine();
				String autor = sc.nextLine();
				int estante = sc.nextInt();
				sc.nextLine();
				int prateleira = sc.nextInt();
				sc.nextLine();
				this.c.inserirLivro(codigo, nomeLivro, autor, estante, prateleira);
				
				break;
			}
			case 2:{
				System.out.println("Digite o código do livro para remover");
				int codigo = sc.nextInt();
				sc.nextLine();
				this.c.removerLivro(codigo);
				
				break;
			}
			case 3:{
				System.out.println("Digite o código do livro para buscar o endereco");
				int codigo = sc.nextInt();
				sc.nextLine();
				System.out.println(this.c.getEnderecoLivro(codigo));
				sc.nextLine(); // espera
				
				break;
			}
			case 4:{
				System.out.println("Digite o RA do aluno para locar uma sala disponível");
				int RA = sc.nextInt();
				sc.nextLine();
				this.c.locarSala(RA);
				
				break;
			}
			case 5:{
				System.out.println("Digite o RA do aluno que está na sala para liberá-la");
				int RA = sc.nextInt();
				sc.nextLine();
				this.c.liberarSala(RA);
				
				break;
			}
			case 6:{
				System.out.println("Digite o RA do aluno e o codigo do livro para emprestar");
				int RA = sc.nextInt();
				sc.nextLine();
				int codigo = sc.nextInt();
				sc.nextLine();
				this.c.emprestarLivro(RA, codigo);
				
				break;
			}
			case 7:{
				System.out.println("Mapa de estantes:");
				this.c.printEstantes();
				System.out.println("Enter para continuar");
				sc.nextLine();
				
				break;
			}
			case 8:{
				System.out.println("Mapa de salas:");
				this.c.printSalas();
				System.out.println("Enter para continuar");
				sc.nextLine();
				
				break;
			}
			case 9:{
				System.out.println("Fila de espera de sala:");
				this.c.printFilaAlunos();
				System.out.println("Enter para continuar");
				sc.nextLine();
				
				break;
			}
			case 10:{
				this.c.salvarDados();
				break;
			}
			default:{
				System.exit(0);
			}
			
		}
			this.limpaConsole();
			this.printOpcoes();
			op = sc.nextInt();
			sc.nextLine();
			
		}

	}
}
