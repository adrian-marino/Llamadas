package com.prueba.dto;

public class Director extends Empleado{

	public Director(String nombre, Long id, boolean disponible) {
		super(nombre, id ,disponible, 3L);
	}
}
