package it.univr;

public class MyQuery {

	
static String qInsertStudente = " insert into studente (nome,cognome,dn,login,pass,email) "+
						 " values (?,?,?,?,?,?)";

static String qInsertDocente = " insert into docente (codice,nome,cognome) "+
						" values ((select max(codice)+1 from docente),?,?)";
	
	
}
