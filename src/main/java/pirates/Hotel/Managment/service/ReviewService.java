package pirates.Hotel.Managment.service;

import pirates.Hotel.Managment.entity.Hotel;
import pirates.Hotel.Managment.entity.Restaurant;
import pirates.Hotel.Managment.entity.Review;
import pirates.Hotel.Managment.repository.ReviewRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private EntitySearchQuery entitySearchQuery;
    @Autowired
    private UserService userService;
    @Autowired
    private HotelService hotelService;
    @Autowired
    private RestaurantService restaurantService;


    public Review saveNewReview(Review review, String  entityid, String userId){
       Hotel hotel=entitySearchQuery.findEntityById(entityid,Hotel.class);
        Restaurant restaurant=entitySearchQuery.findEntityById(entityid,Restaurant.class);
        if (hotel!=null){
            review.setTime(new Date());
            review.setUserName(userService.findById(userId).getUserName());
            reviewRepository.save(review);
            hotelService.updateRating(hotel,review,review.getRating());
            hotel.getReviews().add(review);
            hotelService.saveHotel(hotel);

        } else if (restaurant!=null) {
            review.setTime(new Date());
            review.setUserName(userService.findById(userId).getUserName());
            reviewRepository.save(review);
            restaurant.getReviews().add(review);
            restaurantService.saveRestaurant(restaurant);
            restaurantService.updateRating(restaurant,review,review.getRating());

        }
        return reviewRepository.save(review);

    }
    public void deleteReview(String entityid,String reviewId){
        Hotel hotel=entitySearchQuery.findEntityById(entityid,Hotel.class);
        Restaurant restaurant=entitySearchQuery.findEntityById(entityid,Restaurant.class);
        Review review = FindByid(reviewId);
        if (hotel!=null) {
            hotel.getReviews().remove(review);
            hotel.setHotelRating((((hotel.getHotelRating()) * hotel.getReviews().size()) - review.getRating()) / (hotel.getReviews().size() - 1));
            hotelService.saveHotel(hotel);
        }
        else if (restaurant!=null){
            restaurant.getReviews().remove(review);
            restaurant.setResturantRating((((restaurant.getResturantRating()) * restaurant.getReviews().size()) - review.getRating()) / (restaurant.getReviews().size() - 1));
            restaurantService.saveRestaurant(restaurant);
        }
        reviewRepository.deleteById(reviewId);
    }
    public Review updateReview(Review review, String id, String entityId){
        Review review1=FindByid(id);
        Hotel hotel=entitySearchQuery.findEntityById(entityId,Hotel.class);
        Restaurant restaurant=entitySearchQuery.findEntityById(entityId,Restaurant.class);

        if (review1!=null){
            if(hotel!=null) {
                if (!(review.getRating() == 0) == true) {
                    hotelService.updateRating(hotel, review1, review.getRating());
                }
                review1.setMessage(review.getMessage() != null && !review.getMessage().isEmpty() ? review.getMessage() : review1.getMessage());
            }
            if (restaurant!=null){
                if (!(review.getRating() == 0) == true) {
                    hotelService.updateRating(hotel, review1, review.getRating());
                }
                review1.setMessage(review.getMessage() != null && !review.getMessage().isEmpty() ? review.getMessage() : review1.getMessage());

            }
        }
        return reviewRepository.save(review1);
    }

    public Review FindByid(String id){
        return reviewRepository.findByid(id);
    }


}
