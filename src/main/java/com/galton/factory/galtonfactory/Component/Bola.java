package com.galton.factory.galtonfactory.Component;

import com.galton.factory.galtonfactory.Handler.MovimientoHandler;
import com.galton.factory.galtonfactory.Handler.MovimientoHandler.Movimiento;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bola implements Component, Runnable {
    private int ballId;
    private double posicionX;
    private double posicionY;
    private int tamano;
    private Tablero tablero;
    private MovimientoHandler movimientoHandler;

    private void mover() {
        posicionY += 10;
        int nivel = (int) (posicionY / 10); //calcula en que nivel esta
        synchronized (tablero) {
            posicionX = tablero.moverBola(posicionX);
        }
        try {
            movimientoHandler.sendMovimiento(new Movimiento(ballId, posicionX, posicionY, nivel));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (posicionY < tablero.getAltura() - tamano) {
            mover();
            try {
                Thread.sleep(100);
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