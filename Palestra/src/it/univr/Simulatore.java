package it.univr;

import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

public class Simulatore {

	public static String appendString(String nomeStringa, String messaggio){
		return nomeStringa + "\n" + messaggio ;
	}
	
	public static void main(String[] args) throws FileNotFoundException, java.text.ParseException {
		String nomeFile = "ListaNomi.txt";
		String nomeFileCsv = "ListaNomi.csv";
		Tools.creaFile(nomeFile);
		Tools.clearFile(nomeFile);
		Tools.creaFile(nomeFileCsv);
		Tools.clearFile(nomeFileCsv);

		
		System.out.println("-----------------------------------------------------------");
		ArrayList<Utente> listaUtenti = new ArrayList<Utente>();
		ArrayList<String> listaNomi = new ArrayList<String>();
		while(listaNomi.size()<200){
		String nomeF = 	Tools.getNomeFemminile();
		String cognomeF = Tools.getCognome();
		String mailF = Tools.getMail(nomeF, cognomeF);
		String loginF = Tools.getLogin(nomeF, cognomeF);
		String nomeM = Tools.getNomeMaschile();
		String cognomeM = Tools.getCognome();
		String mailM = Tools.getMail(nomeM, cognomeM);
		String loginM = Tools.getLogin(nomeM, cognomeM);
		String pwd = Tools.getPassword();
		String nomeFCompleto = nomeF+" "+cognomeF+" "+mailF+" "+loginF;
		String nomeMCompleto = nomeM+" "+cognomeM+" "+mailM+" "+loginM;
		
		if(listaNomi.contains(nomeFCompleto)){
			nomeF = 	Tools.getNomeFemminile();
			cognomeF = Tools.getCognome();
			mailF = Tools.getMail(nomeF, cognomeF);
			loginF = Tools.getLogin(nomeF, cognomeF);
		}else listaNomi.add(nomeFCompleto);
		
		if(listaNomi.contains(nomeMCompleto)){
			nomeM = Tools.getNomeMaschile();
			cognomeM = Tools.getCognome();
			mailM = Tools.getMail(nomeM, cognomeM);
			loginM = Tools.getLogin(nomeM, cognomeM);
		}else listaNomi.add(nomeMCompleto);
		
		listaUtenti.add(new Utente(nomeF,cognomeF,Tools.getDateRandom(),mailF,loginF,pwd));
		listaUtenti.add(new Utente(nomeM,cognomeM,Tools.getDateRandom(),mailM,loginM,pwd));
		
		}
		
		String msg ="";
		String msgCsv ="";
		ArrayList<String> listCsv = new ArrayList<String>();
		
		
		for(int i =0;i<listaUtenti.size();i++){
			
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			String dateInString = listaUtenti.get(i).getDn();
			java.util.Date date = null;
			try {
		 
				 date =  formatter.parse(dateInString);
				System.out.println(date);
				System.out.println(formatter.format(date));
		 
			} catch (ParseException e) {
				e.printStackTrace();
			}
			int anno = Tools.getAge(date);
			
			
			msg = appendString(msg, "");
			System.out.println("Utente ["+(i+1)+"] :");
			msg = appendString(msg, "Utente ["+(i+1)+"] :");
			System.out.println("Nome............: "+listaUtenti.get(i).getNome());
			msg = appendString(msg, "Nome............: "+listaUtenti.get(i).getNome());
			System.out.println("Cognome.........: "+listaUtenti.get(i).getCognome());
			msg = appendString(msg, "Cognome.........: "+listaUtenti.get(i).getCognome());
			System.out.println("Data di nascita.: "+listaUtenti.get(i).getDn());
			msg = appendString(msg, "Data di nascita.: "+listaUtenti.get(i).getDn());
			System.out.println("Età.............: "+anno);
			msg = appendString(msg, "Età.............: "+anno);
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

		
		}
}
