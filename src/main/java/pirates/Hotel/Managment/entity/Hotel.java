package pirates.Hotel.Managment.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import pirates.Hotel.Managment.repository.LocationRepository;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "hotels")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Hotel {
    @Id
    private String id;
    @NonNull
    private String hotelName;
    @NonNull
    private List<Location> hotelLocation;
    private String hotelDescription;
    @NonNull
    private  String hotelOwnerId;
    private String hotelOwnerName;


    private List<Rooms> rooms=new ArrayList<>();

    private List<Restaurant> restaurants=new ArrayList<>();

    private List<Review>reviews=new ArrayList<>();
    private int hotelRating;

}
