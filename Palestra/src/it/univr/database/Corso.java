package it.univr.database;

import java.sql.Date;
import java.util.List;

/**
 * classe che rappresenta il corso
 * @author Mago
 *
 */
public class Corso {

	private int id;
	private String nome_corso;
	private String nome_docente;
	private String cognome_docente;
	private int numero_iscritti;
	private Date data_i;
	private Date data_f;
	private String tipo;
	private String descrizione;


	public Corso() {

		id = 0;
		nome_corso = null;
		data_i = null;
		data_f = null;
		tipo = null;
		descrizione = null;

	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}





	public Date getData_i() {
		return data_i;
	}


	public void setData_i(Date data_i) {
		this.data_i = data_i;
	}


	public Date getData_f() {
		return data_f;
	}


	public void setData_f(Date data_f) {
		this.data_f = data_f;
	}


	public String getTipo() {
		return tipo;
	}


	public void setTipo(String tipo) {
		this.tipo = tipo;
	}


	public String getDescrizione() {
		return descrizione;
	}


	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}


	public String getNome_corso() {
		return nome_corso;
	}


	public void setNome_corso(String nome_corso) {
		this.nome_corso = nome_corso;
	}


	public String getNome_docente() {
		return nome_docente;
	}


	public void setNome_docente(String nome_docente) {
		this.nome_docente = nome_docente;
	}


	public String getCognome_docente() {
		return cognome_docente;
	}


	public void setCognome_docente(String cognome_docente) {
		this.cognome_docente = cognome_docente;


	}



	public int getNumero_iscritti() {
		return numero_iscritti;
	}


	public void setNumero_iscritti(int numero_iscritti) {
		this.numero_iscritti = numero_iscritti;
	}


}
