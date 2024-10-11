// src/main/java/com/galton/factory/galtonfactory/Controller/TableroController.java
package com.galton.factory.galtonfactory.Controller;

import com.galton.factory.galtonfactory.Component.Tablero;
import com.galton.factory.galtonfactory.Service.TableroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tableros")
public class TableroController {
    @Autowired
    private TableroService tableroService;

    @PostMapping
    public Tablero crearTablero(@RequestParam int altura, @RequestParam int ancho, @RequestParam int niveles) {
        return tableroService.crearTablero(altura, ancho, niveles);
    }
}