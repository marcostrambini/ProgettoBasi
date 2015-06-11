package it.univr.database;

import java.sql.Date;

/**
 * classe che rappresenta lo studente
 * @author Mago
 *
 */
public class Utente {
	private String nome;
	private String cognome;
	private java.util.Date dn;
	private String mail;
	private String login;
	private String password;
	
	/**
	 * costruttore di default
	 */
	public Utente(){
		this.nome = null;
		this.cognome = null;
		this.dn = null;
		this.mail = null;
		this.login = null;
		this.password = null;
	}
	
	
	/**
	 * costruttore personalizzato utlizzato dal simulatore
	 * @param nome
	 * @param cognome
	 * @param dateRandom
	 * @param mail
	 * @param login
	 * @param pwd
	 */
	public Utente(String nome, String cognome, java.util.Date dateRandom,
			String mail, String login, String pwd) {
		this.nome = nome;
		this.cognome = cognome;
		this.dn = dateRandom;
		this.mail = mail;
		this.login = login;
		this.password = pwd;
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
	public java.util.Date getDn() {
		return dn;
	}
	public void setDn(Date dn) {
		this.dn = dn;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	

}
