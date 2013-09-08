package com.biscotti.objects;

import java.util.Date;

public class Partido {
	private int IdPartido;
	private Date fecha;
	private int fechaTorneo;
	private Equipo equipoL;
	private Equipo equipoV;
	private int golesV;
	private int golesL;
	private int status;
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getIdPartido() {
		return IdPartido;
	}
	public void setIdPartido(int idPartido) {
		IdPartido = idPartido;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public int getFechaTorneo() {
		return fechaTorneo;
	}
	public void setFechaTorneo(int fechaTorneo) {
		this.fechaTorneo = fechaTorneo;
	}
	public Equipo getEquipoL() {
		return equipoL;
	}
	public void setEquipoL(Equipo equipoL) {
		this.equipoL = equipoL;
	}
	public Equipo getEquipoV() {
		return equipoV;
	}
	public void setEquipoV(Equipo equipoV) {
		this.equipoV = equipoV;
	}
	public int getGolesV() {
		return golesV;
	}
	public void setGolesV(int golesV) {
		this.golesV = golesV;
	}
	public int getGolesL() {
		return golesL;
	}
	public void setGolesL(int golesL) {
		this.golesL = golesL;
	}
	
	public Partido(int idPartido, Date fecha, int fechaTorneo, Equipo equipoL,
			Equipo equipoV, int golesV, int golesL, int status) {
		super();
		IdPartido = idPartido;
		this.fecha = fecha;
		this.fechaTorneo = fechaTorneo;
		this.equipoL = equipoL;
		this.equipoV = equipoV;
		this.golesL = golesL;
		this.golesV = golesV;
		this.status = status;
	}
	
}
