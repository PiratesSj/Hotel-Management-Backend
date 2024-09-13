package pirates.Hotel.Managment.repository;

import pirates.Hotel.Managment.entity.PrivateJet;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.rmi.server.ObjID;
import java.util.List;
import java.util.Optional;
@Repository
public interface PrivateJetRepository extends MongoRepository<PrivateJet, String> {
    public PrivateJet findByid(String id);
public List<PrivateJet> findByprivateJetOwnerId(String ownerId);


}
