package it.univr;


import it.univr.database.Corso;
import it.univr.database.DataSource;
import it.univr.database.Docente;
import it.univr.database.Iscritti;
import it.univr.database.TipoCorso;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

@ManagedBean(name = "cb")
@SessionScoped
public class CorsoBean implements Serializable {

  // === Properties ============================================================

  private DataSource ds;
  private List<TipoCorso> listaCorsi;
  private List<Corso> corsiSelezionati;
  private List<Corso> dettagliCorsi;
  private List<Iscritti> listaIscritti; //
  private int numeroIscritti; //
  private String nomeCorso;
  private String obiettiviFormativi;
  private List<Docente> listaDocenti;




private Corso corsoSelezionato;
  private String nome;
  private String tipoCorso;
  
  
  public List<Iscritti> getListaIscritti() {
	return listaIscritti;
}

  public int getNumeroIscritti() {
	return numeroIscritti;
}





  // === Methods ===============================================================



public CorsoBean() {
    this.listaCorsi = null;
    this.corsoSelezionato = null;
  }

  @PostConstruct
  public void initialize() {
    try {
      this.ds = new DataSource();
    } catch( ClassNotFoundException e ){
      this.ds = null;
    }
  }
  


  public List<TipoCorso> getTipoCorsi() {
    if( this.ds != null ){
      listaCorsi = ds.getTipiCorso();
    }
    return listaCorsi;
  }

  public String recuperaDettagliCorso(int idCorso){
	  if( this.ds != null ){
		  
		  listaIscritti = ds.getIscrittiCorso(idCorso);
		  System.out.println("recupero la lista degli iscritti: "+listaIscritti.size());
		  
		  numeroIscritti = ds.getNumeroIscrittiCorso(idCorso);
		  System.out.println("recupero il numero degli iscritti: "+numeroIscritti);
		  nomeCorso = ds.getNomeCorso(idCorso);
		  System.out.println("recupero il nome del corso: "+nomeCorso);
		  obiettiviFormativi = ds.getObiettiviFormativiCorso(idCorso);
		  System.out.println("recupero gli obiettivi formativi: "+obiettiviFormativi);
		  listaDocenti = ds.getListaDocenti(idCorso);
		  System.out.println("recupero la lista dei docenti: "+listaDocenti.size());
		  
	    }
	  return "corso";
  }
  
  public String recuperaCorsi( String tipoCorso ){
    if( this.ds != null ){
      this.tipoCorso = tipoCorso;
      corsiSelezionati = ds.getCorsi( tipoCorso );
      obiettiviFormativi = corsiSelezionati.get(0).getDescrizione();
      
    }
    return "tipo_corso";
  }
    
  public String getTipoCorso() {
	return tipoCorso;
}
  
  public Corso getCorso(){
    return corsoSelezionato;
  }

public List<Corso> getCorsiSelezionati() {
	return corsiSelezionati;
}


public String getObiettiviFormativi() {
	return obiettiviFormativi;
}


public List<Corso> getDettagliCorsi() {
	return dettagliCorsi;
}

public String getNomeCorso() {
	return nomeCorso;
}

public List<Docente> getListaDocenti() {
	return listaDocenti;
}
}
