package pirates.Hotel.Managment.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import pirates.Hotel.Managment.entity.Hotel;

import java.util.List;

@Configuration
public class MongoTemp {
    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Hotel> findHotelsByCity(String city) {
        Query query = new Query();
        query.addCriteria(Criteria.where("hotelLocation").elemMatch(Criteria.where("city").is(city)));
        return mongoTemplate.find(query, Hotel.class);
    }

}
