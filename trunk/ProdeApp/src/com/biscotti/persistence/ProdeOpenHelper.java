package com.biscotti.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class ProdeOpenHelper extends SQLiteOpenHelper {

	public ProdeOpenHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, "ProdeDB", factory, version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL("CREATE TABLE Equipos (" +
					"idClub INTEGER PRIMARY KEY, nombre VARCHAR(30), " +
					"imagenId VARCHAR(15), status BOOLEAN);");
		db.execSQL("INSERT INTO Equipos VALUES()");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
