package com.prueba;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.Assert;
import org.junit.Test;

public class DispatcherTest {
	
	private Dispatcher dispatcher = new Dispatcher();

	/***
	 * Prueba Unitaria para cumplir con el requerimiento
	 * 		-> Procesar 10 llamadas al mismo tiempo
	 * 
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	@Test
	public void dispatchCallDiezLlamadas() throws InterruptedException, ExecutionException {
		List<Callable<Boolean>> tasks = Collections.nCopies(10, calleable());
		ExecutorService executorService = Executors.newFixedThreadPool(10);
		List<Future<Boolean>> llamadaEjecutas = executorService.invokeAll(tasks);
		Thread.sleep(15000);
		Assert.assertEquals(10, llamadaEjecutas.size());
	}
	
	private Callable<Boolean> calleable(){
		return new Callable<Boolean>() {
			@Override
			public Boolean call() throws Exception {
				dispatcher.dispatchCall();
				return true;
			}
		};
	}
	
	/***
	 * Prueba creada porque se deben cubrir los flujos excepcionales de la aplicacion
	 * y este caso es una caso excepcional
	 * 
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	@Test
	public void dispatchCallMasDiezLlamadas() throws InterruptedException, ExecutionException {
		List<Callable<Boolean>> tasks = Collections.nCopies(11, calleable());
		ExecutorService executorService = Executors.newFixedThreadPool(11);
		List<Future<Boolean>> invokeAll = executorService.invokeAll(tasks);
		Thread.sleep(15000);
		
		Assert.assertEquals(11, invokeAll.size());
	}
	
	@Test
	public void dispatchCallMenosDiezLlamadas() throws InterruptedException, ExecutionException {
		List<Callable<Boolean>> tasks = Collections.nCopies(5, calleable());
		ExecutorService executorService = Executors.newFixedThreadPool(10);
		List<Future<Boolean>> invokeAll = executorService.invokeAll(tasks);
		Thread.sleep(15000);
		
		Assert.assertEquals(5, invokeAll.size());
	}
	
}
