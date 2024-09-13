package pirates.Hotel.Managment.repository;

import  pirates.Hotel.Managment.entity.Yacht;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface YachtRepository extends MongoRepository<Yacht, String > {
    public Yacht findByid(String id);
    public List<Yacht> findByYachtOwnerId(String ownerId);

}
