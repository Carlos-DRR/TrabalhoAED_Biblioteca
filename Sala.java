package models;

import java.io.Serializable;

public class Sala implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2582482693475174523L;
	private int salaId;
	private Aluno aluno;
	private Pilha<Livro> livros;
	boolean vazia;
	
	public Sala(int idSala) {
		this.salaId = idSala;
		this.vazia = true;
		this.livros = new Pilha<Livro>(1);
	}
	
	public Sala(Aluno aluno, Pilha<Livro> livros) {
		this.aluno = aluno;
		this.livros = livros;
		this.vazia = false;
		this.livros = new Pilha<Livro>(1);
	}
	public boolean vazia() {
		return this.vazia;
	}
	public Aluno getAluno() {
		return aluno;
	}
	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
		this.vazia = false;
	}
	public void insereLivroNaPilha(Livro livro) {
		this.livros.empilha(livro);
	}
	public Livro removeLivroDaPilha() {
		return this.livros.desempilha();
	}
	public Pilha<Livro> esvazia() {
		this.aluno = null;
		this.vazia = true;
		return this.livros;
	}
	public Pilha<Livro> getLivros() {
		return this.livros;
	}
	public int hashCode() {
		return this.salaId;
	}
	public String toString() {
		StringBuilder sb = new StringBuilder();
		String ra = this.aluno == null ? "nenhum":String.valueOf(this.aluno.getRa());
		sb.append("A sala com id "+ this.salaId +" esta ocupado pelo aluno com RA: " + ra + "\n");
		sb.append("A sala contém os livros: \n" + this.livros.toString() + "\n");
		return sb.toString();
	}
}
