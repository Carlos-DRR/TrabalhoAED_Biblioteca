package models;

import java.io.Serializable;


public class Lista<T> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idLista;
	private int tamanho;
	private No<T> inicio;
	private No<T> fim;
	
	
	public Lista(int idLista) {
		this.idLista = idLista;
		this.criaListaVazia();
	}
	public No<T> getInicio(){
		return this.inicio;
	}
	public int getIdLista() {
		return this.idLista;
	}
	private void criaListaVazia() {
		this.tamanho = 0;
		this.inicio = null;
		this.fim = null;
	}
	public boolean vazia() {
		return this.inicio == null;
	}
	public int getTamanho() {
		return this.tamanho;
	}

	public void insereFim(T obj) {
		No <T> no = new No <T>(obj, null);
		if(this.vazia()) {
			this.inicio = no;
		}else {
			this.fim.setProx(no);
		}
		
		this.fim = no;
		this.tamanho++;
		
	}
	public void insereOrdenado(T obj) {
		No<T> no = new No<T>(obj, null);
		
		if(this.vazia()) {
			this.fim = no;
			this.inicio = no;
		}else{
			No<T> p = this.inicio;
			No<T> ant = null;
			while(p != null && p.getObject().hashCode() < obj.hashCode()) {
				ant = p;
				p = p.getProx();
			}
			
			if(p == null) {
				ant.setProx(no); // ant é fim
				this.fim = no;
			}else {
				if(p == this.inicio) this.inicio = no;
				else ant.setProx(no);
				no.setProx(p);
			} 
			
		}
		this.tamanho++;	
	}

	public T getObjeto(T obj) {		
		if(!this.vazia()) {
			No<T> p = this.inicio;
			while(p != null && p.getObject().hashCode()!= obj.hashCode()) {
				p = p.getProx();
			}
			if(p == null) return null;
			else return p.getObject();
			
		}else System.out.println("Vazia");
		return null;
		
	}
	
	public T getObjeto(int hash) {

		if(!this.vazia()) {
			No<T> p = this.inicio;
			while(p != null && p.getObject().hashCode()!= hash) {
				p = p.getProx();
			}
			if(p == null) return null;
			else return p.getObject();
			
		}else //System.out.println("Lista vazia");
		return null;
		
	}
	
	public boolean existeObjeto(T obj) {		
		if(!this.vazia()) {
			No<T> p = this.inicio;
			while(p != null && p.getObject().hashCode() != obj.hashCode()) {
				p = p.getProx();
			}
			if(p == null) return false;
			else return true;
			
		}else System.out.println("Vazia");
		return false;
		
	}
	
	public boolean existeObjeto(int hashCode) {		
		if(!this.vazia()) {
			No<T> p = this.inicio;
			while(p != null && p.getObject().hashCode() != hashCode) {
				p = p.getProx();
			}
			if(p == null) return false;
			else return true;
			
		}else System.out.println("Vazia");
		return false;
		
	}
	
	public T removeInicio() {
		T l = null;
		if(!this.vazia()) {
			No<T> aux = this.inicio;
			this.inicio = this.inicio.getProx();
			l = aux.getObject();
			aux = null;
			this.tamanho--;
		}else System.out.println("Vazia");
		return l;
	}
	//se não encontrou retorna null
	public T removePorHash(int hash) {
		if(!this.vazia()) {
			No<T> p = this.inicio;
			No<T> ant = null;
			while(p!= null && p.getObject().hashCode() != hash) {
				ant = p;
				p = p.getProx();
			}
			
			if(p != null) {
				if(p == this.inicio) this.inicio = p.getProx();
				else ant.setProx(p.getProx());
				if(p == this.fim) this.fim = ant;
				T obj = p.getObject();
				p = null;
				this.tamanho--;
				return obj;
			}else return null; //não encontrou
			
		}else return null;
	}
	public T removeFim() {
		T l;
		if(!this.vazia()) {
			No<T> p = this.inicio;
			while(p.getProx() != null && p.getProx() != this.fim) {
				p = p.getProx();
			}
			No<T> aux = this.fim;
			if(p.getProx() == null) {
				this.fim = null;
				this.inicio = null;
			}else {
				p.setProx(aux.getProx());
				this.fim = p;
			}
			l = aux.getObject();
			aux = null;
			this.tamanho--;
			return l;
		}else System.out.println("Vazia");
		return null;
	}
	
	public int hashCode() {
		return this.idLista;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		if(!this.vazia()) {
			No<T> p = this.inicio;
			while(p!=null) {
				sb.append(p.getObject().toString());
				p = p.getProx();
			}

		}else sb.append("Vazia\n");
		
		return sb.toString();
	}
	
}
