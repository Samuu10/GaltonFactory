// src/main/java/com/galton/factory/galtonfactory/Controller/BolaController.java
package com.galton.factory.galtonfactory.Controller;

import com.galton.factory.galtonfactory.Component.Bola;
import com.galton.factory.galtonfactory.Component.Tablero;
import com.galton.factory.galtonfactory.Service.BolaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bolas")
public class BolaController {
    //controlador de la Bola siguiendo el patron de diseño abstract factory
    @Autowired
    private BolaService bolaService;

    @PostMapping
    public Bola crearBola(@RequestParam double posicionX, @RequestParam double posicionY, @RequestParam int tamano, @RequestBody Tablero tablero) {
        return bolaService.crearBola(posicionX, posicionY, tamano, tablero);
    }
}