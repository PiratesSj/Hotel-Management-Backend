package  pirates.Hotel.Managment.entity;

import lombok.Data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "dining")
@Data
public class Dining {
    @Id
    private String id;
    private Data reservationTime;

    private Restaurant restaurant;

    private User user;


}
