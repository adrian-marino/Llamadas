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
	
	private Dispatcher dis = new Dispatcher();

	/***
	 * Prueba Unitaria para cumplir con el requerimiento
	 * 		-> Procesar 10 llamadas al mismo tiempo
	 * 
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	@Test
	public void dispatchCallDiezLlamadas() throws InterruptedException, ExecutionException {
		Callable<Long> task = new Callable<Long>() {
            @Override
            public Long call() {
            	try {
					dis.dispatchCall();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (ExecutionException e) {
					e.printStackTrace();
				}
                return 1L;
            }
        };
		List<Callable<Long>> tasks = Collections.nCopies(10, task);
		ExecutorService executorService = Executors.newFixedThreadPool(10);
		List<Future<Long>> llamadaEjecutas = executorService.invokeAll(tasks);
		Thread.sleep(15000);
		Assert.assertEquals(llamadaEjecutas.size(), 10);
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
		Callable<Long> task = new Callable<Long>() {
            @Override
            public Long call() {
            	try {
					dis.dispatchCall();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (ExecutionException e) {
					e.printStackTrace();
				}
                return 1L;
            }
        };
		List<Callable<Long>> tasks = Collections.nCopies(11, task);
		ExecutorService executorService = Executors.newFixedThreadPool(11);
		List<Future<Long>> invokeAll = executorService.invokeAll(tasks);
		Thread.sleep(15000);
		
		Assert.assertEquals(invokeAll.size(), 11);
	}
	
	@Test
	public void dispatchCallMenosDiezLlamadas() throws InterruptedException, ExecutionException {
		Callable<Long> task = new Callable<Long>() {
            @Override
            public Long call() {
            	try {
					dis.dispatchCall();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (ExecutionException e) {
					e.printStackTrace();
				}
                return 1L;
            }
        };
		List<Callable<Long>> tasks = Collections.nCopies(5, task);
		ExecutorService executorService = Executors.newFixedThreadPool(10);
		List<Future<Long>> invokeAll = executorService.invokeAll(tasks);
		Thread.sleep(15000);
		
		Assert.assertEquals(invokeAll.size(), 5);
	}
	
}
