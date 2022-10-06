package mjw;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "entry")
@NamedQueries({
        //Cloud Economics opportunity here...
        @NamedQuery(name="Entries.findAll",
                query = "select e from Entry e order by e.timeStamp asc, e.uuid asc")
})
public class Entry extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long uuid;

    LocalDate timeStamp;
    LocalDateTime createStamp;
    BigDecimal value;
    String description;
    BigDecimal balance;
    String category;

    public Entry(){}

    public Entry(LocalDate timeStamp, BigDecimal value, String description, String category) {
        this.timeStamp = timeStamp;
        this.value = value;
        this.description = description;
        this.category=category;
        this.createStamp=LocalDateTime.now();
    }

    public String getCategory() {
        return category;
    }

    public String getTimeStampStr() {
        return timeStamp.toString();
    }

    public BigDecimal getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public LocalDate getTimeStamp() {
        return timeStamp;
    }

    public LocalDateTime getCreateStamp() {
        return createStamp;
    }
    public Long getUuid() {
        return uuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entry entry = (Entry) o;
        return Objects.equals(uuid, entry.uuid) && Objects.equals(timeStamp, entry.timeStamp) && Objects.equals(createStamp, entry.createStamp) && Objects.equals(value, entry.value) && Objects.equals(description, entry.description) && Objects.equals(balance, entry.balance) && Objects.equals(category, entry.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, timeStamp, createStamp, value, description, balance, category);
    }
    /*public static final Entry of(Account account, LocalDateTime timeStamp, String value){
        return of(account, timeStamp, value, null, null);
    }*/

    /*public static final Entry of(Account account, LocalDateTime timeStamp, String value, String description, Category category){
        return new Entry(timeStamp,
                new BigDecimal(value),
                description,
                category,
                account
        );
    }*/

}