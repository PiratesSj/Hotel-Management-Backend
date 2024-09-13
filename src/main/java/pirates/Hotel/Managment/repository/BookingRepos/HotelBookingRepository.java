package pirates.Hotel.Managment.repository.BookingRepos;

import  pirates.Hotel.Managment.entity.HotelBooking;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface HotelBookingRepository extends MongoRepository<HotelBooking, String> {

}
