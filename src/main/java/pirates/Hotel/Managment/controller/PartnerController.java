package  pirates.Hotel.Managment.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import pirates.Hotel.Managment.entity.*;
import pirates.Hotel.Managment.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/partner")
public class PartnerController {
    //Registering as a partner.
    //Listing services (hotels, private jets, yachts) for review and approval by the admin.
    private UserService userService;

    @Autowired
    private PartnerService partnerService;
    @Autowired
    private HotelService hotelService;
    @Autowired
    private RoomService roomService;
    @Autowired
    private ReviewService reviewService;
    @Autowired
    private YachtService yachtService;
    @Autowired
    private PrivateJetService privateJetService;
    @Autowired
    private RestaurantService restaurantService;
    @Autowired
    private MenuItemService menuItemService;
    @Autowired
    private RequestService requestService;
@GetMapping("/getPartnerDetailByUserId/{userId}")
public  Partner getPartnerByUserId(@PathVariable String userId){
    return partnerService.findByUserId(userId);
}

    @PutMapping("/update-partner/{partnerId}")
    public Partner updatePartner(@PathVariable String partnerId, @RequestBody Partner update) {
        return partnerService.updatePartner(partnerId, update);
    }


    @PostMapping("/create-hotel/id/{partnerId}")
    public void createHotel(@RequestBody Hotel hotel, @PathVariable String partnerId) {
        hotel.setHotelOwnerId(partnerId);
        Request request = new Request();
        request.setType("Hotel");
        Partner partner = partnerService.findById(partnerId);
        hotel.setHotelOwnerName(partner.getCompanyName());
        request.getHotel().add(hotel);
        request.getPartner().add(partner);
        requestService.Save(request);


    }

    @PutMapping("/update-hotel/id/{hotelid}")
    public Hotel updateHotel(@RequestBody Hotel hotel, @PathVariable String hotelid) {
        return hotelService.updateHotel(hotel, hotelid);
    }

    @DeleteMapping("/delete-hotel/id/{hotelid}")
    public void deleteHotel(@PathVariable String hotelid) {
        hotelService.deleteHotelObject(hotelid);
    }

    @GetMapping("/get-all-hotel/{partnerId}")
    public List<Hotel> getAllHotel(@PathVariable String partnerId) {
        return hotelService.findByHotelPartner(partnerId);
    }


    @PostMapping("/create-room/{hotelid}")
    public Rooms createRoom(@RequestBody Rooms rooms, @PathVariable String hotelid) {
        return roomService.registerRoom(rooms, hotelid);
    }

    @DeleteMapping("/delete-room/id/{roomid}")
    public void deleteRoom(@PathVariable String roomid) {
        roomService.deleteRoomObject(roomid);
    }

    @PutMapping("/update-room/id/{roomid}")
    public Rooms updateRoom(@RequestBody Rooms room, @PathVariable String roomid) {
        return roomService.updateRoom(room, roomid);

    }


    @PostMapping("/post-review/{entityId}/{userId}")
    public Review postReview(@RequestBody Review review, @PathVariable String entityId, @PathVariable String userId) {
        return reviewService.saveNewReview(review, entityId, userId);
    }

    @PutMapping("update-review/{entityId}/{reviewId}")
    public Review updateReview(@RequestBody Review review, @PathVariable String reviewId, @PathVariable String entityId) {
        return reviewService.updateReview(review, reviewId, entityId);
    }

    @DeleteMapping("/delete-review/{entityId}/{reviewId}")
    public void deleteReview(@PathVariable String entityId, @PathVariable String reviewId) {
        reviewService.deleteReview(entityId, reviewId);
    }


    @PostMapping("/create-restaurant/{hotelId}/{partnerId}")
    public void createRestaurant(@RequestBody Restaurant restaurant, @PathVariable String hotelId, @PathVariable String partnerId) {
        restaurant.setOwnerId(partnerId);
        Request request = new Request();
        request.getRestaurant().add(restaurant);
        request.setType("Restaurant");
        Partner partner = partnerService.findById(partnerId);
        request.getPartner().add(partner);
        requestService.Save(request);


    }

    @PutMapping("/update-restaurant/id/{restaurantid}")
    public Restaurant updateRestaurant(@RequestBody Restaurant restaurant, @PathVariable String restaurantid) {
        return restaurantService.updateRestaurant(restaurant, restaurantid);
    }

    @DeleteMapping("/delete-restaurant/id/{restaurantid}")
    public void deleteRestaurant(@PathVariable String restaurantid) {
        restaurantService.deleteRestaurantObject(restaurantid);
    }
    @GetMapping("/get-all-Restaurant/{partnerId}")
    public List<Restaurant> getAllRestaurant(@PathVariable String partnerId) {
        return restaurantService.getAllRestaurantBYPartnerId(partnerId);
    }


    @PostMapping("/create-menu-item/{restaurantId}")
    public MenuItem createMenuItem(@RequestBody MenuItem menuItem, @PathVariable String restaurantId) {
        return menuItemService.createMenuItem(menuItem, restaurantId);
    }

    @DeleteMapping("/delete-menu-item/id/{menuitemid}")
    public void deleteMenuItem(@PathVariable String menuitemid) {
        menuItemService.deleteMenuItemObject(menuitemid);
    }

    @PutMapping("/update-menu-item/id/{menuitemid}")
    public MenuItem updateMenuItem(@RequestBody MenuItem menuItem, @PathVariable String menuitemid) {
        return menuItemService.updateMenuItem(menuItem, menuitemid);

    }


    @PostMapping("/create-yacht/id/{partnerId}")
    public void createYacht(@RequestBody Yacht yacht, @PathVariable String partnerId) {
        yacht.setYachtOwnerId(partnerId);
        Request request = new Request();
        request.getYacht().add(yacht);
        request.setType("Yacht");
        Partner partner = partnerService.findById(partnerId);
        request.getPartner().add(partner);
        requestService.Save(request);

    }

    @PutMapping("/update-yacht/id/{yachtid}")
    public Yacht updateYacht(@RequestBody Yacht yacht, @PathVariable String yachtid) {
        return yachtService.updateYacht(yacht, yachtid);
    }

    @DeleteMapping("/delete-yacht/id/{yachtid}")
    public void deleteYacht(@PathVariable String yachtid) {
        yachtService.deleteYachtObject(yachtid);
    }

    @GetMapping("/get-all-yacht/{partnerId}")
    public List<Yacht> getAllYacht(String partnerId) {
        return yachtService.findByYachtPartner(partnerId);
    }


    @GetMapping("/get-all-private-jet")
    public List<PrivateJet> getAllPrivateJets() {
        return privateJetService.findAllPrivateJets();
    }

    @PostMapping("/create-private-jet/id/{partnerId}")
    public void createPrivateJet(@RequestBody PrivateJet privateJet, @PathVariable String partnerId) {
        // privateJet.setPrivateJetOwnerId(partnerId);
        Request request = new Request();
        request.setType("PrivateJet");
        request.getPrivateJet().add(privateJet);
        Partner partner = partnerService.findById(partnerId);
        request.getPartner().add(partner);
        requestService.Save(request);

    }

    @PutMapping("/update-private-jet/id/{privatejetid}")
    public PrivateJet updatePrivateJet(@RequestBody PrivateJet privateJet, @PathVariable String privatejetid) {
        return privateJetService.updatePrivateJet(privateJet, privatejetid);
    }

    @DeleteMapping("/delete-private-jet/id/{privatejetid}")
    public void deletePrivateJet(@PathVariable String privatejetid) {
        privateJetService.deletePrivateJetObject(privatejetid);
    }

    @GetMapping("/get-all-privatejet/{partnerId}")
    public List<PrivateJet> getAllPrivatejet(String partnerId) {
        return privateJetService.findByPrivatejetPartner(partnerId);
    }
}

