package it.univr;


import it.univr.database.Corso;
import it.univr.database.DataSource;
import it.univr.database.Docente;
import it.univr.database.Iscritti;
import it.univr.database.Materiale;
import it.univr.database.ProgrammazioneCorso;
import it.univr.database.TipoCorso;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
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
  private List<Date> periodoSvolgimento;
  private ArrayList<ProgrammazioneCorso> programmazioneCorso;
  private ArrayList<Materiale> materialeCorso;
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
    this.periodoSvolgimento = null;
    this.programmazioneCorso = null;
   
    
  }

  @PostConstruct
  public void initialize() {
    try {
      this.ds = new DataSource();
    } catch( ClassNotFoundException e ){
      this.ds = null;
    }
  }
  

  public Date getDataInizialeCorso(){
	  return periodoSvolgimento.get(0);
  }
  
  public Date getDataFinaleCorso(){
	  return periodoSvolgimento.get(1);
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
		  periodoSvolgimento = ds.getPeriodoSvolgimentoCorso(idCorso);
		  System.out.println("recupero il periodo di svolgimento dal "+periodoSvolgimento.get(0)+" al "+periodoSvolgimento.get(1));
		  programmazioneCorso = ds.getProgrammazioneCorso(idCorso);
		  System.out.println("Recupero la programmazione settimanale, size = "+programmazioneCorso.size());
		  for(int i=0;i<programmazioneCorso.size();i++)
			  System.out.println("il "+programmazioneCorso.get(i).getG_sett()+" dalle "+programmazioneCorso.get(i).getOra_i()+" alle "+programmazioneCorso.get(i).getOra_f());
		  materialeCorso = ds.getMaterialeCorso(idCorso);
		  
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

public List<ProgrammazioneCorso> getProgrammazioneCorso() {
	return programmazioneCorso;
}

public ArrayList<Materiale> getMaterialeCorso() {
	return materialeCorso;
}
}
