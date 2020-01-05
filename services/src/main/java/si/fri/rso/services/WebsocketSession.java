package si.fri.rso.services;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;
import javax.websocket.Session;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ApplicationScoped
public class WebsocketSession {
    private Map<String, Session> sessions = new ConcurrentHashMap<>();

    public void onServerMessage(String message) throws IOException {
        System.out.println("on server message: " + message);

        try {
            for (Map.Entry<String, Session> entry : sessions.entrySet()) {
                System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
                System.out.println("is open: " + entry.getValue().isOpen());
                if (entry.getValue().isOpen()) {
                    entry.getValue().getBasicRemote().sendText(message);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addSession (String key, Session session) {
        this.sessions.put(key, session);
        System.out.println("adding session");
        for (Map.Entry<String, Session> entry : sessions.entrySet()) {
            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
            System.out.println("is open: " + entry.getValue().isOpen());
        }
    }
}
