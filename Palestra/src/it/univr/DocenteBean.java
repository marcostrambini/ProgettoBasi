package it.univr;


import it.univr.database.DataSource;
import it.univr.database.Docente;

import javax.annotation.PostConstruct;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import java.io.Serializable;
import java.util.List;

/**
 * bean per la gestione dei docenti
 * @author Mago
 *
 */
@ManagedBean(name = "db")
@SessionScoped
public class DocenteBean implements Serializable {



  private DataSource ds;
  private List<Docente> listaDocenti;
  

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


}
