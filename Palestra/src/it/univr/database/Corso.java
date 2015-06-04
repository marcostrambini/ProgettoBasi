package it.univr.database;

import java.sql.Date;

public class Corso {
	
	
	 // === Properties ============================================================

	  private int id;
	  private String nome;
	  private Date data_i;
	  private Date data_f;
	  private String tipo;
	  private String descrizione;
	

	  // === Methods ===============================================================

	  public Corso() {
		  
		  id = 0;
		  nome = null;
		  data_i = null;
		  data_f = null;
		  tipo = null;
		  descrizione = null;
	
	  }


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
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


	public String getTipo() {
		return tipo;
	}


	public void setTipo(String tipo) {
		this.tipo = tipo;
	}


	public String getDescrizione() {
		return descrizione;
	}


	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

}
