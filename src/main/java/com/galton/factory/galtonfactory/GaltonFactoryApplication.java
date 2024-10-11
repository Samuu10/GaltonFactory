package com.galton.factory.galtonfactory;

import com.galton.factory.galtonfactory.Component.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Random;

@SpringBootApplication
public class GaltonFactoryApplication {
	public static void main(String[] args) {
		Random rd = new Random();
		// Inicializa el tablero
		Tablero tablero = new Tablero(500, 500, 10, rd);

		// Crea y lanza hilos para las bolas
		for (int i = 0; i < 10; i++) {
			Bola bola = new Bola(250, 0, 10, tablero); // Comienza desde el centro
			Thread hiloBola = new Thread(bola);
			hiloBola.start(); // Inicia la caída de la bola
		}

		// Aquí puedes agregar lógica adicional para controlar la simulación
		// o la finalización de los hilos si es necesario.
	}
}
