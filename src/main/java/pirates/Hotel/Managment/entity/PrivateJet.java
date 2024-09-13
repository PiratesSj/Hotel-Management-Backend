package pirates.Hotel.Managment.entity;

import lombok.Data;
import lombok.NonNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "privateJets")
@Data
public class PrivateJet {
    @Id
    private String id;
    @NonNull
    private String privateJetModel;
    @NonNull
    private int privateJetCapacity;
    private  String privateJetOwnerId;
    private String privateJetOwner;
    private boolean privateJetStatus;
    private int privateJetRating;

    private List<Review> reviews=new ArrayList<>();
}
