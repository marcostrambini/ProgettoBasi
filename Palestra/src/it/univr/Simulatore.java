package it.univr;

import it.univr.database.DataSource;
import it.univr.database.Utente;

import java.beans.Statement;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

//import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

public class Simulatore {

	public static String appendString(String nomeStringa, String messaggio){
		return nomeStringa + "\n" + messaggio ;
	}

	public static void main(String[] args) throws FileNotFoundException, java.text.ParseException, ClassNotFoundException {
		String nomeFile = "ListaNomi.txt";
		String nomeFileCsv = "ListaNomi.csv";
		Tools.creaFile(nomeFile);
		Tools.clearFile(nomeFile);
		Tools.creaFile(nomeFileCsv);
		Tools.clearFile(nomeFileCsv);

		int nUtentiDaCreare = 300;
		int maxIscrizioniConsentite = 7;

		System.out.println("-----------------------------------------------------------");
		ArrayList<Utente> listaUtenti = new ArrayList<Utente>();
		ArrayList<String> listaNomi = new ArrayList<String>();
		while(listaNomi.size()<nUtentiDaCreare){
			String nomeF = 	Tools.getNomeFemminile();
			String cognomeF = Tools.getCognome();
			String mailF = Tools.getMail(nomeF, cognomeF);
			String loginF = Tools.getLogin(nomeF, cognomeF);
			String nomeM = Tools.getNomeMaschile();
			String cognomeM = Tools.getCognome();
			String mailM = Tools.getMail(nomeM, cognomeM);
			String loginM = Tools.getLogin(nomeM, cognomeM);
			String pwd = Tools.getPassword();


			
			
			while(listaNomi.contains(mailF)){
				nomeF = 	Tools.getNomeFemminile();
				cognomeF = Tools.getCognome();
				mailF = Tools.getMail(nomeF, cognomeF);
				loginF = Tools.getLogin(nomeF, cognomeF);
			}
			
			listaNomi.add(mailF);

			while(listaNomi.contains(mailM)){
				nomeM = Tools.getNomeMaschile();
				cognomeM = Tools.getCognome();
				mailM = Tools.getMail(nomeM, cognomeM);
				loginM = Tools.getLogin(nomeM, cognomeM);
			}
			
			listaNomi.add(mailM);
			
			

			listaUtenti.add(new Utente(nomeF,cognomeF,Tools.getDateRandom(),mailF,loginF,pwd));
			listaUtenti.add(new Utente(nomeM,cognomeM,Tools.getDateRandom(),mailM,loginM,pwd));

		}

		String msg ="";
		String msgCsv ="";
		ArrayList<String> listCsv = new ArrayList<String>();


		for(int i =0;i<listaUtenti.size();i++){

			int anno = Tools.getAge(listaUtenti.get(i).getDn());


			msg = appendString(msg, "");
			System.out.println("Utente ["+(i+1)+"] :");
			msg = appendString(msg, "Utente ["+(i+1)+"] :");
			System.out.println("Nome............: "+listaUtenti.get(i).getNome());
			msg = appendString(msg, "Nome............: "+listaUtenti.get(i).getNome());
			System.out.println("Cognome.........: "+listaUtenti.get(i).getCognome());
			msg = appendString(msg, "Cognome.........: "+listaUtenti.get(i).getCognome());
			System.out.println("Data di nascita.: "+listaUtenti.get(i).getDn());
			msg = appendString(msg, "Data di nascita.: "+listaUtenti.get(i).getDn());
			System.out.println("Eta'............: "+anno);
			msg = appendString(msg, "Eta'.............: "+anno);
			System.out.println("Mail............: "+listaUtenti.get(i).getMail());
			msg = appendString(msg, "Mail............: "+listaUtenti.get(i).getMail());
			System.out.println("Login...........: "+listaUtenti.get(i).getLogin());
			msg = appendString(msg, "Login...........: "+listaUtenti.get(i).getLogin());
			System.out.println("Password........: "+listaUtenti.get(i).getPassword());
			msg = appendString(msg, "Password........: "+listaUtenti.get(i).getPassword());

			msgCsv = listaUtenti.get(i).getNome()+","+listaUtenti.get(i).getCognome()+","+listaUtenti.get(i).getDn()+","+
					anno+","+listaUtenti.get(i).getMail()+","+listaUtenti.get(i).getLogin()+","+listaUtenti.get(i).getPassword();
			//			Tools.scriviFile(nomeFileCsv, msgCsv);


		}

		System.out.println("dimensioni dell'array utente: "+listaUtenti.size());
		System.out.println("dimensioni dell'array csv: "+listCsv.size());
		Tools.scriviFile(nomeFile, msg);

		/* Parte di inserimento nel database  */
		DataSource ds =  new DataSource();
		Connection con = ds.getConnection();
		java.sql.Statement stm;

		System.out.println("Connessione: "+con);

		/* Inserisco gli studenti */
		try {
			stm = con.createStatement();

			PreparedStatement pstm = con.prepareStatement(MyQuery.qInsertStudente);
//			PreparedStatement pstmDocenti = con.prepareStatement(MyQuery.qInsertDocente);
			System.out.println("Cancellazione tabelle:");
			
			System.out.println("Cancello supportodidattico2: "+!stm.execute(MyQuery.qDelSupportoDidattico2));
			System.out.println("Salvataggio supportodidattico2: "+!stm.execute(MyQuery.qInsertIntoSupportoDidattico2));
			System.out.println("Cancello supportodidattico: "+!stm.execute(MyQuery.qDelSupportoDidattico));
			
			System.out.println("Cancello programmazione2: "+!stm.execute(MyQuery.qDelProgrammazione2));
			System.out.println("Salvataggio programmazione2: "+!stm.execute(MyQuery.qInsertIntoProgrammazione2));
			System.out.println("Cancello programmazione: "+!stm.execute(MyQuery.qDelProgrammazione));
			
			System.out.println("Cancellazione tabella iscrizioni: " + !stm.execute(" truncate table iscrizione "));
			System.out.println("Cancellazione tabella studenti: " + !stm.execute("delete from studente"));

			for(int i = 0;i<listaUtenti.size();i++){
				
				pstm.setString(1, listaUtenti.get(i).getNome());
				pstm.setString(2, listaUtenti.get(i).getCognome());
				pstm.setDate(3,  new java.sql.Date(listaUtenti.get(i).getDn().getTime())); 
				pstm.setString(4, listaUtenti.get(i).getLogin());
				pstm.setString(5, listaUtenti.get(i).getPassword());
				pstm.setString(6, listaUtenti.get(i).getMail());
				System.out.println("Inserimento studente # "+(i)+": "+listaUtenti.get(i).getNome()+" "+listaUtenti.get(i).getCognome()+" -> esito = "+!pstm.execute());
				pstm.clearParameters();
			}
		
			
			/* fine di inserimento gli studenti */
			
			/* inserisco i docenti 
//			stm.execute("delete from docente where codice > 1");
			for(int i = 0;i<listaUtenti.size();i++){
				
//				stm.execute(" insert into docente (codice,nome,cognome) "+
//						" values ((select max(codice)+1 from docente),'"+listaUtenti.get(i).getNome()+",'"+listaUtenti.get(i).getCognome()+"'");
//				
//				pstmDocenti.clearParameters();
//				pstmDocenti.setString(1, listaUtenti.get(i).getNome());
//				pstmDocenti.setString(2, listaUtenti.get(i).getCognome());
				
//				pstmDocenti.setString(1, "pippo");
//				pstmDocenti.setString(2, listaUtenti.get(i).getCognome());
				String q = " insert into docente (codice,nome,cognome) "+
						" values ((select max(codice)+1 from docente),'"+listaUtenti.get(i).getNome()+"','"+listaUtenti.get(i).getCognome()+"')";
				
				System.out.println("Inserimento record Docente # "+(i)+" : "+stm.execute(q));
				

			}*/
//			pstmDocenti.close();
			pstm.close();
			
		
		/* iscrivo gli utenti ai corsi */
		
		int n_corsi = 0;
		ResultSet rs_corsi = stm.executeQuery(MyQuery.countCorsi);
		if(rs_corsi.next())
		n_corsi = rs_corsi.getInt(1);
		System.err.println("N record nella tabella corsi: "+n_corsi);
		
		
		
		
		
		
		PreparedStatement pstm2 = con.prepareStatement(MyQuery.qIscrizione);
		
		ArrayList<Integer> listaIdCorso = new ArrayList<Integer>();
		
		for(int i =0; i<listaUtenti.size();i++){
			
			double randNVolte = Math.random();
			
			int id_nVolte = (int) (randNVolte * maxIscrizioniConsentite)+1;
			listaIdCorso.clear();
			
			listaIdCorso.add(0);
			
			double randIDCorso = Math.random();
			int id_corso = (int) (randIDCorso * n_corsi)+1; //da 1 a 4
//			System.out.println("id corso random prima del ciclo: "+id_corso);
			
			for(int n=0;n<id_nVolte;n++){
				
				boolean verificatore = true;
				
				while(verificatore){
				if(listaIdCorso.contains(id_corso)){
//					System.out.println("l'id : "+id_corso+" e' contenuto nell'array, ne genero un altro");
					randIDCorso = Math.random();
					id_corso = (int) (randIDCorso * n_corsi)+1;
//					System.out.println("Ho generato l'id: "+id_corso);
					
				}
				else {
					verificatore = false;
					listaIdCorso.add(id_corso);
//					System.out.println("Aggiungo l'id: "+id_corso+" all'array");
				}
				}
				

				
				
				pstm2.clearParameters();
				pstm2.setInt(1,id_corso);
				pstm2.setString(2, listaUtenti.get(i).getMail());
//				pstm2.setDate(3, new java.sql.Date(listaUtenti.get(i).getDn().getTime()));
				pstm2.setDate(3, new java.sql.Date(Tools.getDataIscrizioneRandom().getTime()));
				pstm2.execute();
				System.out.println("Iscrizione avvenuta per: "+listaUtenti.get(i).getNome()+" "+listaUtenti.get(i).getCognome());
				
				
			}
			
			
			
		}
		
		pstm2.close();
	
		
	
		System.out.println("Ripristino supportodidattico: "+!stm.execute(MyQuery.qInsertIntoSupportoDidattico));
			
		System.out.println("Ripristino programmazione: "+!stm.execute(MyQuery.qInsertIntoProgrammazione));	
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			
			
			try {
				
				
				con.close();
				
				System.out.println("connessione chiusa");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			/* fine alimentazione tabella */
		}
	}
}
