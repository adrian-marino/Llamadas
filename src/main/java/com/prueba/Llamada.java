package com.prueba;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.prueba.dto.Director;
import com.prueba.dto.Empleado;
import com.prueba.dto.Operador;
import com.prueba.dto.Supervisor;

public class Llamada implements Runnable{
	
	private Empleado empleado;
	private Long duracion;
	
	private static List<Empleado> empleados = getEmpleados();
	Logger log = LoggerFactory.getLogger(Llamada.class);
	
	@Override
	public void run() {
		try {
			Empleado empleadoAsignado = asignarEmpleado();
			setEmpleado(empleadoAsignado);
			System.out.println("INICIA LLAMADA ATENDIDA POR -> " + empleadoAsignado.getNombre());
			Long duracionLlamada = asignarDuracion();
			TimeUnit.SECONDS.sleep(duracionLlamada);
			finalizarLlamada(getEmpleado(), duracionLlamada);
		} catch (InterruptedException e) {
			log.error("ERROR", e);
			Thread.currentThread().interrupt();
		}
	}
	
	public Llamada() {
		super();
	}

	public Empleado getEmpleado() {
		return empleado;
		
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	public Long getDuracion() {
		return duracion;
	}

	public void setDuracion(Long duracion) {
		this.duracion = duracion;
	}
	
	public synchronized void finalizarLlamada(Empleado empl, Long duracionLlamada) {
		Empleado empleadoHabilitar = empleados.stream()
				.filter(a->a.getId().equals(empl.getId())).findFirst().orElse(null);
		if(null != empleadoHabilitar) {
			empleadoHabilitar.setDisponible(true);
		}
		System.out.println("FINALIZA LLAMADA ATENDIDA POR -> " + getEmpleado().getNombre() + " DURACION LLAMADA -> " + duracionLlamada);
	}

	public synchronized Empleado asignarEmpleado() {
		Empleado empl = empleados.stream()
				.filter(Empleado::isDisponible).findFirst().orElse(null);
		if(null != empl) {
			empl.setDisponible(false);
		}  else {
			asignarEmpleado();
		}
		return empl;
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

	public synchronized Long asignarDuracion() {
		long maximo = 10;
        long minimo = 5;
        Double floor = Math.floor(Math.random()*(maximo-minimo+1)+minimo);
		return floor.longValue();
	}
}
