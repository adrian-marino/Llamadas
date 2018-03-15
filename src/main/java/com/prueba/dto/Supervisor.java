package com.prueba.dto;

public class Supervisor extends Empleado{
	
	public Supervisor(String nombre, Long id, boolean disponible) {
		super(nombre, id ,disponible, 2L);
	}

}
