package com.biscotti.prodeapp;

import java.util.concurrent.ExecutionException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.Menu;
//import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import com.biscotti.objects.Equipo;
import com.biscotti.persistence.EquipoDAO;
import com.biscotti.persistence.ProdeOpenHelper;


public class Boleta extends ActionBarActivity {
	private String[] opcionesMenu;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private ListView drawerList;
	private TableLayout boletaTable = null;
	private EquipoDAO dao = null;
	private ProdeOpenHelper db;
	private CharSequence tituloSeccion;  
	private CharSequence tituloApp;  

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	 	setContentView(R.layout.activity_boleta);
	 	opcionesMenu = new String[] {"Opción 1", "Opción 2", "Opción 3"};
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerList = (ListView) findViewById(R.id.left_drawer);
 
        drawerList.setAdapter(new ArrayAdapter<String>(
                getSupportActionBar().getThemedContext(),
            android.R.layout.simple_list_item_1, opcionesMenu));
	 	db = new ProdeOpenHelper(this);
	 	dao = new EquipoDAO(this);
	 	boletaTable =(TableLayout) findViewById(R.id.tblBoleta);
	 	drawerList.setOnItemClickListener(new OnItemClickListener() {
	        @Override
	        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
	 
	            Fragment fragment = null;
	 
	            switch (position) {
	                case 1:
	                    fragment = new Fragment1();
	                    break;
	                case 2:
	                   // fragment = new Fragment2();
	                    break;
	                case 3:
	                    //fragment = new Fragment3();
	                    break;
	            }
	 
	            FragmentManager fragmentManager =
	                getSupportFragmentManager();
	 
	            fragmentManager.beginTransaction()
	                .replace(R.id.content_frame, fragment)
	                .commit();
	 
	            drawerList.setItemChecked(position, true);
	 
	            tituloSeccion = opcionesMenu[position];
	            getSupportActionBar().setTitle(tituloSeccion);
	 
	            drawerLayout.closeDrawer(drawerList);
	        }
	    });
	 	
	 	tituloSeccion = getTitle();
		tituloApp = getTitle();

		drawerToggle = new ActionBarDrawerToggle(this, 
				drawerLayout,
				R.drawable.ic_navigation_drawer, 
				R.string.action_settings,
				R.string.action_settings) {

			public void onDrawerClosed(View view) {
				getSupportActionBar().setTitle(tituloSeccion);
				ActivityCompat.invalidateOptionsMenu(Boleta.this);
			}

			public void onDrawerOpened(View drawerView) {
				getSupportActionBar().setTitle(tituloApp);
				ActivityCompat.invalidateOptionsMenu(Boleta.this);
			}
		};

		drawerLayout.setDrawerListener(drawerToggle);

		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);
		
	 	generateBoleta();
	
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Log.e("JSON Parser", "click sobre el item");
		if (drawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
					
		switch(item.getItemId())
		{
			case R.id.action_settings:
				Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show();;
				break;
			case R.id.action_refresh:
				Toast.makeText(this, "Refresh", Toast.LENGTH_SHORT).show();
				break;
			default:
				return super.onOptionsItemSelected(item);
		}
    return true;

	
//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		 MenuInflater inflater = getSupportMenuInflater();
//		 inflater.inflate(R.menu.boleta, menu);
//		return true;
//	}
//	 
//	@Override
//	public boolean onOptionsItemSelected(MenuItem item) {
//		return true;
//	}
//	   
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.boleta, menu);
		return true;
	}
	
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {

		boolean menuAbierto = drawerLayout.isDrawerOpen(drawerList);

		if(menuAbierto)
			menu.findItem(R.id.action_fecha).setVisible(false);
		else
			menu.findItem(R.id.action_fecha).setVisible(true);

		return super.onPrepareOptionsMenu(menu);
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		drawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		drawerToggle.onConfigurationChanged(newConfig);
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
		//JSONObject json = jParser.getFromFile(this);
		//JSONObject json =  jParser.getJSONFromUrl("http://www.resultados-futbol.com/scripts/api/api.php?key=ed6def436156233b75d0637a89b674c5&format=json&req=matchs&league=26&round=5");
		String[] url = {"http://emastronardi.no-ip.org/json/fecha1.json"};
		AsyncTask<String, Void, JSONObject> aJson =  new JSONParser().execute(url);
		JSONObject json = null;
		try {
			json = aJson.get();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ExecutionException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		
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
