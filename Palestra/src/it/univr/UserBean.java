package it.univr;

import it.univr.database.Corso;
import it.univr.database.DataSource;
import it.univr.database.Materiale;
import it.univr.database.Utente;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;    
import javax.faces.bean.SessionScoped; 

/**
 * bean per la gestione degli studenti
 * @author Mago
 *
 */
@ManagedBean(name="user")
@SessionScoped
public class UserBean implements Serializable {

	private DataSource ds;
	private String nome;
	private String cognome;
	private Date dn;
	private String email;
	private String password;
	private ArrayList<Corso> listaCorsi;
	private ArrayList<Materiale> listaMateriale;
	private boolean loggedIn;
	private ArrayList<Utente> utente;
	private ArrayList<Utente> studenti;
	

	@PostConstruct
	public void initialize() {
		try {
			this.ds = new DataSource();
		} catch( ClassNotFoundException e ){
			this.ds = null;
		}
	}


	public String getName() { return nome ; }   
	public void setName(String newValue) { nome = newValue; }

	public String getPassword() { return password;	}
	public void setPassword(String newValue){ password = newValue;	}   

	public String getNome() {return nome;}
	public String getCognome() {return cognome;	}

	public Date getDn() {return dn;	}
	public void setDn(Date dn) {	this.dn= dn;	}

	public String getEmail() {	return email;}
	public void setEmail(String email) { this.email = email; }

	public void setNome(String nome) {	this.nome = nome;	}
	public void setCognome(String cognome) { this.cognome = cognome;	}


	public ArrayList<Corso> getListaCorsi() {
		return listaCorsi;
	}


	public ArrayList<Utente> getUtente() {
		return utente;
	}


	public boolean isLoggedIn() {
		return loggedIn;
	}

	/**
	 * metodo che recupera la lista di corsi e il materiale a disposizione per uno specifico studente
	 */
	public void getListaCorsiUtente(){

		listaCorsi = ds.getListaCorsiUtente(email);
		listaMateriale = ds.getMaterialeUtente(email);

	}


	public ArrayList<Materiale> getListaMateriale() {
		return listaMateriale;
	}


	/**
	 * metodo per il controllo della login
	 * @return
	 */
	public String checkLogin(){
		if (email.equals("admin@admin.it")&&password.equals("admin"))
			return "admin";
		else{


			utente = ds.checkLogin(email,password);
			if(utente!=null){
				getListaCorsiUtente();
				loggedIn=true;
				return "studente";
			} else{

				loggedIn=false;
				return "loginDenied";
			}

		}
	}

	/**
	 * metodo che recupera dal ds tutti gli studenti
	 * @return
	 */
    public ArrayList<Utente> recuperaStudenti(){
    	studenti = ds.getStudenti();
    	return studenti;
    	
    }


	public void setStudenti(ArrayList<Utente> studenti) {
		this.studenti = studenti;
	}


	public ArrayList<Utente> getStudenti() {
		return ds.getStudenti();
	}

	/**
	 * metodo che recupera il numero degli iscritti alla palestra
	 * @return
	 */
	public int getNumeroStudenti() {
		return ds.getNumeroIscritti();
	}



}


