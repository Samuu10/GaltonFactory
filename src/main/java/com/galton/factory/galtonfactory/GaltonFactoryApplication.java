package com.galton.factory.galtonfactory;

import com.galton.factory.galtonfactory.Handler.MovimientoHandler;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GaltonFactoryApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(GaltonFactoryApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		int numberOfBalls = 100; // Set the number of balls here

		MovimientoHandler movimientoHandler = new MovimientoHandler();

		for (int i = 0; i < numberOfBalls; i++) {
			final int ballId = i;
			new Thread(() -> {
				try {
					movimientoHandler.simulateBallMovement(ballId);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}).start();
		}

		// Export movements to JSON after all threads have finished
		// This is a simple way to wait for all threads to finish
		System.out.println("Espere a que todas las bolas se carguen.");
		Thread.sleep(5000); // Adjust the sleep time as needed
		movimientoHandler.exportMovimientosToJson();
		movimientoHandler.exportCuadrantesToJson();
	}
}