package mjw;

import io.quarkus.hibernate.orm.rest.data.panache.PanacheEntityResource;

public interface EntryResource extends PanacheEntityResource<Entry, Long> {
}
