package models;
//refazer as implementações de salas e fila de alunos
public class Controller {
	private Lista<Lista<Lista<Livro>>> estantes;
	private Lista<Sala> salas;// em cada sala tem uma pilha
	private Fila<Aluno> filaAlunos;
	private FileHandler fl;
	private String txtPath="";
	private String estantesPathBin = "estantes.out";
	private String salasPathBin = "salas.out";
	private String filaPathBin = "fila.out";
	
	public Controller(String path) {
		this.txtPath = path;
		this.fl = new FileHandler(this.txtPath);
		this.carregarArquivoTxt();
		this.salas = new Lista<Sala>(0);
		this.inicializaSalas(3);
		this.filaAlunos = new Fila<Aluno>(0);
	}
	
	public Controller() {
		this.carregarArquivosBinarios();
	}
	
	public void salvarDados() {
		this.fl.salvarArquivoBinario(this.estantesPathBin, this.estantes);
		this.fl.salvarArquivoBinario(this.salasPathBin, this.salas);
		this.fl.salvarArquivoBinario(this.filaPathBin, this.filaAlunos);
	}
	@SuppressWarnings("unchecked")
	public void carregarArquivosBinarios() {
		this.estantes = (Lista<Lista<Lista<Livro>>>) this.carregarArquivoBinario(this.estantesPathBin);
		this.salas = (Lista<Sala>) this.carregarArquivoBinario(this.salasPathBin);
		this.filaAlunos = (Fila<Aluno>) this.carregarArquivoBinario(this.filaPathBin);	
	}
	public void carregarArquivoTxt() {
		//System.out.println("aqui");
		this.estantes = fl.carregarArquivoTxtMemoria();
	}
	public Object carregarArquivoBinario(String path) {
		this.fl = new FileHandler(path);
		return fl.lerArquivoBinario(path);
	}
	private void inicializaSalas(int qtdSalas) {
		for(int i = 0; i < qtdSalas; i++) {
			Sala s = new Sala(i);
			this.salas.insereFim(s);
		}
	}
	

	public void inserirLivro(int codigo, String titulo, String autor, int estante, int prateleira) {
		Livro livro = new Livro(codigo, titulo, autor, estante, prateleira);
		Lista<Lista<Livro>> lista_estante = this.estantes.getObjeto(estante);
		Lista<Livro> lista_prateleira;
		if(lista_estante != null) {
			lista_prateleira = lista_estante.getObjeto(prateleira);
			if(lista_prateleira != null) {
				lista_prateleira.insereOrdenado(livro);
			}else System.out.println("prateleira inexistente");
		} else System.out.println("estante inexistente");
	}
	
	public void inserirLivro(Livro livro) {
		int estante = livro.getEstante();
		int prateleira = livro.getPrateleira();
		Lista<Lista<Livro>> lista_estante = this.estantes.getObjeto(estante);
		Lista<Livro> lista_prateleira;
		if(lista_estante != null) {
			lista_prateleira = lista_estante.getObjeto(prateleira);
			if(lista_prateleira != null) {
				lista_prateleira.insereOrdenado(livro);
			}else System.out.println("prateleira inexistente");
		} else System.out.println("estante inexistente");
	}
	public boolean removerLivro(int codigo) {
		No<Lista<Lista<Livro>>> estante = this.estantes.getInicio();
		while(estante != null) {
			No<Lista<Livro>> prateleira = estante.getObject().getInicio();
			while(prateleira != null) {
				Lista <Livro> l = prateleira.getObject();
				Livro livro = l.removePorHash(codigo);
				if(livro != null) {
					livro = null;
					return true;
				}
				prateleira = prateleira.getProx();
			}
			estante = estante.getProx();
		}
		return false;
	}
	
	public Livro getLivro(int codigo) {
		No<Lista<Lista<Livro>>> estante = this.estantes.getInicio();
		while(estante != null) {
			No<Lista<Livro>> prateleira = estante.getObject().getInicio();
			while(prateleira != null) {
				Lista <Livro> l = prateleira.getObject();
				Livro livro = l.removePorHash(codigo);
				if(livro != null) return livro;
				prateleira = prateleira.getProx();
			}
			estante = estante.getProx();
		}
		return null;
	}
	
	public String getEnderecoLivro(int codigo) {
		StringBuilder sb = new StringBuilder();
		No<Lista<Lista<Livro>>> estante = this.estantes.getInicio();
		while(estante != null) {
			No<Lista<Livro>> prateleira = estante.getObject().getInicio();
			while(prateleira != null) {
				Lista <Livro> l = prateleira.getObject();
				Livro livro = l.getObjeto(codigo);
				if(livro != null) {
					sb.append("Livro disponível na estante " + livro.getEstante() + " prateleira " + livro.getPrateleira());
					sb.append("\n");
					return sb.toString();
				}
				prateleira = prateleira.getProx();
			}
			estante = estante.getProx();
		}
		return "livro indisponível";
	}
	public void print_estantes() {
		System.out.println(this.estantes.toString());
	}
	public Sala getSalaVazia() {
		No<Sala> no = this.salas.getInicio();
		Sala sala = null;
		while(no != null) {
			sala = no.getObject();
			if(sala.vazia()) return sala;
			no = no.getProx();
		}
		return null;
	}
	public void locarSala(int ra) {
		Aluno aluno = new Aluno(ra);
		Sala sala = this.getSalaVazia();
		if(sala == null) {
			this.filaAlunos.enfileira(aluno);
		}else {
			sala.setAluno(aluno);
		}
	}
	
	public Sala getSalaComAluno(int ra) {
		No<Sala> no = this.salas.getInicio();
		Sala sala = null;
		while(no != null) {
			sala = no.getObject();
			if(sala.getAluno().getRa() == ra) return sala;
			no = no.getProx();
		}
		return null;
	}
	public void emprestarLivro(int ra, int codigoLivro) {
		Sala salaComAluno = getSalaComAluno(ra);
		if(salaComAluno != null) {
			Livro livroParaEmprestar = this.getLivro(codigoLivro);
			if(livroParaEmprestar != null) {
				salaComAluno.insereLivroNaPilha(livroParaEmprestar);
			}else System.out.println("livro indisponível");
		}else System.out.println("O aluno com o RA informado não está nas salas");
	}
	
	private void insereDaPilhaParaEstante(Pilha<Livro> pilhaDaSala) {
		int tamanhoPilha = pilhaDaSala.getTamanho();
		for(int i = 0 ; i < tamanhoPilha; i++) {
			Livro l = pilhaDaSala.desempilha();
			this.inserirLivro(l);
		}
	}
	public void liberarSala(int ra) {
		Sala salaComAluno = getSalaComAluno(ra);
		if(salaComAluno != null) {
			Pilha<Livro> pilhaDaSala = salaComAluno.esvazia();
			this.insereDaPilhaParaEstante(pilhaDaSala);
			//inserir novo aluno na sala vazia
			int raNovoLocador = this.filaAlunos.desenfileira().getRa();
			this.locarSala(raNovoLocador);
			
		}else System.out.println("O aluno com o RA informado não está nas salas");
	}
	public void printSalas() {
		System.out.println("Salas:");
		System.out.println(this.salas.toString());
	}
	public void printFilaAlunos() {
		System.out.println("Fila de alunos:");
		System.out.println(this.filaAlunos.toString());
		
	}
	
	public void printEstantes() {
		StringBuilder sb = new StringBuilder();
		No<Lista<Lista<Livro>>> estante = this.estantes.getInicio();
		while(estante != null) {
			sb.append("Estante: " + estante.getObject().hashCode()+ "\n");
			Lista <Lista<Livro>> prateleira = estante.getObject();
			sb.append(prateleira.toString() + "\n");
			estante = estante.getProx();
		}
		System.out.println(sb.toString());
	}
}
