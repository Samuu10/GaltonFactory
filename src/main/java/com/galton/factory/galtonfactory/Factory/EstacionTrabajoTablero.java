package com.galton.factory.galtonfactory.Factory;

import com.galton.factory.galtonfactory.Component.*;

public class EstacionTrabajoTablero implements Fabrica {
    //parte fabrica del patron
    @Override
    public Component crearComponente() {
        return new Tablero();
    }
}