package it.univr;

public class MyQuery {

	
static String qInsertStudente = " insert into studente (nome,cognome,dn,login,pass,email) "+
						 " values (?,?,?,?,?,?)";

static String qInsertDocente = " insert into docente (codice,nome,cognome) "+
						" values ((select max(codice)+1 from docente),?,?)";

static String qIscrizione = " insert into iscrizione (id_corso, studente_email, data_i) "+
						" values (?,?,?)";

static String countCorsi = " select count(*) from corsi";

static String countIscrittiCorso = " select count(*) from iscrizione where id_corso = ? ";



static String deleteTable = " delete from ?";

/**
 * seleziona tutti i tipi corso
 */
static String qSelectTipiCorso = " select * from tipocorso ";

static String qSelectCorso = " select c.id,c.nome as nome_corso ,c.tipo,c.descrizione,dc.nome as nome_docente ,dc.cognome as cognome_docente,c.data_i,c.data_f "
							+ " from corsi c join docenza dz on c.id=dz.id_corso "
							+ " join docente dc on dz.cod_docente = dc.codice "
							+ " where dz.responsabile = true and c.tipo = ? ";

static String qSelectLogin = " select * from studente where email=? and pass=? ";

static String qCountCorsiId = " select count(*) from iscrizione where id_corso = ? ";

static String qSelectStudentiPerCorso = " select s.nome,s.cognome,i.data_i "
										+ " from studente s join iscrizione i on s.email = i.studente_email "
										+ " where i.id_corso = ? order by s.cognome";

static String qSelectDettaglioCorso = " select c.nome,c.descrizione,dc.nome as nome_docente,dc.cognome, count(i.id_corso) as numero_iscritti "
									+ " from corsi c join docenza dz on c.id = dz.id_corso "
									+ " join docente dc on dz.cod_docente = dc.codice "
									+ " join iscrizione i on i.id_corso = c.id "
									+ " where c.id=? "
									+ " group by i.id_corso,c.nome,c.descrizione,dc.nome,dc.cognome"; 

static String qSelectDocentiCorso = " select doc.codice, doc.nome, doc.cognome "
							    	+ " from docente doc join docenza dz on doc.codice = dz.cod_docente join corsi c on dz.id_corso = c.id "
							    	+ " where c.id =? ";

static String qSelectCorsoMin = " select nome,descrizione from corsi where id=? ";

static String qSelectPeriodoCorso = " select data_i,data_f from corsi where id = ? ";

static String qSelectProgrammazioneCorso = " select f.g_sett, f.ora_i,f.ora_f from corsi c join programmazione p on c.id=p.id_corso join fasceorarie f on p.fo_id = f.id where c.id = ? ";

static String qSelectMaterialeCorso = " select m.id,m.path,m.nome,m.tipo,m.formato "
									+ " from corsi c join supportodidattico s on c.id=s.id_corso join materiale m on s.id_materiale = m.id "
									+ " where c.id = ? order by m.id";

static String qSelectCorsiPerStudente = " select c.id, c.nome from corsi c join iscrizione i on c.id=i.id_corso join studente s on i.studente_email = s.email where s.email = ? ";

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

static String qStatIscrittiCorsi = " select c.nome, count(i.id_corso) as numero_iscritti from corsi c join iscrizione i on c.id = i.id_corso group by i.id_corso,c.nome ";

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

	
}
