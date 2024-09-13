package pirates.Hotel.Managment.entity;

import lombok.Data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "hotel_bookings")
@Data
public class HotelBooking {
    @Id
    private String id;

    private String userName;

    private Rooms rooms;

    private Date checkInDate;
    private Date checkOutDate;
    private double totalAmount;
}
