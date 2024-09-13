package pirates.Hotel.Managment.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "partner")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Partner {
    @Id
    private String id;

    @NonNull
    private String companyName;
    @NonNull
    private String companyAddress;
    @NonNull
    private String contactNumber;
    private User user;

}
