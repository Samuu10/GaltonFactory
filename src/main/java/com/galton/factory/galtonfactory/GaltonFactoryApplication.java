// src/main/java/com/galton/factory/galtonfactory/GaltonFactoryApplication.java
package com.galton.factory.galtonfactory;

import com.galton.factory.galtonfactory.Component.*;
import com.galton.factory.galtonfactory.Handler.MovimientoHandler;
import com.galton.factory.galtonfactory.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.galton.factory.galtonfactory.utils.JsonFileWriter;

import java.util.ArrayList;
import java.util.List;


@SpringBootApplication
public class GaltonFactoryApplication implements CommandLineRunner {

	@Autowired
	private BolaService bolaService;

	@Autowired
	private TableroService tableroService;

	@Autowired
	private MovimientoHandler movimientoHandler;

	public static void main(String[] args) {
		SpringApplication.run(GaltonFactoryApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Tablero tablero = tableroService.crearTablero(500, 500, 10);

		// Crear una lista para almacenar los hilos de las bolas
		List<Thread> hilosBolas = new ArrayList<>();

		// Crear las bolas y sus hilos
		for (int i = 0; i < 10; i++) {
			Bola bola = bolaService.crearBola(250, 0, 10, tablero);
			bola.setMovimientoHandler(movimientoHandler);
			Thread hiloBola = new Thread(bola);
			hilosBolas.add(hiloBola);  // Añadir cada hilo a la lista
			hiloBola.start();  // Iniciar el hilo
		}

		// Esperar a que todos los hilos terminen usando join()
		for (Thread hilo : hilosBolas) {
			hilo.join();  // Esperar a que cada hilo termine
		}

		// Exportar datos a JSON para D3.js después de que todos los hilos terminen
		String jsonMovimientos = movimientoHandler.exportMovimientosToJson();
		JsonFileWriter.writeJsonToFile(jsonMovimientos, "src/main/resources/static/movimientos.json");

		System.out.println("Todos los hilos han terminado. Datos exportados a movimientos.json.");
	}
}
