package it.univr.database;

import java.sql.Date;
import java.util.List;

public class _CorsoDettaglio {
	
	
	 // === Properties ============================================================

	  private int id;
	  private String nome_corso;
	  private List<String> docente;
	  private int numero_iscritti;
	  private Date data_i;
	  private Date data_f;
	 
	

	  // === Methods ===============================================================

	  public _CorsoDettaglio() {
		  
		  id = 0;
		  nome_corso = null;
		  docente = null;
		  numero_iscritti = 0;
		  data_i = null;
		  data_f = null;
		  
	
	  }


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Date getData_i() {
		return data_i;
	}


	public void setData_i(Date data_i) {
		this.data_i = data_i;
	}


	public Date getData_f() {
		return data_f;
	}


	public void setData_f(Date data_f) {
		this.data_f = data_f;
	}
	

	public String getNome_corso() {
		return nome_corso;
	}


	public void setNome_corso(String nome_corso) {
		this.nome_corso = nome_corso;
	}


	public List<String> getDocente() {
		return docente;
	}


	public void setDocente(List<String> docente) {
		this.docente = docente;
	}


	public int getNumero_iscritti() {
		return numero_iscritti;
	}


	public void setNumero_iscritti(int numero_iscritti) {
		this.numero_iscritti = numero_iscritti;
	}



}
