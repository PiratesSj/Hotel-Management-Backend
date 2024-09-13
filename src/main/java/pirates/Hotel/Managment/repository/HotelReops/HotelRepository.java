package pirates.Hotel.Managment.repository.HotelReops;

import org.springframework.data.mongodb.repository.Query;
import  pirates.Hotel.Managment.entity.Hotel;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRepository extends MongoRepository<Hotel, String > {
    public Hotel findByid(String id);

    List<Hotel> findByHotelOwnerId(String partnerId);



}
