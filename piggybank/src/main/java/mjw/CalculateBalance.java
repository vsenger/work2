package mjw;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.sql.DataSource;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Path("/operations")
public class CalculateBalance {
    @Inject
    EntityManager em;
    private static final String insertSQL = "UPDATE entry set balance = ? where uuid = ?";

    @Path("calculateBalance")
    @GET
    @Transactional
    public BigDecimal calculateBalance() {
        final BigDecimal[] balance = {BigDecimal.ZERO};
        em.createNamedQuery("Entries.findAll", Entry.class)
                .getResultList()
                .forEach(entry -> {
                    balance[0] = balance[0].add(entry.getValue());
                    entry.setBalance(balance[0]);
                    em.createNativeQuery(insertSQL)
                            .setParameter(1, balance[0])
                            .setParameter(2, entry.getUuid())
                            .executeUpdate();
                });
        return balance[0];
    }


}