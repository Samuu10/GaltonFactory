// src/main/java/com/galton/factory/galtonfactory/Service/BolaService.java
package com.galton.factory.galtonfactory.Service;

import com.galton.factory.galtonfactory.Component.Bola;
import com.galton.factory.galtonfactory.Component.Tablero;
import com.galton.factory.galtonfactory.Factory.EstacionTrabajoBola;
import com.galton.factory.galtonfactory.Factory.Fabrica;
import org.springframework.stereotype.Service;

@Service
public class BolaService {
    private final Fabrica fabricaBola = new EstacionTrabajoBola();

    public Bola crearBola(double posicionX, double posicionY, int tamano, Tablero tablero) {
        Bola bola = (Bola) fabricaBola.crearComponente();
        bola.setPosicionX(posicionX);
        bola.setPosicionY(posicionY);
        bola.setTamano(tamano);
        bola.setTablero(tablero);
        return bola;
    }
}