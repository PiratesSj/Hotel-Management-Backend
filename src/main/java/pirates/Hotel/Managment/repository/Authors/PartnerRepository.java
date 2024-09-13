package pirates.Hotel.Managment.repository.Authors;

import  pirates.Hotel.Managment.entity.Partner;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartnerRepository extends MongoRepository<Partner, String> {
    Partner findByUserId(String id);
    Partner findByid(String partnerId);
    Partner findBycompanyName(String companyName);
}
