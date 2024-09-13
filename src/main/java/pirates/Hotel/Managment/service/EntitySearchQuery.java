package pirates.Hotel.Managment.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

@Service
public class EntitySearchQuery {

    @Autowired
    private MongoTemplate mongoTemplate;

    public <T> T findEntityById(String id, Class<T> entityClass) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        return mongoTemplate.findOne(query, entityClass);
    }
}
