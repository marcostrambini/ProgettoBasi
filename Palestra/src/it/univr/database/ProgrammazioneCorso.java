package it.univr.database;


import java.sql.Time;

/**
 * classe che rappresenta la programmazione dei corsi
 * @author Mago
 *
 */
public class ProgrammazioneCorso {


	private String g_sett;
	private Time ora_i;
	private Time ora_f;

	public ProgrammazioneCorso() {


		this.g_sett = null;
		this.ora_i = null;
		this.ora_f = null;
		
	}



	public String getG_sett() {
		return g_sett;
	}



	public void setG_sett(String g_sett) {
		this.g_sett = g_sett;
	}



	public Time getOra_i() {
		return ora_i;
	}



	public void setOra_i(Time ora_i) {
		this.ora_i = ora_i;
	}



	public Time getOra_f() {
		return ora_f;
	}



	public void setOra_f(Time ora_f) {
		this.ora_f = ora_f;
	}






}
