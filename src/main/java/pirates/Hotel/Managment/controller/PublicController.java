package  pirates.Hotel.Managment.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import  pirates.Hotel.Managment.entity.*;
import  pirates.Hotel.Managment.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pirates.Hotel.Managment.utils.JwtUtil;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/public")
public class PublicController {
    //Viewing hotels, rooms, restaurant menus, food delivery options, private jets, and yachts.
    StringBuilder stringBuilder=new StringBuilder();
    @Autowired
    private HotelService hotelService;
    @Autowired
    private RoomService roomService;
    @Autowired
    private RestaurantService restaurantService;
    @Autowired
    private PrivateJetService privateJetService;
    @Autowired
    private YachtService yachtService;
    @Autowired
    private UserService userService;
    @Autowired
    private OtpService otpService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserControler userControler;
    @Autowired
    private LocationService locationService;
    @Autowired
    private RequestService requestService;
    @GetMapping("/hotels")
    public List<Hotel> getAllHotels() {
        return hotelService.getAllHotels();
    }

    @GetMapping("/hotelsl/{location}")
    public List<Hotel> getAllHotelsl(@PathVariable String location) {
        return hotelService.findByHotelLocation(location);
    }
    @GetMapping("/Alllocation")
    public List<Location>getAllLocation(){
        return locationService.getAllLocation();
    }

    @GetMapping("/hotels/{partnerId}")
    public List<Hotel> getHotelById(@PathVariable String partnerId) {
        return hotelService.findByHotelPartner(partnerId);
    }


    @GetMapping("privatejets")
    public List<PrivateJet> getAllPrivateJet() {
        return privateJetService.findAllPrivateJets();
    }

    @GetMapping("/privatejets/{privatejetId}")
    public Optional<PrivateJet> getPrivateJetById(@PathVariable String privatejetId) {
        return privateJetService.findPrivateJetIdById(privatejetId);
    }

    @GetMapping("/get-all-restaurant/{hotelId}")
    public List<Restaurant> getAllRestaurantByHotelId(@PathVariable String hotelId){
        Hotel hotel=hotelService.findById(hotelId);
        return hotel.getRestaurants() ;
    }

    @GetMapping("/get-all-restaurant")
    public List<Restaurant> getAllRestaurant(){
        return restaurantService.getAllRestaurant();
    }


    @GetMapping("/yacht")
    public List<Yacht> getAllYacht() {
        return yachtService.findAllYachts();
    }

    @GetMapping("/yacht/{yachtId}")
    public Optional<Yacht> getYachtById(@PathVariable String yachtId) {
        return yachtService.findYachtIdById(yachtId);
    }


    @PostMapping("/sign-up")
    public User SignUp(@RequestBody User user) {

        return userService.addUser(user);
    }
    @PostMapping("/log-in")
    public ResponseEntity<String> logIn(@RequestBody User user) {
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword()));
            UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUserName());
            String jwt = jwtUtil.generateToken(userDetails.getUsername());

            return new ResponseEntity<>(jwt , HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Incorrect username or password", HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/update-password")
    public ResponseEntity<String> resetPasswort(@RequestBody User user) {
        User user1=userService.findByUserName(user.getEmail());
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user1.getUserName(), user1.getPassword()));
            UserDetails userDetails = userDetailsService.loadUserByUsername(user1.getUserName());
            String jwt = jwtUtil.generateToken(userDetails.getUsername());
            userControler.updateUser(user1);
            return new ResponseEntity<>(jwt, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Incorrect username or password", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/verify")
    public ResponseEntity<String> verifyOTP(@RequestBody Otp otp) {

        boolean isVerified = otpService.verifyOTP(otp.getOtp());

        if (isVerified) {
            return ResponseEntity.ok("Email verified successfully.");
        } else {
            return ResponseEntity.status(400).body("Invalid or expired OTP.");
        }
    }
    @PostMapping("/resend-verificationCode")
    public ResponseEntity<String> resendVerifyOTP(@RequestBody String email) {
        StringBuilder str = new StringBuilder(email);
        str.deleteCharAt(0);
        str.deleteCharAt(str.length()-1);
        email=str.toString();
        otpService.resend(email);
        return new ResponseEntity<>("Otp Sent To Email Successfully.", HttpStatus.OK);
    }

    @PostMapping("/Register-partner")
    public void createPartner(@RequestBody Partner partner ){


        Request request=new Request();
        request.setType("Partner");
        request.getPartner().add(partner);
        requestService.Save(request);

    }

    @GetMapping("/health-check")
    public String gethealthcheck(){
        return "Ok";

}
