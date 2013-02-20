package HelloWorld;

import com.google.common.base.Optional;
import com.yammer.metrics.annotation.Timed;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;


@Path("/hello")
@Produces({ MediaType.APPLICATION_JSON})
public class HelloWorld {

    @GET
    public String hallo() {
        return "Dag Matti";
    }
}
