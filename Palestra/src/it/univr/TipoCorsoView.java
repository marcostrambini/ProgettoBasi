package it.univr;


import it.univr.database.Corso;
import it.univr.database.DataSource;
import it.univr.database.TipoCorso;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

@ManagedBean(name = "tc")
@SessionScoped
public class TipoCorsoView implements Serializable {

  // === Properties ============================================================

  private DataSource ds;
  private List<TipoCorso> listaCorsi;
  private List<Corso> corsiSelezionati;
  private Corso corsoSelezionato;
  private String nome;
  private String tipoCorso;

  // === Methods ===============================================================



public TipoCorsoView() {
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

  public String recuperaCorsi( String tipoCorso ){
    if( this.ds != null ){
      this.tipoCorso = tipoCorso;
      corsiSelezionati = ds.getCorsi( tipoCorso );
      
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

//  public String getFacolta() {
//    return facolta;
//  }
}
