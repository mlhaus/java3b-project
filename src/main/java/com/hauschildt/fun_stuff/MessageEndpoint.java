package com.hauschildt.fun_stuff;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@ServerEndpoint(
        value = "/chat/messageEndpoint",
        encoders = {MessageEncoder.class},
        decoders = {MessageDecoder.class}
)
public class MessageEndpoint {
    private static final Set<Session> subscribers = Collections.synchronizedSet(new HashSet<Session>());

    @OnOpen
    public void onOpen(Session session) {
        subscribers.add(session);
    }

    @OnClose
    public void onClose(Session session) {
        subscribers.remove(session);
    }

    @OnMessage
    public void onMessage(Message message, Session session) throws EncodeException, IOException {
        // session represents the user who is sending a new message
        for(Session subscriber: subscribers) {
            // forward that message to all other subscribers
            if(!subscriber.equals(session)) {
                subscriber.getBasicRemote().sendObject(message);
            }
        }
    }

    @OnError
    public void onError(Throwable throwable) {
        System.out.println("ERROR: " + throwable.getMessage());
    }
}
