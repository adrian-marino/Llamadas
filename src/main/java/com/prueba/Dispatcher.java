package com.prueba;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Dispatcher {

	ExecutorService executor = Executors.newFixedThreadPool(10);

	public void dispatchCall() throws InterruptedException, ExecutionException {
		executor.submit(new Llamada());
	}
}

