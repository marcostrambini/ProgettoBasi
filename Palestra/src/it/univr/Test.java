package it.univr;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import it.univr.database.DataSource;
import it.univr.database.ProgrammazioneCorso;

public class Test {
	
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException{
		
		DataSource ds = new DataSource();
		ArrayList<ProgrammazioneCorso> pc = new ArrayList<ProgrammazioneCorso>();
		
		Connection con = ds.getConnection();
		PreparedStatement pstm = con.prepareStatement(MyQuery.qSelectProgrammazioneCorso);
		pstm.setInt(1, 1);
		ResultSet rs = pstm.executeQuery();
		
		
		
		while(rs.next()){
			System.out.println(rs.getString("g_sett")+" "+rs.getTime("ora_i")+" "+rs.getTime("ora_f"));
			ProgrammazioneCorso pro = new ProgrammazioneCorso();
			pro.setG_sett(rs.getString("g_sett"));
			pro.setOra_i(rs.getTime("ora_i"));
			pro.setOra_f(rs.getTime("ora_f"));
			
//			pc.add(pro);
			
	}
		
		pc = null;
		pc = ds.getProgrammazioneCorso(1);
		
		for(int i =0;i<pc.size();i++)
			System.out.println("Con datasource: "+pc.get(i).getG_sett()+" "+pc.get(i).getOra_i()+" "+pc.get(i).getOra_f());

}}
