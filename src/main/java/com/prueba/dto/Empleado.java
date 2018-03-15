package com.prueba.dto;

public class Empleado {
	
	private String nombre;
	private Long id;
	private boolean disponible;
	
	private Long prioridad;
	
	public Empleado() {
		super();
	}

	public Empleado(String nombre, Long id, boolean disponible, Long prioridad) {
		super();
		this.nombre = nombre;
		this.id = id;
		this.disponible = disponible;
		this.prioridad = prioridad;
	}

	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isDisponible() {
		return disponible;
	}

	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}

	public Long getPrioridad() {
		return prioridad;
	}

	public void setPrioridad(Long prioridad) {
		this.prioridad = prioridad;
	}
}
