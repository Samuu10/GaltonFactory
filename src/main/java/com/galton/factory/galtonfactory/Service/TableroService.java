// src/main/java/com/galton/factory/galtonfactory/Service/TableroService.java
package com.galton.factory.galtonfactory.Service;

import com.galton.factory.galtonfactory.Component.Tablero;
import com.galton.factory.galtonfactory.Factory.EstacionTrabajoTablero;
import com.galton.factory.galtonfactory.Factory.Fabrica;
import org.springframework.stereotype.Service;

@Service
public class TableroService {
    private final Fabrica fabricaTablero = new EstacionTrabajoTablero();

    public Tablero crearTablero(int altura, int ancho, int niveles) {
        Tablero tablero = (Tablero) fabricaTablero.crearComponente();
        tablero.setAltura(altura);
        tablero.setAncho(ancho);
        tablero.setNiveles(niveles);
        return tablero;
    }
}