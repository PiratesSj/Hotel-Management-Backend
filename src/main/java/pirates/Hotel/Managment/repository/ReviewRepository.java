package pirates.Hotel.Managment.repository;

import  pirates.Hotel.Managment.entity.Review;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReviewRepository extends MongoRepository<Review, String > {
    Review findByid(String id);
}
