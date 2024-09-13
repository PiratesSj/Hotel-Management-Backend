package pirates.Hotel.Managment.entity;

import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "foodDelivery")
@Data
public class FoodDelivery {
    @Id
    private String id;
    @NonNull
    private String restaurantName;
    @NonNull
    private List<MenuItem> order=new ArrayList<>();
    @NonNull
    private String deliveryAddress;

    private User user;
}
