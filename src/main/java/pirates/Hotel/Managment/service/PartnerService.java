package pirates.Hotel.Managment.service;

import pirates.Hotel.Managment.entity.Partner;
import pirates.Hotel.Managment.entity.User;
import pirates.Hotel.Managment.repository.Authors.PartnerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PartnerService {
    @Autowired
    private PartnerRepository partnerRepository;
    @Autowired
    private UserService userService;


    public Partner addPartner(Partner partner) {


            User user=partner.getUser();

        if (user.getRoles() != null) {
            user.getRoles().clear();
            user.getRoles().add("PARTNER");
            userService.saveUser(user);

        }
        else {
            user.getRoles().add("PARTNER");
            userService.saveUser(user);

        }


        return partnerRepository.save(partner);

    }


    public Partner findByCompanyName(String companyName){
        return partnerRepository.findBycompanyName(companyName);
    }

    public List<Partner> findAllPartner() {
        return partnerRepository.findAll();
    }
    public Partner findByUserId(String id){
        return partnerRepository.findByUserId(id);
    }

    public void deletePartner(String partnerId) {
        Partner partner=findById(partnerId);

        User user=userService.findByUserName(partner.getUser().getUserName());
        partnerRepository.delete(partner);
        userService.deleteUserObject(user);
    }

    public Partner findById(String partnerid) {
        return partnerRepository.findByid(partnerid);
    }

    public void deletePartnerObject(Partner partner){
        partnerRepository.delete(partner);
    }

    public Partner updatePartner(String partnerId, Partner update) {
        Partner partner=findById(partnerId);
        if(partner!=null){
            partner.setCompanyName(!update.getCompanyName().isEmpty() ? update.getCompanyName(): partner.getCompanyName());
            partner.setCompanyAddress(!update.getCompanyAddress().isEmpty() ? update.getCompanyAddress(): partner.getCompanyAddress());
            partner.setContactNumber(!update.getContactNumber().isEmpty() ? update.getContactNumber(): partner.getContactNumber());

            partnerRepository.save(partner);
        }
        return partner;
    }

    public void save(Partner partner) {
        partnerRepository.save(partner);
    }


}
