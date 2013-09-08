package com.biscotti.prodeapp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
//import android.view.Menu;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.biscotti.objects.Equipo;
import com.biscotti.persistence.EquipoDAO;
import com.biscotti.persistence.ProdeOpenHelper;


public class Boleta extends SherlockActivity{
	TableLayout boletaTable = null;
	EquipoDAO dao = null;
	ProdeOpenHelper db;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);	
		//Leer JSON
	 	setContentView(R.layout.activity_boleta);
	 	db = new ProdeOpenHelper(this);
	 	dao = new EquipoDAO(this);
	 	boletaTable =(TableLayout) findViewById(R.id.tblBoleta);
	 	generateBoleta();
	}

//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.boleta, menu);
//		return true;
//	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		 MenuInflater inflater = getSupportMenuInflater();
		 inflater.inflate(R.menu.boleta, menu);
		return true;
	}
	 
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		return true;
	}
	    
	@SuppressWarnings("deprecation")
	private void generateBoleta(){
		
		// JSON Node names
		String TAG_MATCH = "match";
		String TAG_EQUIPOL = "team1";
		String TAG_EQUIPOV = "team2";
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
		    android.view.Display display = ((android.view.WindowManager)getSystemService(this.WINDOW_SERVICE)).getDefaultDisplay();
		    int wdthEqui = (int)(display.getWidth()/2.5);
		    int wdthEmp = display.getWidth() - (wdthEqui*2);
		    // looping through All Contacts
		    for(int i = 0; i < matchs.length(); i++){
		        JSONObject c = matchs.getJSONObject(i);     
		        String date = c.getString(TAG_DATE);
		        Equipo local = dao.getEquipo(c.getInt(TAG_IDLOCAL));
		        Equipo visitante = dao.getEquipo(c.getInt(TAG_IDVISITOR));
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
		        btnLocal.setText(local.getName());
		        btnLocal.setTextSize(14);
		        
		        btnLocal.setBackgroundColor(getResources().getColor(R.color.blanco));
		        btnLocal.setPadding(15, 0, 0, 0);    
		        btnLocal.setWidth(wdthEqui);
		        String uri = "@drawable/"+local.getEscudo();
		        int imageResource = getResources().getIdentifier(uri, null, getPackageName());
		        btnLocal.setCompoundDrawablesWithIntrinsicBounds(imageResource,0,0,0);
		        // Storing each json item in variable
		        Button btnEmpate = new Button(this);
		        //empate.setId(i);
		        btnEmpate.setText("Empate");
		        btnEmpate.setBackgroundColor(getResources().getColor(R.color.azul_1));      
		        btnEmpate.setWidth(wdthEmp);
		        btnEmpate.setTextSize(14);
		        //String equipoLocal = c.getString(TAG_EQUIPOL);
		        Button btnVisit = new Button(this);
		        
		        //btnVisit.setId(i);
		        btnVisit.setText(visitante.getName());
		        btnVisit.setTextSize(14);
		        btnVisit.setBackgroundColor(getResources().getColor(R.color.blanco));      
		        btnVisit.setWidth(wdthEqui);
		        btnLocal.setHeight(80);
		        btnVisit.setPadding(0, 0, 15, 0); 
		        uri = "@drawable/"+visitante.getEscudo();
		        imageResource = getResources().getIdentifier(uri, null, getPackageName());
		        
		        btnVisit.setCompoundDrawablesWithIntrinsicBounds(0, 0, imageResource, 0);
		        
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
