package com.galton.factory.galtonfactory.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;

public class Movimiento {
    //registra los movimientos de las bolas
    public double posicionX;
    public double posicionY;
    public int nivel;
    private static List<Movimiento> movimientos = new ArrayList<>();

    public Movimiento(double posicionX, double posicionY, int nivel) {
        this.posicionX = posicionX;
        this.posicionY = posicionY;
        this.nivel = nivel;
        movimientos.add(this);
    }

    @Override
    public String toString() {
        //de esta manera se exportara a json
        return String.format("{\"posicionX\": %f, \"posicionY\": %f, \"nivel\": %d}", posicionX, posicionY, nivel);
    }
}
