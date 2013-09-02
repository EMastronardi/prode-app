package com.biscotti.prodeapp;

import java.util.Collection;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class Boleta extends Activity {
	TableLayout boletaTable = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
		//Leer JSON
	 	setContentView(R.layout.activity_boleta);
	 	boletaTable =(TableLayout) findViewById(R.id.tblBoleta);
	 	generateBoleta();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.boleta, menu);
		return true;
	}

	@SuppressWarnings("deprecation")
	private void generateBoleta(){
		
		// JSON Node names
		String TAG_MATCH = "match";
		String TAG_EQUIPOL = "local";
		String TAG_EQUIPOV = "visitor";
		String TAG_DATE = "date";
		String TAG_IDLOCAL = "team1";
		String TAG_IDVISITOR = "team2";
		
		// contacts JSONArray
		JSONArray matchs = null;
		// Creating JSON Parser instance
		JSONParser jParser = new JSONParser();
		 
		// getting JSON string from URL
		JSONObject json = jParser.getFromFile(this);
		//JSONObject json =  jParser.getJSONFromUrl("http://www.resultados-futbol.com/scripts/api/api.php?key=ed6def436156233b75d0637a89b674c5&format=json&req=matchs&league=26&round=5");
		
		try {
		    // Getting Array of Contacts
		    matchs = json.getJSONArray(TAG_MATCH);
		    String keyBreak = "";  
		    // looping through All Contacts
		    for(int i = 0; i < matchs.length(); i++){
		        JSONObject c = matchs.getJSONObject(i);     
		        String date = c.getString(TAG_DATE);
		        if(!keyBreak.equals(date)){
		        	//Generacion del TEXTVIEW 
		        	keyBreak = date;
		        	TableRow tblRow = new TableRow(this);
		        	tblRow.setBackgroundResource(R.drawable.gradients);
		        	TextView txt = new TextView(this);
		        	txt.setText(date);
		        	tblRow.addView(txt);
		        	boletaTable.addView(tblRow);
		        }
		        TableRow tblRowEq = new TableRow(this);
		        Button btnLocal = new Button(this);
		        //btnLocal.setId(i);
		        btnLocal.setText(c.getString(TAG_EQUIPOL));
		        btnLocal.setBackgroundColor(getResources().getColor(R.color.blanco));
		        android.view.Display display = ((android.view.WindowManager)getSystemService(this.WINDOW_SERVICE)).getDefaultDisplay();      
		        btnLocal.setWidth((int)(display.getWidth()/3));		
		        // Storing each json item in variable
		        Button btnEmpate = new Button(this);
		        //empate.setId(i);
		        btnEmpate.setText("Empate");
		        btnEmpate.setBackgroundColor(getResources().getColor(R.color.blanco));      
		        btnEmpate.setWidth((int)(display.getWidth()/3));	
		        //String equipoLocal = c.getString(TAG_EQUIPOL);
		        Button btnVisit = new Button(this);
		        //btnVisit.setId(i);
		        btnVisit.setText(c.getString(TAG_EQUIPOV));
		        btnVisit.setBackgroundColor(getResources().getColor(R.color.blanco));      
		        btnVisit.setWidth((int)(display.getWidth()/3));	
		        tblRowEq.addView(btnLocal);
		        tblRowEq.addView(btnEmpate);
		        tblRowEq.addView(btnVisit);
		        
		        boletaTable.addView(tblRowEq);
		        Integer idLocal = c.getInt(TAG_IDLOCAL);
		        Integer idVisitante = c.getInt(TAG_IDVISITOR);
		    }
		} catch (JSONException e) {
		    e.printStackTrace();
		}
	}
}
