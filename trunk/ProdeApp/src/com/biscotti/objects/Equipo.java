package com.biscotti.objects;

public class Equipo {
	int idEquipo;
	String name;
	String escudo;
	public Equipo(int id, String name, String escudo){
		this.idEquipo = id;
		this.name = name;
		this.escudo = escudo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getIdEquipo() {
		return idEquipo;
	}
	public void setIdEquipo(int idEquipo) {
		this.idEquipo = idEquipo;
	}
	public String getEscudo() {
		return escudo;
	}
	public void setEscudo(String escudo) {
		this.escudo = escudo;
	}
	
	
	
}
