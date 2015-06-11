package it.univr.database;
/**
 * classe che rappresenta le tipologie dei corsi
 * @author Mago
 *
 */
public class TipoCorso {



	  private String nome;
	  private String descrizione;
	


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
