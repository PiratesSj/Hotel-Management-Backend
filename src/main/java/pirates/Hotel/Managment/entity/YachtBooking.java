package pirates.Hotel.Managment.entity;

import lombok.Data;
import lombok.NonNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "yacht_booking")
@Data
public class YachtBooking {
    @Id
    private String  id;

    private User user;

    private Yacht yacht;
    @NonNull
    private Data bookingDate;
    private double totalAmount;




}
