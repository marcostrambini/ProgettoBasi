package it.univr;

import java.sql.Date;

public class Utente {
	private String nome;
	private String cognome;
	private String dn;
	private String mail;
	private String login;
	private String password;
	
	public Utente(String nome, String cognome, String dn, String mail, String login, String password){
		this.nome = nome;
		this.cognome = cognome;
		this.dn = dn;
		this.mail = mail;
		this.login = login;
		this.password = password;
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
	public String getDn() {
		return dn;
	}
	public void setDn(String dn) {
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
