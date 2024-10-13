package com.galton.factory.galtonfactory.Config;

import com.galton.factory.galtonfactory.Component.Tablero;
import com.galton.factory.galtonfactory.Handler.MovimientoHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Bean
    public Tablero tablero() {
        return new Tablero(10, 100.0, 100.0, new java.util.Random());
    }

    @Bean
    public MovimientoHandler movimientoHandler() {
        return new MovimientoHandler();
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(movimientoHandler(), "/movimientos").setAllowedOrigins("*");
    }
}