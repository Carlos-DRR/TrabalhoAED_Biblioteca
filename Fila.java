package models;

public class Fila<T> extends Lista<T> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4275148038757219591L;
	public Fila(int idFila) {
		super(idFila);
	}
	public void enfileira(T livro) {
		super.insereFim(livro);
	}
	public T desenfileira() {
		return super.removeInicio();
	}
	public String toString() {
		return super.toString();
	}
}
