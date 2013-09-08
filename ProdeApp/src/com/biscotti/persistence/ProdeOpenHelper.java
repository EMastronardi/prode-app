package com.biscotti.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class ProdeOpenHelper extends SQLiteOpenHelper {
	public static int version = 1;
	public static String TABLA_EQUIPO = "equipos";

	public ProdeOpenHelper(Context context) {
		super(context, "ProdeDB", null, version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL("CREATE TABLE equipos ("
				+ "idEquipo INTEGER PRIMARY KEY, nombre VARCHAR(30), "
				+ "imagenId VARCHAR(15), status BOOLEAN);");
		db.execSQL("CREATE TABLE partidos("
				+ "idPartido INTEGER PRIMARY KEY, fecha Datetime, fechaTorneo Datetime"
				+ "idEquipoL INTEGER, idEquipoV INTEGER, golesL INTEGER, "
				+ "golesV INTEGER, status BOOLEAN);");
		
		db.execSQL("INSERT INTO Equipos VALUES(52392,\"Newell's\",\"newell\",1),"
				+ "(52387,\"Estudiantes\",\"estudiantes\",1),"
				+ "(52384,\"Tigre\",\"tigre\",1),"
				+ "(52375,\"Quilmes\",\"quilmes\", 1),"
				+ "(52388,\"All Boys\",\"allboys\", 1),"
				+ "(52379,\"Racing\",\"racing\", 1),"
				+ "(52380,\"Gimnasia LP\",\"gimnasia\", 1),"
				+ "(52376,\"G. Cruz\",\"gcruz\", 1),"
				+ "(52378,\"Colón\",\"colon\", 1),"
				+ "(52374,\"R. Central\",\"central\", 1),"
				+ "(52372,\"Boca Jrs\",\"boca\", 1),"
				+ "(52385,\"Velez\",\"velez\", 1),"
				+ "(52382,\"S. Lorenzo\",\"sanlorenzo\", 1),"
				+ "(52381,\"River\",\"river\", 1),"
				+ "(52386,\"Arsenal S.\",\"arsenal\", 1),"
				+ "(52377,\"Argentinos \",\"argentinos\", 1),"
				+ "(52391,\"Belgrano\",\"belgrano\", 1),"
				+ "(52393,\"A. Rafaela\",\"rafaela\", 1),"
				+ "(52390,\"Lanús\",\"lanus\", 1),"
				+ "(52383,\"Olimpo\",\"olimpo\", 1)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
	}
}
