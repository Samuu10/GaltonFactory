// src/main/java/com/galton/factory/galtonfactory/Handler/MovimientoHandler.java
package com.galton.factory.galtonfactory.Handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.galton.factory.galtonfactory.Component.Movimiento;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.ArrayList;
import java.util.List;

public class MovimientoHandler extends TextWebSocketHandler {
    private final List<WebSocketSession> sessions = new ArrayList<>();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.add(session);
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // Handle incoming messages if needed
    }

    public void sendMovimiento(Movimiento movimiento) throws Exception {
        String json = objectMapper.writeValueAsString(movimiento);
        for (WebSocketSession session : sessions) {
            session.sendMessage(new TextMessage(json));
        }
    }
}