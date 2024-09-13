package pirates.Hotel.Managment.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document(collection = "reviews")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Review {
    @Id
    private String id;
    private String message;
    private int rating;
    private String userName;
    private Date time;
    private List<String> photos;
    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Review)) {
            return false;
        }
        Review review = (Review) obj;
        return this.id == review.getId() ;
    }
}
