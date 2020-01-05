package si.fri.rso.websocket;

import si.fri.rso.services.WebsocketSession;

import javax.inject.Inject;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.EOFException;

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

    @OnError
    public void onError(Throwable t) throws Throwable {
        // Most likely cause is a user closing their browser. Check to see if
        // the root cause is EOF and if it is ignore it.
        // Protect against infinite loops.
        int count = 0;
        Throwable root = t;
        while (root.getCause() != null && count < 20) {
            root = root.getCause();
            count ++;
        }
        if (root instanceof EOFException) {
            // Assume this is triggered by the user closing their browser and
            // ignore it.
        } else {
            throw t;
        }
    }
}
