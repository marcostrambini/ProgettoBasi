package it.univr.database;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * classe di utilità
 * @author Mago
 *
 */
public class Tools {

	/**
	 * Array contenente nomi propri femminili
	 */
	static String[] nomi_femminili = {"Agnese", "Alba","Alice","Anita","Alessandra","Anna","Arianna","Ambra",
		"Azzurra","Angelica","Annalisa","Aria","Astrid","Ambrosia","Adelaide","Alexandra","Aurora","Barbara","Beatrice",
		"Benedetta","Camilla","Candida","Carola","Cassandra","Cassiopea","Caterina","Celeste","Chantal","Clara",
		"Clarissa","Claudia","Clementina","Cloe","Dalila","Daniela","Deborah","Delfina","Desiree","Denise","Dharma",
		"Diana","Diletta","Domitilla","Dorotea","Donatella","Edy","Edith","Elena","Eleonora","Elettra","Elisabetta",
		"Elisabeth","Eloisa","Elaine","Emily","Erica","Ester","Evelyne","Fanny","Flora","Gabriella","Gaia","Giada",
		"Ginevra","Gioia","Giulia","Gloria","Helena","Ines","Irene","Iris","Isabella","Jasmine","Jessica","Jillian",
		"Layla","Leila","Letizia","Liliana","Lizabeth","Lorena","Lority","Luna","Maia","Manuela","Marianna","Marilu",
		"Martina","Melania","Melissa","Micaela","Micol","Miranda","Miriam","Monica","Morena","Morgana","Nabila",
		"Nadia","Naomi","Nicole","Nicoletta","Ninfa","Nives","Olimpia","Ornella","Ottavia","Paola","Penelope","Priscilla",
		"Rebecca","Rossella","Sabina","Sabrina","Sara","Serena","Shana","Sharon","Shirley","Sibilla","Simona","Soledad",
		"Stella","Susanna","Teodora","Teresa","Valeria","Valerie","Vanessa","Venere","Vera","Veronica","Viola","Vittoria",
		"Viviana","Wendy","Yasmine","Zoe"	};

	
	/**
	 * Array contenente nomi propri maschili
	 */
	static String[] nomi_maschini = {"Aaron","Adam","Abraham","Agostino","Alessandro","Alessio","Amedeo",
		"Antonio","Bartolomeo","Battista","Benedetto","Bernardo","Cesare","Claudio","Corrado","Cristopher",
		"Daniele","Davide","Domenico","Edoardo","Efrem","Emanuele","Emiliano","Enrico","Ermes","Eros",
		"Ettore","Fabrizio","Federico","Fernando","Filippo","Francesco","Gabriele","Giacomo","Gianluca",
		"Gioele","Giordano","Giosue'","Giovanni","Gregorio","Hector","Igor","Isacco","Ismaele","Ivan","JacK",
		"Jacopo","Jonathan","Joseph","Joshua","Leandro","Leonardo","Lorenzo","Loris","Luca","Manuele",
		"Marco","Mariano","Martino","Massimiliano","Massimo","Mathias","Matteo","Mattia","Maurizio",
		"Michele","Michelangelo","Moreno","Moris","Mose'","Narciso","Nathan","Nicholas","Nicola","Nicol",
		"Noah","Oscar","Paolo","Peter","Raffaele","Renato","Riccardo","Roberto","Romeo","Rossano",
		"Roy","Salvatore","Samuele","Samuel","Sasha","Saverio","Sebastiano","Sebastian","Serafino","Silvano",
		"Silvestro","Simone","Sirio","Stefano","Teodoro","Thierry","Thomas","Tommaso","Umberto",
		"Ulisse","Uribe","Valerio","Vincenzo","Vittorio","William",	};
	
	/**
	 * Array contenente cognomi comuni
	 */
	static String[] cognomi = {"Agostini","Aiello","Albanese","Amato","Antonelli","Arena","Baldi","Barbieri",
		"Barone","Basile","Battaglia","Bellini","Benedetti","Bernardi","Bianchi","Bianco","Brambilla",
		"Bruni","Bruno","Calabrese","Caputo","Carbone","Caruso","Castelli","Catalano","Cattaneo","Cavallo",
		"Ceccarelli","Cirillo","Colombo","Conte","Conti","Coppola","Costa","Costantini","De Angelis",
		"De Luca","De Rosa","De Santis","De Simone","Di Stefano","Donati","Esposito","Fabbri","Farina",
		"Ferrante","Ferrara","Ferrari","Ferraro","Ferrero","Ferretti","Ferri","Ferro","Fiore","Fontana",
		"Franco","Fumagalli","Fusco","Galli","Gallo","Gargiulo","Garofalo","Gatti","Gentile","Giordano",
		"Giorgi","Giuliani","Grassi","Grasso","Greco","Grimaldi","Guerra","Guidi","Leone","Lombardi","Lombardo",
		"Longo","Lorusso","Mancini","Marchetti","Marchi","Mariani","Marini","Marino","Marra","Martinelli",
		"Martini","Martino","Mazza","Mele","Meloni","Messina","Milani","Monaco","Montanari","Monti",
		"Morelli","Moretti","Moro","Napolitano","Neri","Olivieri","Orlando","Pace","Pagano","Palmieri",
		"Palumbo","Parisi","Pastore","Pellegrini","Pellegrino","Pepe","Perrone","Piazza","Piccolo",
		"Pinna","Piras","Poli","Pozzi","Proietti","Ricci","Ricciardi","Rinaldi","Riva","Rizzi",
		"Rizzo","Romano","Romeo","Rossetti","Rossi","Ruggeri","Ruggiero","Russo","Sala","Sanna",
		"Santini","Santoro","Sartori","Serra","Silvestri","Sorrentino","Strambini","Testa","Valente","Valentini",
		"Villa","Villani","Vitale","Vitali","Volpe","Zanetti"};


	/**
	 * metodo che ritorna un nome proprio femminile random
	 * @return
	 */
	static String getNomeFemminile(){
		double randNumber1 = Math.random();
		int id = (int) (randNumber1 * nomi_femminili.length);
		return nomi_femminili[id];
	};
	
	/**
	 * metodo che ritorna un nome proprio maschile random
	 * @return
	 */
	static String getNomeMaschile(){
		double randNumber1 = Math.random();
		int id = (int) (randNumber1 * nomi_maschini.length);
		return nomi_maschini[id];
	};
	
	/**
	 * metodo che ritorna un nome cognome comune random
	 * @return
	 */
	static String getCognome(){
		double randNumber1 = Math.random();
		int id = (int) (randNumber1 * cognomi.length);
		return cognomi[id];
	};
	
	/**
	 * metodo che ritorna un indirizzo mail con dominio a caso nella forma n.cognome
	 * prende come parametri nome e cognome generati a random
	 * @param nome
	 * @param cognome
	 * @return
	 */
	static String getMail(String nome, String cognome){
		String [] domini = {"mail.it","mail.com","gmail.com","libero.it","tiscali.it","hotmail.it","alice.it","outlook.com","jegjeghede.org"};
		double randNumber1 = Math.random();
		int id = (int) (randNumber1 * domini.length);
		
		return nome.substring(0,1).toLowerCase()+"."+cognome.toLowerCase()+"@"+domini[id];
	}
	
	/**
	 * metodo che ritorna una login a random prendendo come parametri nome e cognome random
	 * @param nome
	 * @param cognome
	 * @return
	 */
	static String getLogin(String nome, String cognome){
		
		return nome.substring(0,1).toLowerCase()+"."+cognome.toLowerCase();
	}
	
	/**
	 * metodo che ritorna la stringa "password" per l'attributo password di ogni utente
	 * @return
	 */
	static String getPassword(){
		return "password";
	}
	
	/**
	 * metodo che ritorna una data di nascita a random tra il 1980 e il 2000
	 * @return
	 * @throws ParseException
	 */
	static java.util.Date getDateRandom() throws ParseException {
        GregorianCalendar gc = new GregorianCalendar();
        int start = 1980;
        int end = 2000;
        int year = start + (int)Math.round(Math.random() * (end - start));
        gc.set(gc.YEAR, year);
        int dayOfYear = randBetween(1, gc.getActualMaximum(gc.DAY_OF_YEAR));
        gc.set(gc.DAY_OF_YEAR, dayOfYear);
        String dataString = (gc.get(gc.DAY_OF_MONTH) + "/" + (gc.get(gc.MONTH)+1) + "/" +  gc.get(gc.YEAR));
        
        java.util.Date data = null;
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		data =  formatter.parse(dataString);
		return (java.util.Date) data;
    }
	
	/**
	 * metodo che ritorna una data di iscrizione a random tra il 2014 e il 2015
	 * @return
	 * @throws ParseException
	 */
	static java.util.Date getDataIscrizioneRandom() throws ParseException {
        GregorianCalendar gc = new GregorianCalendar();
        int start = 2014;
        int end = 2015;
        int year = start + (int)Math.round(Math.random() * (end - start));
        gc.set(gc.YEAR, year);
        int dayOfYear = randBetween(1, gc.getActualMaximum(gc.DAY_OF_YEAR));
        gc.set(gc.DAY_OF_YEAR, dayOfYear);
        String dataString = (gc.get(gc.DAY_OF_MONTH) + "/" + (gc.get(gc.MONTH)+1) + "/" +  gc.get(gc.YEAR));
        
        java.util.Date data = null;
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		data =  formatter.parse(dataString);
		return (java.util.Date) data;
    }

	/**
	 * metodo di supporto per la generazione di date random tra due estremi
	 * @param start
	 * @param end
	 * @return
	 */
	public static int randBetween(int start, int end) {
        return start + (int)Math.round(Math.random() * (end - start));
    }
	
	/**
	 * metodo che srive una stringa in un determinato file passando il nome come parametro
	 * @param nomeFile
	 * @param parola
	 * @throws FileNotFoundException
	 */
	static void scriviFile(String nomeFile, String parola)
			throws FileNotFoundException {
		FileOutputStream fos = new FileOutputStream(nomeFile, true);
		PrintWriter scrivi = new PrintWriter(fos);
		scrivi.println(parola);
		scrivi.close();
	}
	
	/**
	 * metodo che pulisce il contenuto di un file passando il nome come parametro
	 * @param nomeFile
	 * @throws FileNotFoundException
	 */
	static void clearFile(String nomeFile) throws FileNotFoundException {
		PrintWriter pw = new PrintWriter(nomeFile);
		pw.close();

	}
	
	/**
	 * metodo che crea un file passando il nome come parametro
	 * @param nomeFile
	 * @throws FileNotFoundException
	 */
	static void creaFile(String nomeFile) throws FileNotFoundException {
		File file = new File(nomeFile);

		if (file.exists())
			System.out.println(nomeFile + " esiste gia'");
		else {

			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block

				e.printStackTrace();
			}
		}
	}
	
	/**
	 * metodo che calcola l'eta attuale in base alla data di nascita
	 * @param date
	 * @return
	 */
	public static int getAge(java.util.Date date) {

	    Calendar today = Calendar.getInstance();
	    Calendar birthDate = Calendar.getInstance();

	    int age = 0;

	    birthDate.setTime(date);
	    if (birthDate.after(today)) {
	        throw new IllegalArgumentException("Can't be born in the future");
	    }

	    age = today.get(Calendar.YEAR) - birthDate.get(Calendar.YEAR);

	    // If birth date is greater than todays date (after 2 days adjustment of leap year) then decrement age one year   
	    if ( (birthDate.get(Calendar.DAY_OF_YEAR) - today.get(Calendar.DAY_OF_YEAR) > 3) ||
	            (birthDate.get(Calendar.MONTH) > today.get(Calendar.MONTH ))){
	        age--;

	     // If birth date and todays date are of same month and birth day of month is greater than todays day of month then decrement age
	    }else if ((birthDate.get(Calendar.MONTH) == today.get(Calendar.MONTH )) &&
	              (birthDate.get(Calendar.DAY_OF_MONTH) > today.get(Calendar.DAY_OF_MONTH ))){
	        age--;
	    }

	    return age;
	}
	
	/**
	 * metodo che scrive su file un array di stringe passato come parametro
	 * @param nomeFile
	 * @param parole
	 * @throws FileNotFoundException
	 */
	public void scriviFile(String nomeFile, ArrayList<String> parole)
			throws FileNotFoundException {

		FileOutputStream fos = new FileOutputStream(nomeFile, true);
		PrintWriter scrivi = new PrintWriter(fos);
		for (int i = 0; i < parole.size(); i++)
			scrivi.println(parole.get(i));

		scrivi.close();
	}

	
	}


