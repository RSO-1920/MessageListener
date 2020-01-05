package si.fri.rso.services;

import si.fri.rso.lib.SessionOBJ;

import org.json.JSONObject;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;
import javax.websocket.Session;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@ApplicationScoped
public class WebsocketSession {
    private Map<String, Session> sessions = new ConcurrentHashMap<>();
    private List<SessionOBJ> sessionList = new ArrayList<SessionOBJ>();

    public void onServerMessage(String message) throws IOException {
        System.out.println("on server message: " + message);

        JSONObject jsonObject = new JSONObject(message);

        String channel = Integer.toString((int) jsonObject.getInt("channelId"));
        //String channel = "3";
        System.out.println("Channel " + channel);

        /*try {
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
        }*/

        try{
            int counter = 0;
            for (int i = 0; i < sessionList.size(); i++) {
                if (sessionList.get(i).getChannelID().equals(channel) && sessionList.get(i).getSession().isOpen()) {
                    System.out.println("Sending to " + channel + "! ,counter = " + counter);
                    sessionList.get(i).getSession().getBasicRemote().sendText(message);
                    counter++;
                }
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addSession (String key, Session session) {
        //this.sessions.put(key, session);
        /*for (Map.Entry<String, Session> entry : sessions.entrySet()) {
            System.out.println("Key = " + entry.getKey() + ", Value = ");
            System.out.println("[ADD] is open: " + entry.getValue().isOpen());
        }*/

        //SessionOBJ sobj = new SessionOBJ();
        //sobj.setChannelID(key);
        //sobj.setSession(session);

        System.out.println("adding session");
        sessionList.add(new SessionOBJ(key, session));
    }
}
