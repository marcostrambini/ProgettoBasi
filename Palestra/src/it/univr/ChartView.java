package it.univr;



import it.univr.database.DataSource;
import it.univr.database.MyQuery;

import javax.annotation.PostConstruct;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.faces.bean.ManagedBean;
 







import javax.faces.bean.SessionScoped;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.HorizontalBarChartModel;
import org.primefaces.model.chart.ChartSeries;
 /**
  * bean di supporto per la generazione del grafico
  * @author Mago
  *
  */
@ManagedBean

public class ChartView implements Serializable {
 
    private BarChartModel barModel;
    private HorizontalBarChartModel horizontalBarModel;
 
    @PostConstruct
    public void init() {
        createBarModels();
    }
 
    public BarChartModel getBarModel() {
        return barModel;
    }
     
    public HorizontalBarChartModel getHorizontalBarModel() {
        return horizontalBarModel;
    }
 
    private BarChartModel initBarModel() {
        BarChartModel model = new BarChartModel();
 
        ChartSeries corsi = new ChartSeries();
        corsi.setLabel("corsi");
        
        DataSource ds = null;;
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;
		try {
			ds = new DataSource();
			con = ds.getConnection();
			stm = con.createStatement();
			rs = stm.executeQuery(MyQuery.getqStatIscrittiCorsi());
			while(rs.next()){
        
				System.out.println("Nome corso: "+rs.getString("nome")+ " Iscritti="+rs.getInt("numero_iscritti"));
				corsi.set(rs.getString("nome"), rs.getInt("numero_iscritti"));

			}

 
        model.addSeries(corsi);

         
        
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			try {
				rs.close();
				stm.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
        return model;
    }
     
    private void createBarModels() {
        createBarModel();
        createHorizontalBarModel();
    }
     
    private void createBarModel() {
        barModel = initBarModel();
         
        barModel.setTitle("Info Iscrizioni");
        barModel.setLegendPosition("ne");
         
        Axis xAxis = barModel.getAxis(AxisType.X);
        xAxis.setLabel("Corsi");
         
        Axis yAxis = barModel.getAxis(AxisType.Y);
        yAxis.setLabel("Iscritti");
        yAxis.setMin(0);
        yAxis.setMax(100);
    }
     
    private void createHorizontalBarModel() {
        horizontalBarModel = new HorizontalBarChartModel();
 

        ChartSeries corsi = new ChartSeries();
        corsi.setLabel("corsi");
        
        DataSource ds = null;;
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;
		try {
			ds = new DataSource();
			con = ds.getConnection();
			stm = con.createStatement();
			rs = stm.executeQuery(MyQuery.getqStatIscrittiCorsi());
			while(rs.next()){
        
				System.out.println("Nome corso: "+rs.getString("nome")+ " Iscritti="+rs.getInt("numero_iscritti"));
				corsi.set(rs.getString("nome"), rs.getInt("numero_iscritti"));

			}
 
        horizontalBarModel.addSeries(corsi);
        
        
      		} catch (ClassNotFoundException e) {
      			// TODO Auto-generated catch block
      			e.printStackTrace();
      		} catch (SQLException e) {
      			// TODO Auto-generated catch block
      			e.printStackTrace();
      		} finally{
      			try {
      				rs.close();
      				stm.close();
      				con.close();
      			} catch (SQLException e) {
      				// TODO Auto-generated catch block
      				e.printStackTrace();
      			}
    
         
        horizontalBarModel.setTitle("Iscrizioni ai corsi");
        horizontalBarModel.setLegendPosition("e");
        horizontalBarModel.setStacked(true);
         
        Axis xAxis = horizontalBarModel.getAxis(AxisType.X);
        xAxis.setLabel("Iscritti");
        xAxis.setMin(0);
        xAxis.setMax(100);
         
        Axis yAxis = horizontalBarModel.getAxis(AxisType.Y);
        yAxis.setLabel("Corsi");        
    }
    }
}