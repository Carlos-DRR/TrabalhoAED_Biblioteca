package models;

public class Pilha<T> extends Lista<T>{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6101516981812610240L;
	public Pilha(int idPilha) {
		super(idPilha);
	}
	
	public void empilha(T obj) {
		super.insereFim(obj);
	}
	public T desempilha() {
		return super.removeFim();
	}
	public String toString() {
		return super.toString();
	}
}
