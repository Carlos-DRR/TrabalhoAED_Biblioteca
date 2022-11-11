package models;

import java.io.Serializable;

public class No<T> implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;
	private T object;
	private No<T> prox;
	
	public No(T object, No<T> prox) {
		this.object = object;
		this.prox = prox;
	}
	
	public T getObject() {
		return object;
	}
	
	public void setObject(T object) {
		this.object = object;
	}
	
	public No<T> getProx() {
		return prox;
	}
	
	public void setProx(No<T> prox) {
		this.prox = prox;
	}
	
}
