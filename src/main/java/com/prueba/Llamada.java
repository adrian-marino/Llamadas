package com.prueba;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.prueba.dto.Empleado;

public class Llamada implements Runnable{
	
	private Empleado empleado;
	private Long duracion;
	
	private static List<Empleado> empleados = Empleado.getEmpleados();
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
	
	public synchronized Long asignarDuracion() {
		long maximo = 10;
        long minimo = 5;
        Double floor = Math.floor(Math.random()*(maximo-minimo+1)+minimo);
		return floor.longValue();
	}
}
