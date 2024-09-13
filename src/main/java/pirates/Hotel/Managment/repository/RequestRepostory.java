package pirates.Hotel.Managment.repository;

import org.springframework.stereotype.Repository;
import pirates.Hotel.Managment.entity.Request;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

@Repository
public interface RequestRepostory extends MongoRepository<Request, String> {
     Request findByid(String id);
     List<Request> findBytype(String type);
}
