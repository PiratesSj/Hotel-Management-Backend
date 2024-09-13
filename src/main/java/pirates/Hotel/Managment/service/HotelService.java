package pirates.Hotel.Managment.service;

import pirates.Hotel.Managment.entity.Hotel;
import pirates.Hotel.Managment.entity.Partner;
import pirates.Hotel.Managment.entity.Review;
import pirates.Hotel.Managment.entity.User;
import pirates.Hotel.Managment.repository.HotelReops.HotelRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pirates.Hotel.Managment.utils.MongoTemp;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class HotelService {
    @Autowired
    private HotelRepository hotelRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private PartnerService partnerService;
    @Autowired
    private LocationService locationService;
    @Autowired
    private MongoTemp mongoTemp;

    public List<Hotel> getAllHotels() {

        return hotelRepository.findAll();
    }

    public Optional<Hotel> getHotelById(String hotelId) {
        return hotelRepository.findById(hotelId);
    }

    public Hotel saveHotel(Hotel hotel) {
        return hotelRepository.save(hotel);
    }
    public Hotel registerHotel(Hotel hotel, String userName) {
        User user=userService.findByUserName(userName);

        locationService.saveAll(hotel.getHotelLocation());
        String role= String.valueOf(user.getRoles());
        if(!Objects.equals(role, "ADMIN")){
            Partner partner =partnerService.findByUserId(user.getId());
            //hotel.setHotelOwnerId(partner.getId());
            hotel.setHotelOwnerName(partner.getCompanyName());
        }
        else {
            hotel.setHotelOwnerId(user.getId());
            hotel.setHotelOwnerName("ADMIN");
        }
        return saveHotel(hotel);
    }

    public Hotel updateHotel(Hotel hotel, String id) {
        Hotel hotel1=findById(id);
        if (hotel1!=null){
            hotel1.setHotelName(hotel.getHotelName()!=null&&!hotel.getHotelName().equals("")? hotel.getHotelName() : hotel1.getHotelName());
            hotel1.setHotelLocation(hotel.getHotelLocation()!=null&&!hotel.getHotelLocation().equals("")? hotel.getHotelLocation() : hotel1.getHotelLocation());
            hotel1.setHotelDescription(hotel.getHotelDescription()!=null&&!hotel.getHotelDescription().equals("")? hotel.getHotelDescription() : hotel1.getHotelDescription());
        }
        return saveHotel(hotel1);

    }
    public void deleteHotelObject(String id){
        Hotel hotel=findById(id);
        hotelRepository.delete(hotel);
    }

    public Hotel findById(String id) {
        return hotelRepository.findByid(id);
    }


    public void updateRating(Hotel hotel, Review review,int rating) {
        if (hotel.getReviews().isEmpty()){
            hotel.setHotelRating(review.getRating());
            hotelRepository.save(hotel);
        }
        else {
            if(hotel.getReviews().contains(review)){
                int totalReview=hotel.getReviews().size();
                int totalratingsum=(hotel.getHotelRating()*totalReview)-review.getRating();
                hotel.setHotelRating((totalratingsum+rating)/totalReview);
            }
            else {
                int noOfReviews = hotel.getReviews().size();
                int updateRating = (hotel.getHotelRating() + review.getRating()) / (noOfReviews);
                hotel.setHotelRating(updateRating);
                hotelRepository.save(hotel);
            }
        }

    }
    public List<Hotel> findByHotelPartner(String partnerId){
        return hotelRepository.findByHotelOwnerId(partnerId);
    }

    public List<Hotel> findByHotelLocation(String Location){

        return mongoTemp.findHotelsByCity(Location);
    }



}
