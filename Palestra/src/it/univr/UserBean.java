package it.univr;



import it.univr.database.DataSource;

import java.io.Serializable;
import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import javax.faces.bean.ManagedBean;    
import javax.faces.bean.SessionScoped; 


@ManagedBean(name="user")
@SessionScoped
public class UserBean implements Serializable {
	
	private String nome;
	private String cognome;
	private Date dn;
	private String email;
	private String password;

	
	public String getName() { return nome ; }   
	public void setName(String newValue) { nome = newValue; }

	public String getPassword() { return password;	}
	public void setPassword(String newValue){ password = newValue;	}   

	public String getNome() {return nome;}
	public String getCognome() {return cognome;	}
	
	public Date getDn() {return dn;	}
	public void setDn(Date dn) {	this.dn= dn;	}
	
	public String getEmail() {	return email;}
	public void setEmail(String email) { this.email = email; }
	
	public void setNome(String nome) {	this.nome = nome;	}
	public void setCognome(String cognome) { this.cognome = cognome;	}
	

	public String checkLogin() throws ClassNotFoundException, SQLException{

		DataSource ds = new DataSource();
		Connection con = ds.getConnection();
		System.out.println("connesione aperta: "+con);
		PreparedStatement pstm = con.prepareStatement(MyQuery.qSelectLogin);

		pstm.setString(1, email);
		pstm.setString(2, password);
		
		ResultSet rs = pstm.executeQuery();

		if(rs.next()){
			nome = rs.getString("nome");
			cognome = rs.getString("cognome");
			dn = rs.getDate("dn");
			email= rs.getString("email");
			con.close();
			System.out.println("Connessione chiusa e passaggio ad OK: "+con);
			return "loginOK";
		}

		else {
			con.close();
			System.out.println("Connessione chiusa e passaggio a Denied: "+con);
			return "loginDenied";
	
		}		


	}



}


