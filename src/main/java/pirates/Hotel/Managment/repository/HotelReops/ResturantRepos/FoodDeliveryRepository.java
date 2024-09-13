package pirates.Hotel.Managment.repository.HotelReops.ResturantRepos;

import  pirates.Hotel.Managment.entity.FoodDelivery;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface FoodDeliveryRepository extends MongoRepository<FoodDelivery, String> {
    List<FoodDelivery> findByUser(String userId);
    List<FoodDelivery> findByRestaurantName(String restaurantName);
}
