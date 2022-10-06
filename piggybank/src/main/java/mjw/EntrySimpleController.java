package mjw;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.*;

import javax.ws.rs.core.MediaType;
import java.math.BigDecimal;
import java.time.LocalDate;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Path("/entry")
public class EntrySimpleController {
    @Inject
    EntityManager em;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @GET
    public List<Entry> list(){
        return em.createNamedQuery("Entries.findAll", Entry.class)
                .getResultList();
    }

    @Path("new")
    @GET
    @Transactional
    public Entry getNew(@QueryParam("categoryID") String category,
                        @QueryParam("description") String description,
                        @QueryParam("amount") BigDecimal amount,
                        @QueryParam("date") String date){
        var tx = new Entry(LocalDate.parse(date, formatter), amount, description, category);
        tx.persist();
        return tx;
    }
    @Path("findAll")
    @GET
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    public List<Entry> findAll(){
        return em.createNamedQuery("Entries.findAll", Entry.class)
                .getResultList();

    }

    @Path("find")
    @GET
    @Transactional
    public List<Entry> findByDescription(@QueryParam("description") String description){
        return Entry.find("description", description).list();
    }


}