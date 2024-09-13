package pirates.Hotel.Managment.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "menuItems")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuItem {
    @Id
    private String id;
    @NonNull
    private String itemName;
    private String description;
    private double price;

}
