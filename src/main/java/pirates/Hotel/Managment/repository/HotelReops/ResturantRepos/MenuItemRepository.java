package pirates.Hotel.Managment.repository.HotelReops.ResturantRepos;

import  pirates.Hotel.Managment.entity.MenuItem;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface MenuItemRepository extends MongoRepository<MenuItem, String> {
    public MenuItem findByid(String id);
}
