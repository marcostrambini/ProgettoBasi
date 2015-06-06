package it.univr.database;

import java.sql.Date;
import java.util.List;

public class Docente {


	// === Properties ============================================================

	private int codice;
	private String nome;
	private String cognome;



	// === Methods ===============================================================

	public Docente() {

		codice = 0;
		nome = null;
		cognome = null;
		
	}



	public int getCodice() {
		return codice;
	}



	public void setCodice(int codice) {
		this.codice = codice;
	}



	public String getNome() {
		return nome;
	}



	public void setNome(String nome) {
		this.nome = nome;
	}



	public String getCognome() {
		return cognome;
	}



	public void setCognome(String cognome) {
		this.cognome = cognome;
	}






}
