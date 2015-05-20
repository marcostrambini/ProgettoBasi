package it.univr;

import java.util.ArrayList;

public class Simulatore {

	public static void main(String[] args) {
		
		System.out.println("-----------------------------------------------------------");
		ArrayList<Utente> listaUtenti = new ArrayList<Utente>();
		ArrayList<String> listaNomi = new ArrayList<String>();
		while(listaNomi.size()<100){
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
		
		listaUtenti.add(new Utente(nomeF,cognomeF,Tools.getDateRandom(),mailF,loginF,"password"));
		listaUtenti.add(new Utente(nomeM,cognomeM,Tools.getDateRandom(),mailM,loginM,"password"));
		
		}
		
		for(int i =0;i<listaUtenti.size();i++){
			System.out.println("Utente ["+(i+1)+"] :");
			System.out.println("Nome............: "+listaUtenti.get(i).getNome());
			System.out.println("Cognome.........: "+listaUtenti.get(i).getCognome());
			System.out.println("Data di nascita.: "+listaUtenti.get(i).getDn());
			System.out.println("Mail............: "+listaUtenti.get(i).getMail());
			System.out.println("Login...........: "+listaUtenti.get(i).getLogin());
			System.out.println("Password........: "+listaUtenti.get(i).getPassword());
			
		}
		
		System.out.println("dimensioni dell'array utente: "+listaUtenti.size());
		
	
		
		}
}
