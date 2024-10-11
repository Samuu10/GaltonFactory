// src/main/java/com/galton/factory/galtonfactory/Component/Tablero.java
package com.galton.factory.galtonfactory.Component;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import java.util.Random;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tablero implements Component {
    private int altura;           // Altura total del tablero
    private int ancho;            // Ancho del tablero
    private int niveles;          // Número de niveles en la pirámide
    private Random random = new Random(); // Generador de números aleatorios

    public double moverBola(double posicionX) {
        double desplazamiento = random.nextDouble() < 0.5 ? -1 : 1; // Movimiento aleatorio
        double nuevaPosicionX = posicionX + desplazamiento;

        if (nuevaPosicionX < 0) {
            nuevaPosicionX = 0; // Límite izquierdo
        } else if (nuevaPosicionX > ancho) {
            nuevaPosicionX = ancho; // Límite derecho
        }
        return nuevaPosicionX;
    }

    @Override
    public void run() {
        // Implementación vacía
    }

    @Override
    public String crear() {
        return "Tablero creado";
    }
}