package models;

import java.io.Serializable;

public class Aluno implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7894756514523362642L;
	private int ra;

	public Aluno(int ra) {
		this.ra = ra;
	}

	public int getRa() {
		return ra;
	}

	public void setRa(int ra) {
		this.ra = ra;
	}
	public int hashCode() {
		return this.ra;
	}
	
	public String toString() {
		return "Aluno RA: " + this.getRa() + "\n"; 
	}
}
