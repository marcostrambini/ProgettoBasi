package it.univr.database;

public class TipoCorso {

	 // === Properties ============================================================


	  private String nome;
	  private String descrizione;
	

	  // === Methods ===============================================================

	  public TipoCorso() {
	  
		  nome = null;
		  descrizione = null;
	
	  }


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getDescrizione() {
		return descrizione;
	}


	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	
}
