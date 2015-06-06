package it.univr;


import it.univr.database.Corso;
import it.univr.database.DataSource;
import it.univr.database.Docente;
import it.univr.database.Iscritti;
import it.univr.database.TipoCorso;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

@ManagedBean(name = "db")
@ApplicationScoped
//@SessionScoped
public class DocenteBean implements Serializable {

  // === Properties ============================================================

  private DataSource ds;
  private List<Docente> listaDocenti;
  
//  private List<Corso> corsiSelezionati;
//  private List<Corso> dettagliCorsi;
//  private List<Iscritti> listaIscritti;
//  private String obiettiviFormativi;
//  private Corso corsoSelezionato;
//  private String nome;
//  private String tipoCorso;
//  
  
  
  
//  public List<Iscritti> getListaIscritti() {
//	return listaIscritti;
//}



  // === Methods ===============================================================



  public DocenteBean() {
    this.listaDocenti = null;
//    this.corsoSelezionato = null;
  }

  @PostConstruct
  public void initialize() {
    try {
      this.ds = new DataSource();
    } catch( ClassNotFoundException e ){
      this.ds = null;
    }
  }
  


  public List<Docente> getDocenti(int idCorso) {
    if( this.ds != null ){
    	listaDocenti = ds.getListaDocenti( idCorso);
    }
    return listaDocenti;
  }

public List<Docente> getListaDocenti() {
	return listaDocenti;
}

public void setListaDocenti(List<Docente> listaDocenti) {
	this.listaDocenti = listaDocenti;
}

//  public String recuperaIscritti(int idCorso){
//	  if( this.ds != null ){
//		  listaIscritti = ds.getIscrittiCorso(idCorso);
//		  dettagliCorsi = ds.getDettagliCorsi(idCorso);
//		  
//	    }
//	  return "corso";
//  }
//  
//  public String recuperaCorsi( String tipoCorso ){
//    if( this.ds != null ){
//      this.tipoCorso = tipoCorso;
//      corsiSelezionati = ds.getCorsi( tipoCorso );
//      obiettiviFormativi = corsiSelezionati.get(0).getDescrizione();
//      
//    }
//    return "tipo_corso";
//  }
//    
//  public String getTipoCorso() {
//	return tipoCorso;
//}
//  
//  public Corso getCorso(){
//    return corsoSelezionato;
//  }
//
//public List<Corso> getCorsiSelezionati() {
//	return corsiSelezionati;
//}
//
//
//public String getObiettiviFormativi() {
//	return obiettiviFormativi;
//}
////  public String getFacolta() {
////    return facolta;
////  }
//
//public List<Corso> getDettagliCorsi() {
//	return dettagliCorsi;
//}
}
