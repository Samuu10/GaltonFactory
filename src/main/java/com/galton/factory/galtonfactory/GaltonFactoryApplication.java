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
		//clase main que llama a movimientoHandler, clase principal que incluye la logica
		int numberOfBalls = 100; //elige el numero de bolas

		MovimientoHandler movimientoHandler = new MovimientoHandler();

		//se simula la caida de las bolas seleccionadas
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

		//se exportan los registros de movimientos y cuadrantes llenos
		System.out.println("Espere a que todas las bolas se carguen.");
		Thread.sleep(5000); // se espera a que terminen todos los hilos
		movimientoHandler.exportMovimientosToJson();
		movimientoHandler.exportCuadrantesToJson();
	}
}