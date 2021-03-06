package it.univr.database;

import java.sql.Date;

/**
 * classe che rappresenta gli iscritti
 * @author Mago
 *
 */
public class Iscritti {
	
	  private String nome;
	  private String cognome;
	  private Date data_i;
	 

	  public Iscritti() {
				 
		  nome = null;
		  data_i = null;
		  cognome = null;
		
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


	public Date getData_i() {
		return data_i;
	}


	public void setData_i(Date data_i) {
		this.data_i = data_i;
	}


}
