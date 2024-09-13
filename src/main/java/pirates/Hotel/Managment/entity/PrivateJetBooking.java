package pirates.Hotel.Managment.entity;

import lombok.Data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "privatejet_bookong")
@Data
public class PrivateJetBooking {
    @Id
    private String id;

    private User user;
    private PrivateJet privateJet;
    private Data bookingDate;
    private double totalAmount;

}
