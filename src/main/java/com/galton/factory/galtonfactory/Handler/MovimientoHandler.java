package com.galton.factory.galtonfactory.Handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.io.File;
import java.io.IOException;
//controla y simula los movimientos de las bolas seleccionadas
public class MovimientoHandler extends TextWebSocketHandler implements WebSocketHandler {
    private List<WebSocketSession> sessions = new ArrayList<>();
    private List<Movimiento> movimientos = new ArrayList<>(); //lista que registra los movimientos de las bolas
    private int[] cuadrantes = new int[10]; //array para contar bolas en cada cuadrante

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.add(session);
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        //si hace falta se emitira un mensaje
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessions.remove(session);
    }
//registra en que posicion acaba cada bola y la almacena en uno de los 10 contenedores para poder realizar el histograma
    public void sendMovimiento(Movimiento movimiento) throws Exception {
        movimientos.add(movimiento);
        if (movimiento.nivel == 9) { // Último nivel
            // Asignación de cuadrante ajustada para capturar las posiciones intermedias
            int cuadrante = Math.min((int) Math.round(movimiento.posicionX * 10), 9); // Redondeo para capturar posiciones cercanas
            cuadrantes[cuadrante]++;
        }
        TextMessage message = new TextMessage(movimiento.toString());
        for (WebSocketSession session : sessions) {
            session.sendMessage(message);
        }
    }

//Se transforman los movimientos a un tipo json para que puedan ser leidos por el JS
    public String exportMovimientosToJson() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(movimientos);
        mapper.writeValue(new File("src/main/resources/static/movimientos.json"), movimientos);
        return json;
    }
//Lo mismo que la anterior pero con los cuadrantes
    public String exportCuadrantesToJson() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(cuadrantes);
        mapper.writeValue(new File("src/main/resources/static/cuadrantes.json"), cuadrantes);
        return json;
    }

    //clase que simula el recorrido de las bolas
    public void simulateBallMovement(int ballId) {
        new Thread(() -> {
            double posicionX = 0.5; // Inicia en el centro
            Random random = new Random();

            for (int nivel = 0; nivel < 10; nivel++) {
                // Mueve a la derecha o izquierda
                boolean moveRight = random.nextBoolean();
                if (moveRight) {
                    // Mueve a la derecha
                    posicionX += 0.1;
                } else {
                    // Mueve a la izquierda
                    posicionX -= 0.1;
                }

                //límites establecidos en [0, 1]
                posicionX = Math.max(0.0, Math.min(1, posicionX));

                //se crea un movimiento, se crean 10 por cada bola, 1 por cada nivel
                Movimiento movimiento = new Movimiento(ballId, posicionX, nivel * 10.0, nivel);
                try {
                    sendMovimiento(movimiento);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    public static class Movimiento {
        public int ballId;
        public double posicionX;
        public double posicionY;
        public int nivel;

        public Movimiento(int ballId, double posicionX, double posicionY, int nivel) {
            this.ballId = ballId;
            this.posicionX = posicionX;
            this.posicionY = posicionY;
            this.nivel = nivel;
        }

        @Override
        public String toString() {
            return String.format("{\"ballId\": %d, \"posicionX\": %f, \"posicionY\": %f, \"nivel\": %d}", ballId, posicionX, posicionY, nivel);
        }
    }
}