package mjw;

import java.sql.SQLException;

import javax.inject.*;
import javax.sql.*;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/_hc")
public class HealthCheckResource {
    private static final int DB_CONN_TIMEOUT_SEC = 10;

    @Inject
    DataSource ds;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String healthCheck() {
        try (var conn = ds.getConnection()){
            conn.isValid(DB_CONN_TIMEOUT_SEC);
        }catch(SQLException ex){
            throw new WebApplicationException("Could not connect to database", 500);
        }
        return "Application is healthy";
    }
}