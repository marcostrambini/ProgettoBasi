package it.univr;

public class MyQuery {

	
static String qInsertStudente = " insert into studente (nome,cognome,dn,login,pass,email) "+
						 " values (?,?,?,?,?,?)";

static String qInsertDocente = " insert into docente (codice,nome,cognome) "+
						" values ((select max(codice)+1 from docente),?,?)";

static String qIscrizione = " insert into iscrizione (id_corso, studente_email, data_i) "+
						" values (?,?,?)";

static String countCorsi = " select count(*) from corsi";

static String deleteTable = " delete from ?";

/**
 * seleziona tutti i tipi corso
 */
static String qSelectTipiCorso = " select * from tipocorso ";

static String qSelectCorso = " select * from corsi where tipo = ? ";


public static String getqSelectCorso() {
	return qSelectCorso;
}


public static String getqSelectTipiCorso() {
	return qSelectTipiCorso;
}
	
	
}
