// src/main/java/com/galton/factory/galtonfactory/Component/Bola.java
package com.galton.factory.galtonfactory.Component;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bola implements Component, Runnable {
    private double posicionX; // Posición X inicial de la bola (siempre desde el centro)
    private double posicionY; // Posición Y (la altura desde donde cae)
    private int tamano;       // Tamaño de la bola
    private Tablero tablero;

    // Método para simular la caída de la bola desde el centro siguiendo la distribución normal
    public void caer(double desplazamientoX, double desplazamientoY) {
        this.posicionX += desplazamientoX;
        this.posicionY += desplazamientoY;
    }

    // Método para mover la bola
    private void mover() {
        posicionY += 10; // Ajusta la velocidad de caída
        synchronized (tablero) {
            posicionX = tablero.moverBola(posicionX);
        }
    }

    @Override
    public void run() {
        while (posicionY < tablero.getAltura() - tamano) {
            mover();
            try {
                Thread.sleep(100); // Espera para simular la caída
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String crear() {
        return "Bola creada";
    }
}