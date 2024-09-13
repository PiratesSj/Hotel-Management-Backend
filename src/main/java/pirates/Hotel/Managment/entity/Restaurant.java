package pirates.Hotel.Managment.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "restaurants")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Restaurant {
    @Id
    private String id;
    @NonNull
    private String restaurantName;
    private String restaurantsDescription;
    private List<Location> restaurantsLocation;
    private String ownerId;

    private List<MenuItem> menuItems=new ArrayList<>();
    private int resturantRating;


    private List<Review> reviews=new ArrayList<>();

}
