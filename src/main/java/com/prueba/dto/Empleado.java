package com.prueba.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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
	
	public static List<Empleado> getEmpleados(){
		List<Empleado> lista = new ArrayList<>();
		
		lista.add(new Operador("OPERADOR 1", 1L, true));
		lista.add(new Operador("OPERADOR 2", 2L, true));
		lista.add(new Operador("OPERADOR 3", 3L, true));
		lista.add(new Operador("OPERADOR 4", 4L, true));
		lista.add(new Operador("OPERADOR 5", 5L, true));
		lista.add(new Operador("OPERADOR 6", 6L, true));
		lista.add(new Operador("OPERADOR 7", 7L, true));
		
		lista.add(new Supervisor("SUPERVISOR 1", 8L, true));
		lista.add(new Supervisor("SUPERVISOR 2", 9L, true));
		
		lista.add(new Director("DIRECTOR", 10L, true));
		
		Comparator<Empleado> c =
				(Empleado o1, Empleado o2)->o1.getPrioridad().compareTo(o2.getPrioridad());
		Collections.sort(lista, c);
		return lista;
	}
}
