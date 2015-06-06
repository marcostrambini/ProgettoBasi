package it.univr.database;

import it.univr.MyQuery;
import it.univr.UserBean;

import java.io.Serializable;
import java.sql.Connection;
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


  	private TipoCorso makeTipoCorsoBean( ResultSet rs ) throws SQLException {
  		TipoCorso bean = new TipoCorso();
  		bean.setNome( rs.getString( "nome" ) );
  		bean.setDescrizione( rs.getString( "descrizione" ) );

  		return bean;
  }
  	
  private UserBean makeUserBean(ResultSet rs) throws SQLException {
	  UserBean bean = new UserBean();
	  bean.setNome(rs.getString("nome"));
	  bean.setCognome(rs.getString("cognome"));
	  bean.setDn(rs.getDate("dn"));
	  bean.setEmail(rs.getString("email"));
	  
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
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    List<Corso> result = new ArrayList<Corso>();
    try {
      // tentativo di connessione al database
      con = getConnection();
      // connessione riuscita, ottengo l'oggetto per l'esecuzione dell'interrogazione.
      pstmt = con.prepareStatement( MyQuery.getqSelectCorso() );
      pstmt.clearParameters();
      // imposto i parametri della query
      pstmt.setString( 1, tipoCorso );
      // eseguo la query
      rs = pstmt.executeQuery();
      // memorizzo il risultato dell'interrogazione in Vector di Bean
   
      
      while( rs.next() ) {
          result.add(  makeCorsoBean( rs ) );
        }

    } catch( SQLException sqle ) { // Catturo le eventuali eccezioni
      sqle.printStackTrace();

    } finally { // alla fine chiudo la connessione.
      try {
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
        con.close();
      } catch( SQLException sqle1 ) {
        sqle1.printStackTrace();
      }
    }
    return result;
  }

  
  public boolean checkLogin(String email, String password){
	  Connection con = null;
	  PreparedStatement pstm = null;
	  ResultSet rs = null;
	  try{
		 con = getConnection();
		 pstm = con.prepareStatement(MyQuery.getqSelectLogin());
		 pstm.setString(1, email);
		 pstm.setString(2, password);
		 rs = pstm.executeQuery();
		 if(rs.next())
			 return true;
		 else
			 return false;
		 
		 
	  }catch(SQLException e){
	  	  e.printStackTrace();
	  return false;
	  }finally{
		  try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  }
  }
  
  /**
   * Metodo per il recupero della/e facolta' di appartenenza del corso di studi
   * con l'id specificato.
   *
   * @param id
   * @return
   */
//  public String getFacoltaCorso( int id ) {
//    // dichiarazione delle variabili
//    Connection con = null;
//    PreparedStatement pstmt = null;
//    ResultSet rs = null;
//    String result = null;
//
//    try {
//      // tentativo di connessione al database
//      con = DriverManager.getConnection( url, user, passwd );
//      // Connessione riuscita, ottengo l'oggetto per l'esecuzione
//      // dell'interrogazione.
//      pstmt = con.prepareStatement( csf );
//      pstmt.clearParameters();
//      pstmt.setInt( 1, id );
//      rs = pstmt.executeQuery();
//
//      // memorizzo il risultato dell'interrogazione nel Bean
//      if( rs.next() ) {
//        result = rs.getString( "Nome" );
//      }
//
//    } catch( SQLException sqle ) { // catturo le eventuali eccezioni!
//      sqle.printStackTrace();
//
//    } finally { // alla fine chiudo la connessione.
//      try {
//        con.close();
//      } catch( SQLException sqle1 ) {
//        sqle1.printStackTrace();
//      }
//    }
//    return result;
//  }
//  
//  public List<PresideFacolta> getPresideFacolta() {
//	    // dichiarazione delle variabili
//	    Connection con = null;
//	    Statement stmt = null;
//	    ResultSet rs = null;
//	    List<PresideFacolta> result = new ArrayList<PresideFacolta>();
//
//	    try {
//	      // tentativo di connessione al database
//	      con = DriverManager.getConnection( url, user, passwd );
//	      // connessione riuscita, ottengo l'oggetto per l'esecuzione dell'interrogazione.
//	      stmt = con.createStatement();
//	      // eseguo l'interrogazione desiderata
//	      rs = stmt.executeQuery( q2 );
//	      // memorizzo il risultato dell'interrogazione nel Vector
//	      while( rs.next() ) {
//	        result.add( makePFBean( rs ) );
//	      }
//
//	    } catch( SQLException sqle ) { // catturo le eventuali eccezioni!
//	      sqle.printStackTrace();
//
//	    } finally { // alla fine chiudo la connessione.
//	      try {
//	        con.close();
//	      } catch( SQLException sqle1 ) {
//	        sqle1.printStackTrace();
//	      }
//	    }
//	    return result;
//	  }
//  
//  public String getDettaglioPreside( String nomef ) {
//	    // dichiarazione delle variabili
//	    Connection con = null;
//	    PreparedStatement pstmt = null;
//	    ResultSet rs = null;
//	    String result = null;
//
//	    try {
//	      // tentativo di connessione al database
//	      con = DriverManager.getConnection( url, user, passwd );
//	      // Connessione riuscita, ottengo l'oggetto per l'esecuzione
//	      // dell'interrogazione.
//	      pstmt = con.prepareStatement( dettaglioPreside );
//	      pstmt.clearParameters();
//	      pstmt.setString( 1, nomef );
//	      rs = pstmt.executeQuery();
//
//	      // memorizzo il risultato dell'interrogazione nel Bean
//	      if( rs.next() ) {
//	        result = rs.getString( "nomep" ) + " "+rs.getString("cognomep");
//	      }
//
//	    } catch( SQLException sqle ) { // catturo le eventuali eccezioni!
//	      sqle.printStackTrace();
//
//	    } finally { // alla fine chiudo la connessione.
//	      try {
//	        con.close();
//	      } catch( SQLException sqle1 ) {
//	        sqle1.printStackTrace();
//	      }
//	    }
//	    return result;
//	  }
  
  public Connection getConnection(){
	  
	  try {
		return DriverManager.getConnection( url, user, passwd );
	} catch (SQLException e) {
		System.out.println("non riesco a creare la connessione");
		e.printStackTrace();
		return null;
	}
	  
  }




}
