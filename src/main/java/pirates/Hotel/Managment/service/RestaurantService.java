package pirates.Hotel.Managment.service;

import pirates.Hotel.Managment.entity.*;
import pirates.Hotel.Managment.repository.HotelReops.ResturantRepos.RestaurantRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantService {
    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private HotelService hotelService;
    public Restaurant registerRestaurantWithHotel(Restaurant restaurant, String hotelId, String partnerId) {
        restaurant.setOwnerId(partnerId);
        Hotel hotel=hotelService.findById(hotelId);
        hotel.getRestaurants().add(restaurant);
        restaurant.setRestaurantsLocation(hotel.getHotelLocation());
        restaurantRepository.save(restaurant);
        hotelService.saveHotel(hotel);
        return restaurantRepository.save(restaurant);
    }
    public Restaurant registerRestaurant(Restaurant restaurant, String ownerId) {
        restaurant.setOwnerId(ownerId);
        return restaurantRepository.save(restaurant);
    }

    public List<Restaurant> getAllRestaurant() {
        return restaurantRepository.findAll();
    }
    public List<Restaurant> getAllRestaurantBYPartnerId(String ownerId) {
        return restaurantRepository.findByOwnerId(ownerId);
    }

    public Restaurant updateRestaurant(Restaurant restaurant, String id) {
        Restaurant restaurant1=findById(id);
        if (restaurant1!=null){
            restaurant1.setRestaurantName(restaurant.getRestaurantName()!=null&&!restaurant.getRestaurantName().equals("")? restaurant.getRestaurantName() : restaurant1.getRestaurantName());

             }
        return saveRestaurant(restaurant1);
    }

    public Restaurant findById(String id) {
        return restaurantRepository.findByid(id);
    }

    public void deleteRestaurantObject(String id) {
        restaurantRepository.deleteById(id);
    }
    public void updateRating(Restaurant restaurant, Review review, int rating) {
        if (restaurant.getReviews().isEmpty()){
            restaurant.setResturantRating(review.getRating());
            restaurantRepository.save(restaurant);
        }
        else {
            if(restaurant.getReviews().contains(review)){
                int totalReview=restaurant.getReviews().size();
                int totalratingsum=(restaurant.getResturantRating()*totalReview)-review.getRating();
                restaurant.setResturantRating((totalratingsum+rating)/totalReview);
                restaurantRepository.save(restaurant);
            }
            else {
                int noOfReviews = restaurant.getReviews().size();
                int updateRating = (restaurant.getResturantRating() + review.getRating()) / (noOfReviews);
                restaurant.setResturantRating(updateRating);
                restaurantRepository.save(restaurant);
            }
        }

    }

    public Restaurant saveRestaurant(Restaurant restaurant) {
       return restaurantRepository.save(restaurant);
    }
}
