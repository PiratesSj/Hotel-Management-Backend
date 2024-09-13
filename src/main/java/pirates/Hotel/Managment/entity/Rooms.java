package pirates.Hotel.Managment.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "rooms")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rooms {
    @Id
    private String id;
    @NonNull
    private String roomType;
    @NonNull
    private String price;
    private  String capacity;
    private boolean availability;



}
