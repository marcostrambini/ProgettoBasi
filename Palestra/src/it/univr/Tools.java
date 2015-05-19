package it.univr;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Tools {

	static String[] nomi_femminili = {"Agnese", "Alba","Alice","Anita","Alessandra","Anna","Arianna","Ambra",
		"Azzurra","Angelica","Annalisa","Aria","Astrid","Ambrosia","Adelaide","Alexandra","Aurora","Barbara","Beatrice",
		"Benedetta","Camilla","Candida","Carola","Cassandra","Cassiopea","Caterina","Celeste","Chantal","Clara",
		"Clarissa","Claudia","Clementina","Cloe","Dalila","Daniela","Deborah","Delfina","Desir�e","Denise","Dharma",
		"Diana","Diletta","Domitilla","Dorotea","Donatella","Edy","Edith","Elena","Eleonora","Elettra","Elisabetta",
		"Elisabeth","Eloisa","Elaine","Emily","Erica","Ester","Evelyne","Fanny","Flora","Gabriella","Gaia","Giada",
		"Ginevra","Gioia","Giulia","Gloria","Helena","Ines","Irene","Iris","Isabella","Jasmine","Jessica","Jillian",
		"Layla","Leila","Letizia","Liliana","Lizabeth","Lorena","Lority","Luna","Maia","Manuela","Marianna","Maril�",
		"Martina","Melania","Melissa","Micaela","Micol","Miranda","Miriam","Monica","Morena","Morgana","Nabila",
		"Nadia","Naomi","Nicole","Nicoletta","Ninfa","Nives","Olimpia","Ornella","Ottavia","Paola","Penelope","Priscilla",
		"Rebecca","Rossella","Sabina","Sabrina","Sara","Serena","Shana","Sharon","Shirley","Sibilla","Simona","Soledad",
		"Stella","Susanna","Teodora","Teresa","Valeria","Valerie","Vanessa","Venere","Vera","Veronica","Viola","Vittoria",
		"Viviana","Wendy","Yasmine","Zoe"	};


	static String[] nomi_maschini = {"Aaron","Adam","Abraham","Agostino","Alessandro","Alessio","Amedeo",
		"Antonio","Bartolomeo","Battista","Benedetto","Bernardo","Cesare","Claudio","Corrado","Cristopher",
		"Daniele","Davide","Domenico","Edoardo","Efrem","Emanuele","Emiliano","Enrico","Ermes","Eros",
		"Ettore","Fabrizio","Federico","Fernando","Filippo","Francesco","Gabriele","Giacomo","Gianluca",
		"Gioele","Giordano","Giosu�","Giovanni","Gregorio","Hector","Igor","Isacco","Ismaele","Ivan",
		"Jacopo","Jonathan","Joseph","Joshua","Leandro","Leonardo","Lorenzo","Loris","Luca","Manuele",
		"Marco","Mariano","Martino","Massimiliano","Massimo","Mathias","Matteo","Mattia","Maurizio",
		"Michele","Michelangelo","Moreno","Moris","Mos�","Narciso","Nathan","Nicholas","Nicola","Nicol",
		"Noah","Oscar","Paolo","Peter","Raffaele","Renato","Riccardo","Roberto","Romeo","Rossano",
		"Roy","Salvatore","Samuele","Samuel","Sasha","Saverio","Sebastiano","Sebastian","Serafino","Silvano",
		"Silvestro","Simone","Sirio","Stefano","Teodoro","Thierry","Thomas","Tommaso","Umberto",
		"Ulisse","Valerio","Vincenzo","Vittorio","William",	};
	
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
		"Santini","Santoro","Sartori","Serra","Silvestri","Sorrentino","Testa","Valente","Valentini",
		"Villa","Villani","Vitale","Vitali","Volpe","Zanetti"};


	static String getNomeFemminile(){
		double randNumber1 = Math.random();
		int id = (int) (randNumber1 * nomi_femminili.length);
		return nomi_femminili[id];
	};
	
	static String getNomeMaschile(){
		double randNumber1 = Math.random();
		int id = (int) (randNumber1 * nomi_maschini.length);
		return nomi_maschini[id];
	};
	
	static String getCognome(){
		double randNumber1 = Math.random();
		int id = (int) (randNumber1 * cognomi.length);
		return cognomi[id];
	};
	
	static String getMail(String nome, String cognome){
		String [] domini = {"mail.it","mail.com","gmail.com","libero.it","tiscali.it"};
		double randNumber1 = Math.random();
		int id = (int) (randNumber1 * domini.length);
		
		return nome.substring(0,1).toLowerCase()+"."+cognome.toLowerCase()+"@"+domini[id];
	}
	
	
	static String dateRandom() {
        GregorianCalendar gc = new GregorianCalendar();
        int start = 1980;
        int end = 2000;
        int year = start + (int)Math.round(Math.random() * (end - start));
        gc.set(gc.YEAR, year);
        int dayOfYear = randBetween(1, gc.getActualMaximum(gc.DAY_OF_YEAR));
        gc.set(gc.DAY_OF_YEAR, dayOfYear);
        return (gc.get(gc.DAY_OF_MONTH) + "/" + (gc.get(gc.MONTH)+1) + "/" +  gc.get(gc.YEAR));
       
    }

	public static int randBetween(int start, int end) {
        return start + (int)Math.round(Math.random() * (end - start));
    }

	public static void main(String[] args) {
//		for(int i =0;i<50;i++){
//		
//		double randNumber1 = Math.random();
//		double randNumber2 = Math.random();
//		double randNumber3 = Math.random();
//		double randNumber4 = Math.random();
//		
//		int id_fn = (int) (randNumber1 * nomi_femminili.length);
//		int id_fc = (int) (randNumber2 * cognomi.length);
//		int id_mn = (int) (randNumber3 * nomi_maschini.length);
//		int id_mc = (int) (randNumber4 * cognomi.length);
//
//		
//		System.out.println(nomi_femminili[id_fn]+" "+cognomi[id_fc]);
//		System.out.println(nomi_maschini[id_mn]+" "+cognomi[id_mc]);
//		}
		System.out.println("-----------------------------------------------------------");
		
		ArrayList<String> listaNomi = new ArrayList<String>();
		while(listaNomi.size()<100){
		String nomeF = 	getNomeFemminile();
		String cognomeF = getCognome();
		String mailF = getMail(nomeF, cognomeF);
		String nomeM = getNomeMaschile();
		String cognomeM = getCognome();
		String mailM = getMail(nomeM, cognomeM);
		String nomeFCompleto = nomeF+" "+cognomeF+" "+mailF;
		String nomeMCompleto = nomeM+" "+cognomeM+" "+mailM;
		
		if(listaNomi.contains(nomeFCompleto)){
			nomeF = 	getNomeFemminile();
			cognomeF = getCognome();
			mailF = getMail(nomeF, cognomeF);
		}else listaNomi.add(nomeFCompleto);
		
		if(listaNomi.contains(nomeMCompleto)){
			nomeM = getNomeMaschile();
			cognomeM = getCognome();
			mailM = getMail(nomeM, cognomeM);
		}else listaNomi.add(nomeMCompleto);
		
		}
		
		for(int i =0;i<listaNomi.size();i++)
			System.out.println(listaNomi.get(i));
		
		System.out.println("dimensioni dell'array nomi: "+listaNomi.size());
		
		for(int i=0;i<50;i++)
		System.out.println(dateRandom());
		
		}
	}


