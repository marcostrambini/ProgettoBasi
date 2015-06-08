package it.univr.database;

import it.univr.MyQuery;
import it.univr.UserBean;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Questa classe mette a disposizione i metodi per effettuare interrogazioni
 * sulla base di dati.
 */
public class DataSource implements Serializable {

  // === Properties ============================================================

  // dati di identificazione dell'utente (da personalizzare)
  private String user = "userlab90";
  private String passwd = "novantaZX";

  // URL per la connessione alla base di dati e' formato dai seguenti
  // componenti: <protocollo>://<host del server>/<nome base di dati>.
  private String url = "jdbc:postgresql://dbserver.scienze.univr.it/dblab90";

  // Driver da utilizzare per la connessione e l'esecuzione delle query.
  private String driver = "org.postgresql.Driver";

  // -- definizione delle query ------------------------------------------------

  // recupera le principali info su tutti i corsi di studi
//  private String css =
//    "SELECT id, Codice, Nome FROM corsostudi ORDER BY Nome";

  // recupera tutte le informazioni di un particolare corso di studi
//  private String cs =
//    "SELECT id,nome,codice,abbreviazione,durataanni,sede,informativa "
//    + "FROM corsostudi "
//    + "WHERE id=?";

  // recupera la/e facolta' di un particolare corso di studi
//  private String csf =
//    "SELECT DISTINCT f.nome "
//    + "FROM facolta f INNER JOIN corsoinfacolta csf "
//    + "ON (f.id=csf.id_facolta) "
//    + "WHERE csf.id_corsostudi=?";

  //recupera il nome, codice, nome preside delle facolta
//  private String q2 = 
//		  "select f.nome as nomef, f.codice, f.indirizzo,p.nome as nomep,p.cognome as cognomep "
//		+ "from facolta f join persona p "
//        + "on p.id = f.id_preside_persona";
//
//  private String dettaglioPreside = 
//		  "select p.nome as nomep,p.cognome as cognomep "
//		  +"from facolta f join persona p "
//		  +"on p.id = f.id_preside_persona "
//		  +"where f.nome = ?";
  
  
  // === Methods ===============================================================

  /**
   * Costruttore della classe. Carica i driver da utilizzare per la connessione
   * alla base di dati.
   *
   * @throws ClassNotFoundException Eccezione generata nel caso in cui i driver
   * per la connessione non siano trovati nel CLASSPATH.
   */
  public DataSource() throws ClassNotFoundException {
    Class.forName( driver );
  }


  private Corso makeCorsoBean( ResultSet rs ) throws SQLException {
    Corso bean = new Corso();
    bean.setId( rs.getInt( "id"));
    bean.setNome_corso( rs.getString( "nome_corso" ));
    bean.setData_i( rs.getDate("data_i"));
    bean.setData_f( rs.getDate("data_f"));
    bean.setNome_docente(rs.getString("nome_docente"));
    bean.setCognome_docente(rs.getString("cognome_docente"));
    bean.setTipo(rs.getString("tipo"));
    bean.setDescrizione(rs.getString("descrizione"));
    return bean;
  }
  
  private Corso makeCorsiStudenteBean(ResultSet rs) throws SQLException{
	  Corso bean = new Corso();
	  bean.setNome_corso(rs.getString("nome"));
	  return bean;
  }
  

  private Corso makeCorsoDettaglioBean( ResultSet rs ) throws SQLException {
	    Corso bean = new Corso();
	    bean.setId( rs.getInt( "id"));
	    bean.setNome_corso( rs.getString( "nome_corso" ));
	    bean.setData_i( rs.getDate("data_i"));
	    bean.setData_f( rs.getDate("data_f"));
	    bean.setNome_docente(rs.getString("nome_docente"));
	    bean.setCognome_docente(rs.getString("cognome_docente"));
	    bean.setTipo(rs.getString("tipo"));
	    bean.setDescrizione(rs.getString("descrizione"));
	    bean.setNumero_iscritti(rs.getInt("numero_iscritti"));
	    return bean;
	  }
  
  
  

  	private TipoCorso makeTipoCorsoBean( ResultSet rs ) throws SQLException {
  		TipoCorso bean = new TipoCorso();
  		bean.setNome( rs.getString( "nome" ) );
  		bean.setDescrizione( rs.getString( "descrizione" ) );

  		return bean;
  }
  	
  	private ProgrammazioneCorso makeProgrammazioneCorso( ResultSet rs ) throws SQLException {
  		ProgrammazioneCorso bean = new ProgrammazioneCorso();
  		bean.setG_sett( rs.getString( 1 ) );
  		bean.setOra_i(rs.getTime(2));
  		bean.setOra_f(rs.getTime(3));

  		return bean;
  }
 
  	private Materiale makeMaterialeCorso( ResultSet rs ) throws SQLException {
  		Materiale bean = new Materiale();
  		bean.setId(rs.getInt("id"));
  		bean.setPath(rs.getString("path"));
  		bean.setNome(rs.getString("nome"));
  		bean.setTipo(rs.getString("tipo"));
  		bean.setFormato(rs.getString("formato"));
  		return bean;
  }
  	
  	private Docente makeDocenteBean( ResultSet rs ) throws SQLException {
  		Docente bean = new Docente();
  		bean.setCodice(rs.getInt("codice"));
  		bean.setNome( rs.getString( "nome" ) );
  		bean.setCognome(rs.getString( "cognome" ));

  		return bean;
  }
  	
  private Utente makeUserBean(ResultSet rs) throws SQLException {
	  Utente bean = new Utente();
	  bean.setNome(rs.getString("nome"));
	  bean.setCognome(rs.getString("cognome"));
	  bean.setDn(rs.getDate("dn"));
	  bean.setMail(rs.getString("email"));
	  
	  return bean;
	  
	  
  }
  
  private Iscritti makeIscrittiCorsoBean(ResultSet rs) throws SQLException {
	  Iscritti bean = new Iscritti();
	  bean.setNome(rs.getString("nome"));
	  bean.setCognome(rs.getString("cognome"));
	  bean.setData_i(rs.getDate("data_i"));
	  return bean;
}
//  
//
//  private PresideFacolta makePFBean( ResultSet rs ) throws SQLException {
//	  PresideFacolta bean = new PresideFacolta();
//	  bean.setNomef(rs.getString("nomef"));
//	  bean.setCodice(rs.getString("codice"));
//	  bean.setIndirizzo(rs.getString("indirizzo"));
//	  bean.setNomep(rs.getString("nomep"));
//	  bean.setCognomep(rs.getString("cognomep"));
//	  
//      return bean;
//  }

  // ===========================================================================


  public  List<Corso> getCorsi( String tipoCorso ) {
    // Dichiarazione delle variabili necessarie
    Connection con = null;
    PreparedStatement pstm = null;
    ResultSet rs = null;
    List<Corso> result = new ArrayList<Corso>();
    try {
      // tentativo di connessione al database
      con = getConnection();
      // connessione riuscita, ottengo l'oggetto per l'esecuzione dell'interrogazione.
      pstm = con.prepareStatement( MyQuery.getqSelectCorso() );
      pstm.clearParameters();
      // imposto i parametri della query
      pstm.setString( 1, tipoCorso );
      // eseguo la query
      rs = pstm.executeQuery();
      // memorizzo il risultato dell'interrogazione in Vector di Bean
   
      
      while( rs.next() ) {
          result.add(  makeCorsoBean( rs ) );
        }

    } catch( SQLException sqle ) { // Catturo le eventuali eccezioni
      sqle.printStackTrace();

    } finally { // alla fine chiudo la connessione.
      try {
    	  rs.close();
     	 pstm.close();
        con.close();
      } catch( SQLException sqle1 ) {
        sqle1.printStackTrace();
      }
    }
    return result;
  }


  public List<Iscritti> getIscrittiCorso(int idCorso) {
	  Connection con = null;
	    PreparedStatement pstm = null;
	    ResultSet rs = null;
	    List<Iscritti> result = new ArrayList<Iscritti>();
	    try {
	        con = getConnection();
	        pstm = con.prepareStatement(MyQuery.getqSelectStudentiPerCorso());
	        pstm.setInt(1, idCorso);
	        rs = pstm.executeQuery( );
	    
	        while( rs.next() ) {
	          result.add( makeIscrittiCorsoBean( rs ) );
	        }

	      } catch( SQLException sqle ) { 
	        sqle.printStackTrace();

	      } finally { 
	        try {
	        	 rs.close();
	        	 pstm.close();
	          con.close();
	        } catch( SQLException sqle1 ) {
	          sqle1.printStackTrace();
	        }
	      }
	      return result;
	    }
   
  public List<Corso> getDettagliCorsi(int idCorso) {
	  Connection con = null;
	  PreparedStatement pstm = null;
	    ResultSet rs = null;
	    List<Corso> result = new ArrayList<Corso>();
	    try {
	        con = getConnection();
	        pstm = con.prepareStatement(MyQuery.getqSelectDettaglioCorso() );
	        pstm.setInt(1, idCorso);
	        rs = pstm.executeQuery( );
		    
	    
	        while( rs.next() ) {
	          result.add( makeCorsoDettaglioBean( rs ) );
	        }

	      } catch( SQLException sqle ) { 
	        sqle.printStackTrace();

	      } finally { 
	        try {
	        	 rs.close();
	        	 pstm.close();
	          con.close();
	        } catch( SQLException sqle1 ) {
	          sqle1.printStackTrace();
	        }
	      }
	      return result;
	    }
	

  public List<Docente> getListaDocenti(int idCorso) {
	  Connection con = null;
	  PreparedStatement pstm = null;
	    ResultSet rs = null;
	    List<Docente> result = new ArrayList<Docente>();
	    try {
	        con = getConnection();
	        pstm = con.prepareStatement(MyQuery.getqSelectDocentiCorso());
	        pstm.setInt(1, idCorso);
	        rs = pstm.executeQuery( );
		    
	    
	        while( rs.next() ) {
	          result.add( makeDocenteBean( rs ) );
	        }

	      } catch( SQLException sqle ) { 
	        sqle.printStackTrace();

	      } finally { 
	        try {
	        	 rs.close();
	        	 pstm.close();
	          con.close();
	        } catch( SQLException sqle1 ) {
	          sqle1.printStackTrace();
	        }
	      }
	      return result;
	    }
	
 
  

public List<TipoCorso> getTipiCorso() {
    // dichiarazione delle variabili
    Connection con = null;
    Statement stmt = null;
    ResultSet rs = null;
    List<TipoCorso> result = new ArrayList<TipoCorso>();

    try {
      con = getConnection();
      stmt = con.createStatement();
      rs = stmt.executeQuery( MyQuery.getqSelectTipiCorso() );
  
      while( rs.next() ) {
        result.add( makeTipoCorsoBean( rs ) );
      }

    } catch( SQLException sqle ) { 
      sqle.printStackTrace();

    } finally { 
      try {
    	  rs.close();
    	  stmt.close();
        con.close();
      } catch( SQLException sqle1 ) {
        sqle1.printStackTrace();
      }
    }
    return result;
  }

  
  public ArrayList<Utente> checkLogin(String email, String password){
	  Connection con = null;
	  PreparedStatement pstm = null;
	  ResultSet rs = null;
	  ArrayList<Utente> result = new ArrayList<Utente>();
	  try{
		 con = getConnection();
		 pstm = con.prepareStatement(MyQuery.getqSelectLogin());
		 pstm.setString(1, email);
		 pstm.setString(2, password);
		 rs = pstm.executeQuery();
		 if(rs.next()){
			 result.add( makeUserBean(rs));
		 }else result = null;
	  }catch(SQLException e){
	  	  e.printStackTrace();
	
	  }finally{
		  try {
			  rs.close();
		    	pstm.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  }
	  return result;
  }
  
  
  public Connection getConnection(){
	  
	  try {
		return DriverManager.getConnection( url, user, passwd );
	} catch (SQLException e) {
		System.out.println("non riesco a creare la connessione");
		e.printStackTrace();
		return null;
	}
	  
  }


public int getNumeroIscrittiCorso(int idCorso) {
	Connection con = null;
    PreparedStatement pstm = null;
    ResultSet rs = null;
   int result = 0;
    try {
    	con = getConnection();
        pstm = con.prepareStatement(MyQuery.getCountIscrittiCorso());
        pstm.setInt(1, idCorso);
        rs = pstm.executeQuery();	
    	
    	
  
      if( rs.next() ) {
    	  result = rs.getInt(1);
       
      }

    } catch( SQLException sqle ) { 
      sqle.printStackTrace();

    } finally { 
      try {
    	  rs.close();
      	pstm.close();
        con.close();
      } catch( SQLException sqle1 ) {
        sqle1.printStackTrace();
      }
    }
    return result;
  }


public String getNomeCorso(int idCorso) {
	Connection con = null;
    PreparedStatement pstm = null;
    ResultSet rs = null;
    String result = null;
    try {
    	  con = getConnection();
          pstm = con.prepareStatement(MyQuery.getqSelectCorsoMin());
          pstm.setInt(1, idCorso);
          rs = pstm.executeQuery();
    
  
      if( rs.next() ) {
    	  result = rs.getString(1);
       
      }

    } catch( SQLException sqle ) { 
      sqle.printStackTrace();

    } finally { 
      try {
    	  rs.close();
      	pstm.close();
        con.close();
      } catch( SQLException sqle1 ) {
        sqle1.printStackTrace();
      }
    }
    return result;
  }

public String getObiettiviFormativiCorso(int idCorso) {
	Connection con = null;
    PreparedStatement pstm = null;
    ResultSet rs = null;
    String result = null;
    try {
      con = getConnection();
      pstm = con.prepareStatement(MyQuery.getqSelectCorsoMin());
      pstm.setInt(1, idCorso);
      rs = pstm.executeQuery();
      if( rs.next() ) {
    	  result = rs.getString(2);
       
      }

    } catch( SQLException sqle ) { 
      sqle.printStackTrace();

    } finally { 
      try {rs.close();
  	pstm.close();
        con.close();
      } catch( SQLException sqle1 ) {
        sqle1.printStackTrace();
      }
    }
    return result;
  }


public List<Date> getPeriodoSvolgimentoCorso(int idCorso) {
	Connection con = null;
    PreparedStatement pstm = null;
    ResultSet rs = null;
    List<Date> result =new ArrayList<Date>(); 
    
    try {
      con = getConnection();
      pstm = con.prepareStatement(MyQuery.getqSelectPeriodoCorso());
      pstm.setInt(1, idCorso);
      rs = pstm.executeQuery();
      
      if( rs.next() ) {
    	  result.add(rs.getDate(1));
    	  result.add(rs.getDate(2));
       
      }

    } catch( SQLException sqle ) { 
      sqle.printStackTrace();

    } finally { 
      try {
    	rs.close();
    	pstm.close();
        con.close();
      } catch( SQLException sqle1 ) {
        sqle1.printStackTrace();
      }
    }
    return result;
}


public ArrayList<ProgrammazioneCorso> getProgrammazioneCorso(int idCorso) {
	Connection con = null;
    PreparedStatement pstm1 = null;
    Statement stm = null;
    ResultSet rs = null;
    ArrayList<ProgrammazioneCorso> result =new ArrayList<ProgrammazioneCorso>(); 
    System.out.println("sono nel metodono programmazione corso");
    try {
    
      System.out.println("sono nel try");
      con = getConnection();
      
     
      pstm1 = con.prepareStatement(MyQuery.getqSelectProgrammazioneCorso());
      pstm1.setInt(1, idCorso);
      rs = pstm1.executeQuery();
      pstm1.clearParameters();

     while(rs.next() ) {
    	  result.add(makeProgrammazioneCorso(rs));
    	       System.out.println("size di result="+result.size());
      }

    } catch( SQLException sqle ) { 
      sqle.printStackTrace();

    } finally { 
      try {
    	rs.close();
      	pstm1.close();
        con.close();
      } catch( SQLException sqle1 ) {
        sqle1.printStackTrace();
      }
    }
    return result;
}


public ArrayList<Materiale> getMaterialeCorso(int idCorso) {
	Connection con = null;
    PreparedStatement pstm1 = null;
    ResultSet rs = null;
    ArrayList<Materiale> result =new ArrayList<Materiale>(); 
    try {
   
      con = getConnection();
      
     
      pstm1 = con.prepareStatement(MyQuery.getqSelectMaterialeCorso());
      pstm1.setInt(1, idCorso);
      rs = pstm1.executeQuery();
      pstm1.clearParameters();

     while(rs.next() ) {
    	  result.add(makeMaterialeCorso(rs));
    	      
      }

    } catch( SQLException sqle ) { 
      sqle.printStackTrace();

    } finally { 
      try {
    	rs.close();
      	pstm1.close();
        con.close();
      } catch( SQLException sqle1 ) {
        sqle1.printStackTrace();
      }
    }
    return result;
}


public ArrayList<Corso> getListaCorsiUtente(String email) {
	Connection con = null;
    PreparedStatement pstm1 = null;
    ResultSet rs = null;
    ArrayList<Corso> result =new ArrayList<Corso>(); 
    try {
   
      con = getConnection();
      
     
      pstm1 = con.prepareStatement(MyQuery.getqSelectCorsiPerStudente());
      pstm1.setString(1, email);
      rs = pstm1.executeQuery();
      pstm1.clearParameters();

     while(rs.next() ) {
    	  result.add(makeCorsiStudenteBean(rs));
    	      
      }

    } catch( SQLException sqle ) { 
      sqle.printStackTrace();

    } finally { 
      try {
    	rs.close();
      	pstm1.close();
        con.close();
      } catch( SQLException sqle1 ) {
        sqle1.printStackTrace();
      }
    }
    return result;
}


public ArrayList<Materiale> getMaterialeUtente(String email) {
	Connection con = null;
    PreparedStatement pstm1 = null;
    ResultSet rs = null;
    ArrayList<Materiale> result =new ArrayList<Materiale>(); 
    try {
   
      con = getConnection();
      
     
      pstm1 = con.prepareStatement(MyQuery.getqSelectMaterialePerStudente());
      pstm1.setString(1, email);
      rs = pstm1.executeQuery();
      pstm1.clearParameters();

     while(rs.next() ) {
    	  result.add(makeMaterialeCorso(rs));
    	      
      }

    } catch( SQLException sqle ) { 
      sqle.printStackTrace();

    } finally { 
      try {
    	rs.close();
      	pstm1.close();
        con.close();
      } catch( SQLException sqle1 ) {
        sqle1.printStackTrace();
      }
    }
    return result;
}



}










