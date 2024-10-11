// src/main/java/com/galton/factory/galtonfactory/GaltonFactoryApplication.java
package com.galton.factory.galtonfactory;

import com.galton.factory.galtonfactory.Component.*;
import com.galton.factory.galtonfactory.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GaltonFactoryApplication implements CommandLineRunner {
	@Autowired
	private BolaService bolaService;

	@Autowired
	private TableroService tableroService;

	public static void main(String[] args) {
		SpringApplication.run(GaltonFactoryApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Tablero tablero = tableroService.crearTablero(500, 500, 10);

		for (int i = 0; i < 10; i++) {
			Bola bola = bolaService.crearBola(250, 0, 10, tablero);
			Thread hiloBola = new Thread(bola);
			hiloBola.start();
		}

		// Exportar datos a JSON para D3.js
		System.out.println(tablero.toJson());
	}
}