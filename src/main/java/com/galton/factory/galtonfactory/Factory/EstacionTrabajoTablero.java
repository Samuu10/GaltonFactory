package com.galton.factory.galtonfactory.Factory;

import com.galton.factory.galtonfactory.Component.*;

public class EstacionTrabajoTablero implements Fabrica {

    @Override
    public Component crearComponente() {
        return new Tablero();
    }
}