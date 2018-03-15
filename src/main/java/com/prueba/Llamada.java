package com.prueba;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

import com.prueba.dto.Director;
import com.prueba.dto.Empleado;
import com.prueba.dto.Operador;
import com.prueba.dto.Supervisor;

public class Llamada implements Runnable{
	
	private Empleado empleado;
	private Long duracion;
	
	private static List<Empleado> empleados = getEmpleados();
	
	ReentrantLock lock = new ReentrantLock();
	
	@Override
	public void run() {
		Empleado empleadoAsignado = asignarEmpleado();
		setEmpleado(empleadoAsignado);
		System.out.println("INICIA LLAMADA ATENDIDA POR -> " + getEmpleado().getNombre());
		try {
			Long duracionLlamada = asignarDuracion();
			TimeUnit.SECONDS.sleep(duracionLlamada);
			finalizarLlamada(getEmpleado(), duracionLlamada);
		} catch (InterruptedException e) {
			e.printStackTrace();
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
	
	public synchronized void finalizarLlamada(Empleado empl, Long duracionLlamada) throws InterruptedException {
		Empleado empleado = empleados.stream()
				.filter(a->a.getId().equals(empl.getId())).findFirst().orElseGet(null);
		empleado.setDisponible(true);
		System.out.println("FINALIZA LLAMADA ATENDIDA POR -> " + getEmpleado().getNombre() + " DURACION LLAMADA -> " + duracionLlamada);
	}

	public synchronized Empleado asignarEmpleado() {
		lock.lock();
		Empleado empl = empleados.stream()
				.filter(Empleado::isDisponible).findFirst().orElseGet(null);
		if(null != empl) {
			empl.setDisponible(false);
			lock.unlock();
		}  else {
			System.out.println("SIM EMPLEADOS");
			asignarEmpleado();
		}
		return empl;
	}
	
	public static synchronized List<Empleado> getEmpleados(){
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
