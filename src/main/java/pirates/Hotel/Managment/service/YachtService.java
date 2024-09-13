package pirates.Hotel.Managment.service;

import pirates.Hotel.Managment.entity.*;
import pirates.Hotel.Managment.repository.YachtRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class YachtService {
    @Autowired
    private YachtRepository yachtRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private PartnerService partnerService;
    public List<Yacht> findAllYachts(){
        return yachtRepository.findAll();
    }
    public Optional<Yacht> findYachtIdById(String privatejetId) {
        return yachtRepository.findById(privatejetId);

    }
    public Yacht saveYacht(Yacht yacht){
        return yachtRepository.save(yacht);
    }


    public Yacht createYacht(Yacht yacht, String userName) {

        User user=userService.findByUserName(userName);

        String role= String.valueOf(user.getRoles());
        if(!Objects.equals(role, "ADMIN")){
            Partner partner =partnerService.findByUserId(user.getId());
            yacht.setYachtOwner(partner.getCompanyName());
            yacht.setYachtOwnerId(partnerService.findByUserId(user.getId()).getId());
        }
        else {
            yacht.setYachtOwner("ADMIN");
        }
        yacht.setYachtStatus(true);
        return saveYacht(yacht);
    }

    public Yacht updateYacht(Yacht yacht, String id) {
        Yacht yacht1=findById(id);
        if (yacht1!=null){
            yacht1.setYachtCapacity(!(yacht.getYachtCapacity()==0)? yacht.getYachtCapacity() : yacht1.getYachtCapacity());
            yacht.setYachtName(yacht.getYachtName()!=null&&!yacht.getYachtName().equals("")?yacht.getYachtName():yacht1.getYachtName());
            yacht.setYachtStatus(yacht.isYachtStatus()==yacht1.isYachtStatus()? yacht1.isYachtStatus() : yacht.isYachtStatus());
        }
        return saveYacht(yacht1);

    }

    private Yacht findById(String  id) {
        return yachtRepository.findByid(id);
    }

    public void deleteYachtObject(String id) {
        yachtRepository.deleteById(id);
    }

    public List<Yacht> findByYachtPartner(String  partnerId) {
        return yachtRepository.findByYachtOwnerId(partnerId);
    }
}
