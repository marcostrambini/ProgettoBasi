package it.univr.database;

/**
 * classe che contiene tutte le query utilizzate nel progetto
 * @author Mago
 *
 */
public class MyQuery {

/**
 * query di inserimento dello studente nella base di dati
 */
static String qInsertStudente = " insert into studente (nome,cognome,dn,login,pass,email) "+
						 " values (?,?,?,?,?,?)";
/**
 * query di inserimento del docente nella base di dati
 */
static String qInsertDocente = " insert into docente (codice,nome,cognome) "+
						" values ((select max(codice)+1 from docente),?,?)";
/**
 * query per l'iscrizione di uno studente ad un corso
 */
static String qIscrizione = " insert into iscrizione (id_corso, studente_email, data_i) "+
						" values (?,?,?)";

/**
 * query che conta il numero di corsi
 */
static String countCorsi = " select count(*) from corsi";

/**
 * query che conta il numero di iscritti ad un determinato corso
 */
static String countIscrittiCorso = " select count(*) from iscrizione where id_corso = ? ";

/**
 * query di cancellazione di una tabella generica
 */
static String deleteTable = " delete from ?";

/**
 * seleziona tutti i tipi corso
 */
static String qSelectTipiCorso = " select * from tipocorso ";

/**
 * query che seleziona i corsi di un determinato tipo
 */
static String qSelectCorso = " select c.id,c.nome as nome_corso ,c.tipo,c.descrizione,dc.nome as nome_docente ,dc.cognome as cognome_docente,c.data_i,c.data_f "
							+ " from corsi c join docenza dz on c.id=dz.id_corso "
							+ " join docente dc on dz.cod_docente = dc.codice "
							+ " where dz.responsabile = true and c.tipo = ? ";

/**
 * query che seleziona lo studente in base alla combinazione di login e pass
 */
static String qSelectLogin = " select * from studente where email=? and pass=? ";

/**
 * queri che conta quanti iscritti ad un corso
 */
static String qCountCorsiId = " select count(*) from iscrizione where id_corso = ? ";

/**
 * query che seleziona tutti glistudenti appartenenti ad un determinato corso
 */
static String qSelectStudentiPerCorso = " select s.nome,s.cognome,i.data_i "
										+ " from studente s join iscrizione i on s.email = i.studente_email "
										+ " where i.id_corso = ? order by s.cognome";

/**
 * query che seleziona il dettaglio di un corso
 */
static String qSelectDettaglioCorso = " select c.nome,c.descrizione,dc.nome as nome_docente,dc.cognome, count(i.id_corso) as numero_iscritti "
									+ " from corsi c join docenza dz on c.id = dz.id_corso "
									+ " join docente dc on dz.cod_docente = dc.codice "
									+ " join iscrizione i on i.id_corso = c.id "
									+ " where c.id=? "
									+ " group by i.id_corso,c.nome,c.descrizione,dc.nome,dc.cognome"; 

/**
 * query di selezione docenti di un determinato corso
 */
static String qSelectDocentiCorso = " select doc.codice, doc.nome, doc.cognome "
							    	+ " from docente doc join docenza dz on doc.codice = dz.cod_docente join corsi c on dz.id_corso = c.id "
							    	+ " where c.id =? ";

/**
 * query minimal che seleziona un corso in base all'id
 */
static String qSelectCorsoMin = " select nome,descrizione from corsi where id=? ";

/**
 * query che seleziona il periodo di un determinato corso
 */
static String qSelectPeriodoCorso = " select data_i,data_f from corsi where id = ? ";

/**
 * query che seleziona la programmazione di un determinato corso
 */
static String qSelectProgrammazioneCorso = " select f.g_sett, f.ora_i,f.ora_f from corsi c join programmazione p on c.id=p.id_corso join fasceorarie f on p.fo_id = f.id where c.id = ? ";

/**
 * query che seleziona il materiale a disposizione di un determinato corso
 */
static String qSelectMaterialeCorso = " select m.id,m.path,m.nome,m.tipo,m.formato "
									+ " from corsi c join supportodidattico s on c.id=s.id_corso join materiale m on s.id_materiale = m.id "
									+ " where c.id = ? order by m.id";

/**
 * query che seleziona i corsi frequentati da uno studente
 */
static String qSelectCorsiPerStudente = " select c.id, c.nome from corsi c join iscrizione i on c.id=i.id_corso join studente s on i.studente_email = s.email where s.email = ? ";

/** che seleziona il materiale a disposizione di uno studente
 * query
 */
static String qSelectMaterialePerStudente = " select distinct(m.nome ), m.path,m.id,m.formato,m.tipo "
										  + " from corsi c join iscrizione i on c.id=i.id_corso join studente s on i.studente_email = s.email join supportodidattico sup on sup.id_corso = c.id join materiale m on m.id=sup.id_materiale "
										  + " where s.email = ?";


// Query per il simulatore

static String qDelSupportoDidattico2 = " delete from supportodidattico2 ";
static String qInsertIntoSupportoDidattico2 = " insert into supportodidattico2 select * from supportodidattico ";
static String qDelSupportoDidattico = " delete from supportodidattico ";
static String qInsertIntoSupportoDidattico = " insert into supportodidattico select * from supportodidattico2 ";

static String qDelProgrammazione2 = " delete from programmazione2 ";
static String qInsertIntoProgrammazione2 = " insert into programmazione2 select * from programmazione ";
static String qDelProgrammazione = " delete from programmazione ";
static String qInsertIntoProgrammazione = " insert into programmazione select * from programmazione2 ";


// fine Query per il simulatore

// Query per statistiche
/**
 * query che seleziona tutti i corsi e il numero di iscritti agli stessi
 */
static String qStatIscrittiCorsi = " select c.nome, count(i.id_corso) as numero_iscritti from corsi c join iscrizione i on c.id = i.id_corso group by i.id_corso,c.nome ";
/**
 * query che seleziona tutti gli studenti
 */
static String qSelectStudenti = " select * from studente order by cognome ";
/**
 * query che conta tutti gli studenti iscritti alla palestra
 */
static String qcountStudenti = " select count(*) from studente";

// fine Query per statistiche

public static String getqSelectPeriodoCorso() {
	return qSelectPeriodoCorso;
}


public static String getqSelectStudentiPerCorso() {
	return qSelectStudentiPerCorso;
}


public static String getqCountCorsiId() {
	return qCountCorsiId;
}


public static String getqSelectLogin() {
	return qSelectLogin;
}


public static String getqSelectCorso() {
	return qSelectCorso;
}


public static String getqSelectTipiCorso() {
	return qSelectTipiCorso;
}


public static String getqSelectDettaglioCorso() {
	return qSelectDettaglioCorso;
}


public static String getqSelectDocentiCorso() {
	return qSelectDocentiCorso;
}
	
public static String getCountIscrittiCorso() {
	return countIscrittiCorso;
}


public static String getqSelectCorsoMin() {
	return qSelectCorsoMin;
}


public static String getqSelectProgrammazioneCorso() {
	return qSelectProgrammazioneCorso;
}


public static String getqSelectMaterialeCorso() {
	return qSelectMaterialeCorso;
}


public static String getqSelectCorsiPerStudente() {
	return qSelectCorsiPerStudente;
}


public static String getqSelectMaterialePerStudente() {
	return qSelectMaterialePerStudente;
}


public static String getqStatIscrittiCorsi() {
	return qStatIscrittiCorsi;
}


public static String getqSelectStudenti() {
	return qSelectStudenti;
}


public static String getQcountStudenti() {
	return qcountStudenti;
}

	
}
