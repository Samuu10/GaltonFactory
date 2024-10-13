// src/main/java/com/galton/factory/galtonfactory/Component/Tablero.java
package com.galton.factory.galtonfactory.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Random;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tablero implements Component {
    private int niveles;
    private double altura;
    private double ancho;
    private List<Bola> bolas;
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
    public Tablero(int niveles, double altura, double ancho, Random random) {
        this.niveles = niveles;
        this.altura = altura;
        this.ancho = ancho;
        this.random = random;
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