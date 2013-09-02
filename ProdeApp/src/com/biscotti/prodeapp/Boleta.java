package com.biscotti.prodeapp;

import java.util.Collection;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class Boleta extends Activity {
	TableLayout boletaTable = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		boletaTable =(TableLayout) findViewById(R.id.tblBoleta);
		generateBoleta();
		//Leer JSON
	 	setContentView(R.layout.activity_boleta);
	 	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.boleta, menu);
		return true;
	}

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
		    String keyBreak = null;  
		    // looping through All Contacts
		    for(int i = 0; i < matchs.length(); i++){
		        JSONObject c = matchs.getJSONObject(i);     
		        String date = c.getString(TAG_DATE);
		        if(!keyBreak.equals(date)){
		        	//Generacion del TEXTVIEW 
		        	TableRow tblRow = new TableRow(this);
		        	tblRow.setBackgroundResource(R.drawable.gradients);
		        	TextView txt = new TextView(this);
		        	txt.setText(date);
		        	tblRow.addView(tblRow);
		        	boletaTable.addView(tblRow);
		        }
		        
		        // Storing each json item in variable
		        String equipoLocal = c.getString(TAG_EQUIPOL);
		        String equipoVisitante = c.getString(TAG_EQUIPOV);
		        
		        Integer idLocal = c.getInt(TAG_IDLOCAL);
		        Integer idVisitante = c.getInt(TAG_IDVISITOR);
		    }
		} catch (JSONException e) {
		    e.printStackTrace();
		}
	}
}
