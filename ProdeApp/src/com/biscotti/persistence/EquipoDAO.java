package com.biscotti.persistence;
import com.biscotti.objects.Equipo;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class EquipoDAO {

	private SQLiteDatabase db;
	private ProdeOpenHelper myHelper;
	private String[] columnas = {"idEquipo", "nombre", "imagenId"};
	public EquipoDAO(Context context){
		myHelper = new ProdeOpenHelper(context);
		
	}
	private void open() {
		db = myHelper.getWritableDatabase();
	}
	 
	private void close() {
		myHelper.close();
	}

	public Equipo getEquipo(int idEquipo){
		this.open();
		Cursor cursor = db.query(false,myHelper.TABLA_EQUIPO, columnas, "idEquipo = ? ", new String[] { String.valueOf(idEquipo) }, null, null, null, null, null);

		if (cursor != null)
	        cursor.moveToFirst();
		Equipo equipo = new Equipo(Integer.parseInt(cursor.getString(0)),
	            cursor.getString(1), cursor.getString(2));
        cursor.close();
        return equipo;
	}
}
