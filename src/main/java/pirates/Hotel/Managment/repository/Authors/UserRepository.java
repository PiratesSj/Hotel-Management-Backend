package pirates.Hotel.Managment.repository.Authors;

import  pirates.Hotel.Managment.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    User findByuserName(String username);
    User findByid(String id);


}
