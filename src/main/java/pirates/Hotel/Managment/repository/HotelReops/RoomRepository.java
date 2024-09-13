package pirates.Hotel.Managment.repository.HotelReops;

import  pirates.Hotel.Managment.entity.Rooms;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends MongoRepository<Rooms, String> {

    Rooms findByid(String id);

}
