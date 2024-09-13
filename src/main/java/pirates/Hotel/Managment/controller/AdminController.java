package  pirates.Hotel.Managment.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import pirates.Hotel.Managment.entity.*;
import  pirates.Hotel.Managment.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    //Managing (CRUD operations) hotels, rooms, restaurants, menu items, food delivery options, private jets, yachts, and partners.
    //Reviewing and approving partner requests.
    //location,Payment,cupon,bookinkg pdf.,.cookies/otp auth
    @Autowired
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


    //USER Services//
    @PostMapping("/create-user")
    public User createUser(@RequestBody User user){
        return userService.addUser(user);

    }
    @GetMapping("/get-all-users")
    public List<User> getAllUser(){
        return userService.findAllUser();
    }
    @DeleteMapping("/delete-user/{username}")
    public void deleteUser(@PathVariable String username){
         userService.deleteUser(username);
    }
    @PutMapping("/update-user/{username}")
    public User updateUser(@PathVariable String username ,@RequestBody User user){
        user.setUserName(username);
        return userService.updateUser(username,user);
    }




    //Partner Services//
    @PostMapping("/createpartner")
    public Partner createPartner(@RequestBody Partner partner){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        User user=userService.findByUserName(authentication.getName());
        return partnerService.addPartner(partner);

    }
    @GetMapping("/get-all-partner")
    public List<Partner> getAllPartner(){
        return partnerService.findAllPartner();
    }
    @DeleteMapping("/delete-partner/{partnerId}")
    public void deletePartner(@PathVariable String partnerId){

        partnerService.deletePartner(partnerId);
    }
    @PutMapping("/update-partner/{partnerId}")
    public Partner updatePartner(@PathVariable String partnerId,@RequestBody Partner update){
        return partnerService.updatePartner(partnerId,update);
    }





    //Hotel Services//
    @PostMapping("/create-hotel/id/{userName}")
    public Hotel createHotel(@RequestBody Hotel hotel,@PathVariable String userName){
        return hotelService.registerHotel(hotel,userName);
    }
    @GetMapping("/get-all-hotel")
    public List<Hotel> getAllHotel(){
        return hotelService.getAllHotels();
    }
    @PutMapping("/update-hotel/id/{hotelid}")
    public Hotel updateHotel(@RequestBody Hotel hotel,@PathVariable String hotelid){
        return hotelService.updateHotel(hotel,hotelid);
    }
    @DeleteMapping("/delete-hotel/id/{hotelid}")
    public void deleteHotel(@PathVariable String hotelid){
        hotelService.deleteHotelObject(hotelid);
    }





    //Room Services//
    @PostMapping("/create-room/{hotelid}")
    public Rooms createRoom(@RequestBody Rooms rooms,@PathVariable String hotelid){
        return roomService.registerRoom(rooms,hotelid);
    }

    @DeleteMapping("/delete-room/id/{roomid}")
    public void deleteRoom(@PathVariable String roomid){
        roomService.deleteRoomObject(roomid);
    }
    @PutMapping("/update-room/id/{roomid}")
    public  Rooms updateRoom(@RequestBody Rooms room ,@PathVariable String roomid){
        return roomService.updateRoom(room,roomid);

    }


    //Review Service////
    @PostMapping("/post-review/{entityId}/{userId}")
    public Review postReview(@RequestBody Review review,@PathVariable String entityId,@PathVariable String userId){
        return reviewService.saveNewReview(review,entityId,userId);
    }
    @PutMapping("update-review/{entityId}/{reviewId}")
    public Review updateReview(@RequestBody Review review,@PathVariable String reviewId,@PathVariable String entityId){
        return reviewService.updateReview(review,reviewId,entityId);
    }
    @DeleteMapping("/delete-review/{entityId}/{reviewId}")
    public void deleteReview(@PathVariable String entityId,@PathVariable String reviewId){
        reviewService.deleteReview(entityId,reviewId);
    }



   //Yatch//
    @GetMapping("/get-all-yacht")
    public List<Yacht> getAllYacht(){
        return yachtService.findAllYachts();
    }
    @PostMapping("/create-yacht/id/{userName}")
    public Yacht createYacht(@RequestBody Yacht yacht,@PathVariable String userName){
        return yachtService.createYacht(yacht,userName);
    }
    @PutMapping("/update-yacht/id/{yachtid}")
    public Yacht updateYacht(@RequestBody Yacht yacht,@PathVariable String yachtid){
        return yachtService.updateYacht(yacht,yachtid);
    }
    @DeleteMapping("/delete-yacht/id/{yachtid}")
    public void deleteYacht(@PathVariable String yachtid){
        yachtService.deleteYachtObject(yachtid);
    }




    //PrivateJet//
    @GetMapping("/get-all-private-jet")
    public List<PrivateJet> getAllPrivateJets(){
        return privateJetService.findAllPrivateJets();
    }
    @PostMapping("/create-private-jet/id/{userName}")
    public PrivateJet createPrivateJet(@RequestBody PrivateJet privateJet,@PathVariable String  userName){
        return privateJetService.createPrivateJet(privateJet,userName);
    }
    @PutMapping("/update-private-jet/id/{privatejetid}")
    public PrivateJet updatePrivateJet(@RequestBody PrivateJet privateJet,@PathVariable String privatejetid){
        return privateJetService.updatePrivateJet(privateJet,privatejetid);
    }
    @DeleteMapping("/delete-private-jet/id/{privatejetid}")
    public void deletePrivateJet(@PathVariable String privatejetid){
        privateJetService.deletePrivateJetObject(privatejetid);
    }


    //Restaurant//
    @PostMapping("/create-restaurant/{hotelId}/{userid}")
    public Restaurant createRestaurant(@RequestBody Restaurant restaurant,@PathVariable String hotelId,@PathVariable String userid){
        return restaurantService.registerRestaurantWithHotel(restaurant,hotelId,userid);
    }
    @GetMapping("/get-all-restaurant")
    public List<Restaurant> getAllRestaurant(){
        return restaurantService.getAllRestaurant();
    }
    @PutMapping("/update-restaurant/id/{restaurantid}")
    public Restaurant updateRestaurant(@RequestBody Restaurant restaurant,@PathVariable String restaurantid){
        return restaurantService.updateRestaurant(restaurant,restaurantid);
    }
    @DeleteMapping("/delete-restaurant/id/{restaurantid}")
    public void deleteRestaurant(@PathVariable String restaurantid){
        restaurantService.deleteRestaurantObject(restaurantid);
    }




    //Menu//
    @PostMapping("/create-menu-item/{restaurantId}")
    public MenuItem createMenuItem(@RequestBody MenuItem menuItem,@PathVariable String restaurantId){
        return menuItemService.createMenuItem(menuItem,restaurantId);
    }

    @DeleteMapping("/delete-menu-item/id/{menuitemid}")
    public void deleteMenuItem(@PathVariable String menuitemid){
        menuItemService.deleteMenuItemObject(menuitemid);
    }
    @PutMapping("/update-menu-item/id/{menuitemid}")
    public  MenuItem updateMenuItem(@RequestBody MenuItem menuItem ,@PathVariable String menuitemid){
        return menuItemService.updateMenuItem(menuItem,menuitemid);

    }

    //Requests//
    @GetMapping("/get-All-Requests")
    public List<Request> getAllRequest(){
        return requestService.findAllRequest();
    }





@PostMapping("/accept-Request/{type}/{requestId}")
    public void acceptRequestPartner(@PathVariable String type,@PathVariable String requestId){
        requestService.AcceptRequest(type,requestId);
    }




    @DeleteMapping("/deny-Request/{type}/{requestId}")
    public void deniedRequestPartner(@PathVariable String  type, @PathVariable String requestId) {
        requestService.DeleteRequestA(type,requestId);
    }






}
