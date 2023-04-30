package com.hauschildt.fun_stuff;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@ServerEndpoint(
        value = "/tictactoe/endpoint",
        encoders = {MessageEncoder.class},
        decoders = {MessageDecoder.class}
)
public class TicTacToeEndpoint {
    private static final Map<Session,String> players = Collections.synchronizedMap(new HashMap<Session, String>());

    private void sendJson(String json, Session sendTo) throws DecodeException, EncodeException, IOException {
        MessageDecoder decoder = new MessageDecoder();
        if(decoder.willDecode(json)) {
            Message message = decoder.decode(json);
            sendTo.getBasicRemote().sendObject(message);
        } else {
            System.out.println("Invalid Json format");
        }
    }

    @OnOpen
    public void onOpen(Session session) throws EncodeException, IOException, DecodeException {
        String json = "";
        if(!players.containsValue("X")) {
            players.put(session, "X");
            if(!players.containsValue("O")) {
                // Player O hasn't joined yet
                json = "{" +
                        "  \"messageType\":\"playerJoined\"," +
                        "  \"yourPlayer\":\"X\"," +
                        "  \"totalPlayers\":\""+ players.size() +"\"," +
                        "  \"message\":\"Please wait for player O to join\"," +
                        "  \"itsYourTurn\":\"false\"" +
                        "}";
            } else {
                // Player O has joined
                json = "{" +
                        "  \"messageType\":\"playerJoined\"," +
                        "  \"yourPlayer\":\"X\"," +
                        "  \"totalPlayers\":\""+ players.size() +"\"," +
                        "  \"message\":\"it's your turn\"," +
                        "  \"itsYourTurn\":\"true\"" +
                        "}";
            }
        } else if(!players.containsValue("O")) {
            players.put(session, "O");
            json = "{" +
                    "  \"messageType\":\"playerJoined\"," +
                    "  \"yourPlayer\":\"O\"," +
                    "  \"totalPlayers\":\""+ players.size() +"\"," +
                    "  \"message\":\"Please wait for player X to play\"," +
                    "  \"itsYourTurn\":\"false\"" +
                    "}";
            // To do - tell player X they can play
        } else {
            players.put(session, "Guest");
            json = "{" +
                    "  \"messageType\":\"playerJoined\"," +
                    "  \"yourPlayer\":\"Guest\"," +
                    "  \"totalPlayers\":\""+ players.size() +"\"," +
                    "  \"message\":\"You are watching the current game\"," +
                    "  \"itsYourTurn\":\"false\"" +
                    "}";
        }
        System.out.println(json);
        sendJson(json, session);
        System.out.println("Number of players online: " + players.size());
    }
    @OnClose
    public void onClose(Session session) {
        players.remove(session);
        System.out.println("Number of players online: " + players.size());
    }
    @OnMessage
    public void onMessage(Message json, Session session) {

    }
    @OnError
    public void onError(Throwable throwable) {
        System.out.println("ERROR: " + throwable.getMessage() );
    }
}
