package si.fri.rso.lib;

import javax.websocket.Session;

public class SessionOBJ {

    private String ChannelID;
    private Session session;

    public SessionOBJ(String channelID, Session session) {
        this.ChannelID = channelID;
        this.session = session;
    }

    public void setChannelID(String channelID) {
        ChannelID = channelID;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public String getChannelID() {
        return ChannelID;
    }
}
