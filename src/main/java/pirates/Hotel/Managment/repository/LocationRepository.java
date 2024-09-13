package pirates.Hotel.Managment.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pirates.Hotel.Managment.entity.Location;

import java.util.List;

@Repository
public interface LocationRepository extends MongoRepository<Location, String> {
    public List<Location> findBycity(String city);
}
