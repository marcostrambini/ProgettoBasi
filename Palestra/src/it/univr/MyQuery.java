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

static String qSelectCorso = " select c.id,c.nome as nome_corso ,c.tipo,c.descrizione,dc.nome as nome_docente ,dc.cognome as cognome_docente,c.data_i,c.data_f "
							+ " from corsi c join docenza dz on c.id=dz.id_corso "
							+ " join docente dc on dz.cod_docente = dc.codice "
							+ " where dz.responsabile = true and c.tipo = ? ";

static String qSelectLogin = " select * from studente where email=? and pass=? ";

static String qCountCorsiId = " select count(*) from iscrizione where id_corso = ? ";

static String qSelectStudentiPerCorso = " select s.nome,s.cognome,i.data_i "
										+ " from studente s join iscrizione i on s.email = i.studente_email "
										+ " where i.id_corso = ?";

static String qSelectDettaglioCorso = " select c.nome,c.descrizione,dc.nome,dc.cognome, count(i.id_corso) as numero_iscritti "
									+ " from corsi c join docenza dz on c.id = dz.id_corso "
									+ " join docente dc on dz.cod_docente = dc.codice "
									+ " join iscrizione i on i.id_corso = c.id "
									+ " where c.id=? "
									+ " group by i.id_corso,c.nome,c.descrizione,dc.nome,dc.cognome"; 

static String qSelectMaterialiCorso = " select m.nome,m.tipo, m.formato,m.path "
									+ " from supportodidattico s join materiale m on s.id_materiale = m.id join corsi c on c.id = s.id_corso "
									+ " where c.id = ?";

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


public static String getqSelectMaterialiCorso() {
	return qSelectMaterialiCorso;
}
	
	
}
