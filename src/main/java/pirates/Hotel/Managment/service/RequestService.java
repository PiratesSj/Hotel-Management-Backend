package pirates.Hotel.Managment.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pirates.Hotel.Managment.entity.*;
import pirates.Hotel.Managment.repository.RequestRepostory;

import java.util.List;

@Service
public class RequestService {
    @Autowired
    private RequestRepostory requestRepostory;
    @Autowired
    private PartnerService partnerService;
    @Autowired
    private HotelService hotelService;
    @Autowired
    private YachtService yachtService;
    @Autowired
    private PrivateJetService privateJetService;
    @Autowired
    private RestaurantService restaurantService;
    @Autowired
    private UserService userService;



    public void AcceptRequest(String type,String requestId){
        switch(type){
            case "Partner":
                approvePartner(requestId);
                break;  //optional
            case "Hotel":
                  approveHotel(requestId);
                break;
            case "Yacht":
                approveYacht(requestId);
                break;
            case "PrivateJet":
              approvePrivateJet(requestId);
                break;
            case "Restaurant":
                approveRestaurant(requestId);
                break;

            default:

        }
    }

    public void approvePartner(String requestId ){
        Request request=FindById(requestId);
        Partner partner=  request.getPartner().get(0);
        partnerService.addPartner(partner);
        requestRepostory.deleteById(requestId);

    }
    public void approveHotel(String requestId ){
        Request request=FindById(requestId);
        Hotel hotel= request.getHotel().get(0);
        Partner partner= request.getPartner().get(0);
        hotelService.registerHotel(hotel,partner.getUser().getUserName());

        requestRepostory.deleteById(requestId);
    }
    public void approveRestaurant(String requestId ){
        Request request=FindById(requestId);
        Restaurant restaurant= request.getRestaurant().get(0);
        Partner partner= request.getPartner().get(0);
        restaurantService.registerRestaurant(restaurant,partner.getId());
        requestRepostory.deleteById(requestId);
    }
    public void approveYacht(String requestId ){
        Request request=FindById(requestId);
        Yacht yacht=  request.getYacht().get(0);
        Partner partner=request.getPartner().get(0);
        yachtService.createYacht(yacht,partner.getUser().getUserName());
        requestRepostory.deleteById(requestId);
    }
    public void approvePrivateJet(String requestId ){
        Request request=FindById(requestId);
        PrivateJet privateJet=  request.getPrivateJet().get(0);
        Partner partner= request.getPartner().get(0);
        privateJetService.createPrivateJet(privateJet,partner.getUser().getUserName());

        requestRepostory.deleteById(requestId);
    }
    public void DeleteRequestA(String type, String requestId){
        if(type.equals("Partner")){
            DeletePartnerR(requestId);
        }
        else {
            DeleteRequestA(requestId);
        }
    }
    public void DeletePartnerR(String requestId){
        Request request=FindById(requestId);
        Partner partner= request.getPartner().get(0);
        userService.deleteUser(partner.getUser().getUserName());
        requestRepostory.deleteById(requestId);
    }
   public void DeleteRequestA(String requestId){
        requestRepostory.deleteById(requestId);
   }
    public Request FindById(String requestId){
        return requestRepostory.findByid(requestId);
    }

    public void Save(Request request) {

        requestRepostory.save(request);


    }

    public List<Request> findAllRequest() {
        return requestRepostory.findAll();
    }
}
