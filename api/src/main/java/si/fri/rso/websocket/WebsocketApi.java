package si.fri.rso.websocket;

import si.fri.rso.services.WebsocketSession;

import javax.inject.Inject;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/msg/{key}")
public class WebsocketApi {

    @Inject
    WebsocketSession websocketSession;

    @OnOpen
    public void onOpen(Session session, @PathParam("key") String key) {
        System.out.println("key: " + key);
        websocketSession.addSession(key, session);
    }

    @OnMessage
    public String greetListener(Session session, String name) {
        System.out.print("Preparing greeting for customer '" + name + "' ...");
        return "Hello, " + name + "!";
    }
}
