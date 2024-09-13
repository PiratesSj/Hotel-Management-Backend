package pirates.Hotel.Managment.entity;

import lombok.Data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
@Data
@Document(collection = "otp_tokens")
public class Otp {
    @Id
    private String id;
    private String otp;
    private String userId;
    private Date expiryDate;

}
