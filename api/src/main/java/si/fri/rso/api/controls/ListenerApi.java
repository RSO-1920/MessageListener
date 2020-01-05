package si.fri.rso.api.controls;

import si.fri.rso.services.MessageListenerBean;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Metered;
import org.eclipse.microprofile.metrics.annotation.Timed;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@ApplicationScoped
@Path("/msg")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ListenerApi {

    private static final Logger LOG = LogManager.getLogger(ListenerApi.class.getName());

    //@Inject
    //MessageListenerBean messageListenerBean;

    @GET
    @Timed(name = "incomingMsg_time_get")
    @Counted(name = "incomingMsg_counted_get")
    @Metered(name = "incomingMsg_metered_get")
    public Response getAllMessages(){
        LOG.info("Inside get method");

        return Response.status(200).entity("Alive").build();
    }

}
