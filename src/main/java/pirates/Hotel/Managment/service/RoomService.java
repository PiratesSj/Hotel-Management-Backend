package pirates.Hotel.Managment.service;

import pirates.Hotel.Managment.entity.Hotel;
import pirates.Hotel.Managment.entity.Rooms;
import pirates.Hotel.Managment.repository.HotelReops.RoomRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoomService {
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private HotelService hotelService;





    public Rooms registerRoom(Rooms rooms, String id) {
        rooms.setAvailability(true);
        Hotel hotel=hotelService.findById(id);
        Rooms saved=roomRepository.save(rooms);
        hotel.getRooms().add(rooms);
        hotelService.saveHotel(hotel);
        return saved ;
    }
    public void deleteRoomObject(String id){
        Rooms room=findById(id);
        roomRepository.delete(room);
    }
    public Rooms findById(String id){
        return roomRepository.findByid(id);
    }

    public Rooms updateRoom(Rooms room, String id) {
        return null;

    }
}
