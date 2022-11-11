package models;

import java.io.Serializable;

public class Livro implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3L;
	private int codigo;
	private String titulo;
	private String autor;
	private int estante;
	private int prateleira;
	public Livro() {
		
	}
	public Livro(int codigo) {
		this.codigo = codigo;
	}
	public Livro(int codigo, String titulo, String autor, int estante, int prateleira) {
		this.codigo = codigo;
		this.titulo = titulo;
		this.autor = autor;
		this.estante = estante;
		this.prateleira = prateleira;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public int getEstante() {
		return estante;
	}
	public void setEstante(int estante) {
		this.estante = estante;
	}
	public int getPrateleira() {
		return prateleira;
	}
	public void setPrateleira(int prateleira) {
		this.prateleira = prateleira;
	}
	
	public String toString() {
		//int estante, int prateleira
		StringBuilder sb = new StringBuilder();
		sb.append("Livro codigo: " + Integer.toString(this.codigo) + "\n" 
				+ "\tTitulo: " + this.titulo + "\n" 
				+ "\tAutor: " + this.autor + "\n"
				+ "\tEstante: " + Integer.toString(this.estante) + "\n"
				+ "\tPrateleira: " + Integer.toString(this.prateleira) + "\n");
		return sb.toString();
	}
	public int hashCode() {
		return this.codigo;
	}
}
