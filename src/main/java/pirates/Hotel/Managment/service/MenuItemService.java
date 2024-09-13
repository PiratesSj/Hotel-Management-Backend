package pirates.Hotel.Managment.service;

import pirates.Hotel.Managment.entity.Hotel;
import pirates.Hotel.Managment.entity.MenuItem;
import pirates.Hotel.Managment.entity.Restaurant;
import pirates.Hotel.Managment.repository.HotelReops.ResturantRepos.MenuItemRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MenuItemService {
    @Autowired
    private MenuItemRepository menuItemRepository;
    @Autowired
    private RestaurantService restaurantService;


    public MenuItem createMenuItem(MenuItem menuItem, String id) {
        Restaurant restaurant=restaurantService.findById(id);
        menuItemRepository.save(menuItem);
        restaurant.getMenuItems().add(menuItem);
        restaurantService.saveRestaurant(restaurant);
        return menuItemRepository.save(menuItem);
    }

    public void deleteMenuItemObject(String id) {
        menuItemRepository.deleteById(id);
    }

    public MenuItem updateMenuItem(MenuItem menuItem, String id) {
        MenuItem menuItem1=findByid(id);
        if(menuItem1!=null){
            menuItem1.setItemName(menuItem1.getItemName()!=null&&!menuItem.getItemName().equals("")? menuItem.getItemName() : menuItem1.getItemName());
            menuItem1.setDescription(menuItem.getDescription()!=null&&!menuItem.getDescription().equals("")? menuItem.getDescription() : menuItem1.getDescription());
            menuItem1.setPrice(!(menuItem.getPrice()==0)? menuItem1.getPrice() : menuItem.getPrice());
        }
        return menuItemRepository.save(menuItem1);
    }
    public MenuItem findByid(String id){
        return menuItemRepository.findByid(id);
    }
}
