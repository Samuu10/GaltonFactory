package com.galton.factory.galtonfactory.Factory;

import com.galton.factory.galtonfactory.Component.Bola;
import com.galton.factory.galtonfactory.Component.*;

public class EstacionTrabajoBola implements Fabrica{

    @Override
    public Component crearComponente() {
        return new Bola();
    }
}