package pirates.Hotel.Managment.repository.HotelReops.ResturantRepos;

import  pirates.Hotel.Managment.entity.Restaurant;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RestaurantRepository extends MongoRepository<Restaurant, String> {
    public Restaurant findByid(String id);
    public List<Restaurant> findByOwnerId(String ownerId);

}
