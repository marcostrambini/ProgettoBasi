package it.univr.database;

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


  /**
   * restituisce un bean di Corso
   * @param rs
   * @return
   * @throws SQLException
   */
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
  
  /**
   * restituisce un bean di Corso ristretto
   * @param rs
   * @return
   * @throws SQLException
   */
  private Corso makeCorsiStudenteBean(ResultSet rs) throws SQLException{
	  Corso bean = new Corso();
	  bean.setId(rs.getInt("id"));
	  bean.setNome_corso(rs.getString("nome"));
	  return bean;
  }
  
  /**
   * restituice un bean di Corso con dettagli
   * @param rs
   * @return
   * @throws SQLException
   */
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
  
  
  
  	/**
  	 * restituisce un bean di TipoCorso
  	 * @param rs
  	 * @return
  	 * @throws SQLException
  	 */
  	private TipoCorso makeTipoCorsoBean( ResultSet rs ) throws SQLException {
  		TipoCorso bean = new TipoCorso();
  		bean.setNome( rs.getString( "nome" ) );
  		bean.setDescrizione( rs.getString( "descrizione" ) );

  		return bean;
  }
  	
  	/**
  	 * restituisce un bean per la programmazione dei corsi
  	 * @param rs
  	 * @return
  	 * @throws SQLException
  	 */
  	private ProgrammazioneCorso makeProgrammazioneCorso( ResultSet rs ) throws SQLException {
  		ProgrammazioneCorso bean = new ProgrammazioneCorso();
  		bean.setG_sett( rs.getString( 1 ) );
  		bean.setOra_i(rs.getTime(2));
  		bean.setOra_f(rs.getTime(3));

  		return bean;
  }
 
  	/**
  	 * restituisce un bean di tipo Materiale
  	 * @param rs
  	 * @return
  	 * @throws SQLException
  	 */
  	private Materiale makeMaterialeCorso( ResultSet rs ) throws SQLException {
  		Materiale bean = new Materiale();
  		bean.setId(rs.getInt("id"));
  		bean.setPath(rs.getString("path"));
  		bean.setNome(rs.getString("nome"));
  		bean.setTipo(rs.getString("tipo"));
  		bean.setFormato(rs.getString("formato"));
  		return bean;
  }
  	
  	/**
  	 * restituisce un bean di tipo Docente
  	 * @param rs
  	 * @return
  	 * @throws SQLException
  	 */
  	private Docente makeDocenteBean( ResultSet rs ) throws SQLException {
  		Docente bean = new Docente();
  		bean.setCodice(rs.getInt("codice"));
  		bean.setNome( rs.getString( "nome" ) );
  		bean.setCognome(rs.getString( "cognome" ));

  		return bean;
  }
  	
  /**
   * restituisce un bean di tipo Utente
   * @param rs
   * @return
   * @throws SQLException
   */
  private Utente makeUserBean(ResultSet rs) throws SQLException {
	  Utente bean = new Utente();
	  bean.setNome(rs.getString("nome"));
	  bean.setCognome(rs.getString("cognome"));
	  bean.setDn(rs.getDate("dn"));
	  bean.setMail(rs.getString("email"));
	  
	  return bean;
	  
	  
  }
  
  /**
   * restituisce un bean di tipo Iscritti
   * @param rs
   * @return
   * @throws SQLException
   */
  private Iscritti makeIscrittiCorsoBean(ResultSet rs) throws SQLException {
	  Iscritti bean = new Iscritti();
	  bean.setNome(rs.getString("nome"));
	  bean.setCognome(rs.getString("cognome"));
	  bean.setData_i(rs.getDate("data_i"));
	  return bean;
}



  /**
   * metodo che recupera una lista di Corsi
   * @param tipoCorso
   * @return
   */
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

  /**
   * metodo che recupera una lista di Iscritti
   * @param idCorso
   * @return
   */
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
   
  /**
   * metodo che recupera una lista di Corso
   * @param idCorso
   * @return
   */
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
	

  /**
   * metodo che recupera una lista di Docente
   * @param idCorso
   * @return
   */
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
	
 
  
/**
 * metodo che recupera una lista di TipoCorso
 * @return
 */
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

  /**
   * metodo che recupera una lista di Utente
   * @param email
   * @param password
   * @return
   */
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
  
  /**
   * metodo che recupera una lista di Utente
   * @return
   */
  public ArrayList<Utente> getStudenti(){
	  Connection con = null;
	  Statement stm = null;
	  ResultSet rs = null;
	  ArrayList<Utente> result = new ArrayList<Utente>();
	  try{
		 con = getConnection();
		stm=con.createStatement();
		
		 rs =stm.executeQuery(MyQuery.getqSelectStudenti());
		 while(rs.next()){
			 result.add( makeUserBean(rs));
		 }
	  }catch(SQLException e){
	  	  e.printStackTrace();
	
	  }finally{
		  try {
			  rs.close();
		    	stm.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  }
	  return result;
  }
  
  /**
   * metodo che restituisce un oggetto di tipo Connection al db di riferimento
   * @return
   */
  public Connection getConnection(){
	  
	  try {
		return DriverManager.getConnection( url, user, passwd );
	} catch (SQLException e) {
		System.out.println("non riesco a creare la connessione");
		e.printStackTrace();
		return null;
	}
	  
  }

/**
 * metodo che restituisce il nuemro di iscritti ad un determinato corso
 * @param idCorso
 * @return
 */
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

/**
 * metodo che restituisce il nome di un corso dato l'id
 * @param idCorso
 * @return
 */
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

/**
 * metodo che restituisce gli obiettivi formativi di un determinato corso
 * @param idCorso
 * @return
 */
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

/**
 * metodo che restituisce il periodo di svolgimento di un determinato corso
 * @param idCorso
 * @return
 */
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

/**
 * metodo che restituisce la lista di programmazione di un determinato corso
 * @param idCorso
 * @return
 */
public ArrayList<ProgrammazioneCorso> getProgrammazioneCorso(int idCorso) {
	Connection con = null;
    PreparedStatement pstm1 = null;
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

/**
 * metodo che restituisce la lista dei materiali utilizzati da u determinato corso
 * @param idCorso
 * @return
 */
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

/**
 * metodo che restituisce la lista dei corsi a cui è iscritto uno studente
 * @param email
 * @return
 */
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

/**
 * metodo che restituisce la lista dei materiali a disposizione di un singolo utente
 * @param email
 * @return
 */
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

/**
 * metodo che restituisce il numero degli iscritti alla palestra
 * @return
 */
public int getNumeroIscritti() {
	Connection con = null;
    Statement stm = null;
    ResultSet rs = null;
  int result = 0;
    
    try {
      con = getConnection();
      stm = con.createStatement();
      rs = stm.executeQuery(MyQuery.getQcountStudenti());      
      if( rs.next() ) {
    	  result = rs.getInt(1);
       
      }

    } catch( SQLException sqle ) { 
      sqle.printStackTrace();

    } finally { 
      try {
    	rs.close();
    	stm.close();
        con.close();
      } catch( SQLException sqle1 ) {
        sqle1.printStackTrace();
      }
    }
    return result;
}



}










