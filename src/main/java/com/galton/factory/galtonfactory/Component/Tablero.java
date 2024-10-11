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
    private int altura;
    private int ancho;
    private int niveles;
    private Random random = new Random();

    public double moverBola(double posicionX) {
        double desplazamiento = random.nextDouble() < 0.5 ? -1 : 1;
        double nuevaPosicionX = posicionX + desplazamiento;

        if (nuevaPosicionX < 0) {
            nuevaPosicionX = 0;
        } else if (nuevaPosicionX > ancho) {
            nuevaPosicionX = ancho;
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

    public String toJson() {
        return String.format("{\"altura\": %d, \"ancho\": %d, \"niveles\": %d}", altura, ancho, niveles);
    }
}