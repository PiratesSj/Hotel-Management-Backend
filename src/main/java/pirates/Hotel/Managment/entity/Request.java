package pirates.Hotel.Managment.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "request")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Request {
    @Id
    private String id;
    String type;


    private List<Partner> partner=new ArrayList<>();

    private List<Hotel> hotel=new ArrayList<>();

    private List<Yacht> yacht=new ArrayList<>();


    private List<PrivateJet> privateJet=new ArrayList<>();


    private List<Restaurant> restaurant=new ArrayList<>();

}
