package pirates.Hotel.Managment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pirates.Hotel.Managment.entity.Location;
import pirates.Hotel.Managment.repository.LocationRepository;

import java.util.List;

@Service
public class LocationService {

    @Autowired
    private LocationRepository locationRepository;
    public void save(Location location){
        locationRepository.save(location);
    }
    public void saveAll(List<Location> location){
        locationRepository.saveAll(location);
    }
    public List<Location> getAllLocation(){
        return locationRepository.findAll();
    }
    public List<Location> findBycity(String city) {
        return locationRepository.findBycity(city);
    }
}
