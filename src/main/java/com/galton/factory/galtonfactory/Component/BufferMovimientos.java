// src/main/java/com/galton/factory/galtonfactory/Component/BufferMovimientos.java
package com.galton.factory.galtonfactory.Component;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class BufferMovimientos {
    //almacena los movimientos en el buffer
    private final List<Movimiento> movimientos = Collections.synchronizedList(new LinkedList<>());

    public void addMovimiento(Movimiento movimiento) {
        movimientos.add(movimiento);
    }

    public List<Movimiento> getMovimientos() {
        return new LinkedList<>(movimientos);
    }
}