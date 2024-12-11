package com.example.ticket_simulator_system.websocket;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

import java.io.IOException;
import java.util.*;

@Component
public class AppWebSocketHandler extends AbstractWebSocketHandler {

    private final List<WebSocketSession> sessions = Collections.synchronizedList(new ArrayList<>());

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        super.afterConnectionEstablished(session);
        sessions.add(session);
        System.out.println("session connected");
        session.sendMessage(new TextMessage("Backend ready for realtime Communication."));
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        super.afterConnectionClosed(session, status);
        sessions.remove(session);
        System.out.println("Client disconnected: " + session.getId());
    }

    public void sendMessage(Object data) throws IOException {
        synchronized (sessions) {
            for (WebSocketSession session : sessions) {
                if (session.isOpen()) {
                    String jason = objectMapper.writeValueAsString(data);
                    session.sendMessage(new TextMessage(jason));
                }
            }
        }
    }

    public void sendCreatedVendor(String name) throws IOException {
        Map<String, Object> vendorData = new HashMap<>();
        vendorData.put("type","vendorName");
        vendorData.put("name", name);
        sendMessage(vendorData);
    }

    public void sendNotification(String message) throws IOException {
        Map<String, Object> notification = new HashMap<>();
        notification.put("type","notification");
        notification.put("message", message);
        sendMessage(notification);
    }

    public void sendCreateCustomer(String customerName) throws IOException {
        Map<String, Object> name = new HashMap<>();
        name.put("type","customerName");
        name.put("message", customerName);
        sendMessage(name);
    }
}
