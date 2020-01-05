package si.fri.rso.services;

import org.json.JSONObject;

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

        JSONObject jsonObject = new JSONObject(message);

        String channel = Integer.toString((int) jsonObject.getInt("channelId"));
        //String channel = "3";
        System.out.println("Cgannel " + channel);

        try {
            for (Map.Entry<String, Session> entry : sessions.entrySet()) {
                System.out.print("Key = " + entry.getKey() + ", Value = ");
                System.out.println("     [MSG] is open: " + entry.getValue().isOpen());
                if (entry.getValue().isOpen() && entry.getKey().equals(channel)) {
                    System.out.println("Sending to " + entry.getKey() + "!");
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
            System.out.println("Key = " + entry.getKey() + ", Value = ");
            System.out.println("[ADD] is open: " + entry.getValue().isOpen());
        }
    }
}
