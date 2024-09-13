package pirates.Hotel.Managment.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    private String id;

    private String userName;
    private String firstName;
    private String lastName;
    private String name;
    private String phone;
    private String password;
    private String email;
    private boolean enabled = false;
    private List<String> roles = new ArrayList<>();
}
