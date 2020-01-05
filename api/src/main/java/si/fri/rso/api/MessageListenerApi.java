package si.fri.rso.api;

import com.kumuluz.ee.discovery.annotations.RegisterService;
import si.fri.rso.api.controls.ListenerApi;
import si.fri.rso.api.MessageWebsocket;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/v1")
@RegisterService(value = "rso1920-message-listener")
public class MessageListenerApi extends Application{
    /*@Override
    public Set<Class<?>> getClasses() {
        final Set<Class<?>> resources = new HashSet<Class<?>>();
        resources.add(ListenerApi.class);
        resources.add(MessageListenerApi.class);
        resources.add(MessageWebsocket.class);
        return resources;
    }*/
}

