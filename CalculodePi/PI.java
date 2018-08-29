/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.*;
/**
 *
 * @author Lizeth
 */
public class PI {

     public static void main(String[] args) throws InterruptedException, IOException {
		int Numthreads = Runtime.getRuntime().availableProcessors();
		if(args.length > 1) {
			Numthreads = Integer.parseInt(args[1]);
		}
		int limite = 1000;
		if(args.length > 0) {
			limite = Integer.parseInt(args[0]);
		}
		System.out.println("Calculo de Pi\n" + Numthreads + " Hilos...");
		PiThread[] threads = new PiThread[Numthreads];
		
		Instant start = Instant.now();
		int ultimo = 0;
		for(int i = 0; i < threads.length; i++) {
			threads[i] = new PiThread(ultimo, ultimo + limite/threads.length-1, limite);
			threads[i].start();
			ultimo = ultimo + limite/threads.length;
		}
		int deadThreads = 0;
		while(deadThreads != threads.length) {
			deadThreads = 0;
			for(PiThread t : threads) {
				if(t.getResultado() != null)
					deadThreads++;
			}
		}
		BigDecimal pi = BigDecimal.ZERO;
		for(PiThread t : threads) {
			pi = pi.add(t.getResultado());
		}
                Instant end = Instant.now();
                Duration tiempo = Duration.between(start, end);
		System.out.println("Calculando los " + limite + " digitos of PI en " + tiempo.toMillis()/1000.0 + " s! \nValores iniciales: " + pi.toString().substring(0, 15));
		System.out.println("Los valores se han guardado en el archivo calculo_de_PI.txt");
		FileWriter fw = new FileWriter("calculo_de_PI.txt");
		fw.write(pi.toString().substring(0, limite+2));
		fw.close();
		
		
		System.out.println("¡Gracias!");
	}
     
}
