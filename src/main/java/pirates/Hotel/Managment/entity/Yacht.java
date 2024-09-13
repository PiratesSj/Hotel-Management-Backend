package pirates.Hotel.Managment.entity;

import lombok.Data;
import lombok.NonNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "yatch")
@Data
public class Yacht {
    @Id
    private String id;
    @NonNull
    private String yachtName;
    @NonNull
    private int yachtCapacity;
    private  String yachtOwnerId;
    private String yachtOwner;
    private boolean yachtStatus;
    private int yachtRating;

    private List<Review> reviews=new ArrayList<>();


}
