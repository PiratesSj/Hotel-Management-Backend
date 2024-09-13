package pirates.Hotel.Managment.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Location")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Location {
    @Id
    private String id;
    @NonNull
    private String city;
    @NonNull
    private String state;
    @NonNull
    private String pin;
    @NonNull
    private String country;
}
