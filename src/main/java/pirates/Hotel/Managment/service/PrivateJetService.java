package pirates.Hotel.Managment.service;


import pirates.Hotel.Managment.entity.Partner;
import pirates.Hotel.Managment.entity.PrivateJet;
import pirates.Hotel.Managment.entity.User;
import pirates.Hotel.Managment.repository.PrivateJetRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PrivateJetService {
   @Autowired
   private PrivateJetRepository privateJetRepository;
   @Autowired
   private UserService userService;
   @Autowired
   private PartnerService partnerService;

    public List<PrivateJet> findAllPrivateJets() {
        return privateJetRepository.findAll();
    }

    public Optional<PrivateJet> findPrivateJetIdById(String privatejetId) {
        return privateJetRepository.findById(privatejetId);

    }

    public PrivateJet createPrivateJet(PrivateJet privateJet, String userName) {
        User user=userService.findByUserName(userName);
        privateJet.setPrivateJetOwnerId(user.getId());
        String role= String.valueOf(user.getRoles());
        if(!Objects.equals(role, "ADMIN")){
            Partner partner =partnerService.findByUserId(user.getId());
            privateJet.setPrivateJetOwner(partner.getCompanyName());
        }
        else {
            privateJet.setPrivateJetOwner("ADMIN");
        }
        return savePrivateJet(privateJet);
    }

    private PrivateJet savePrivateJet(PrivateJet privateJet) {
        return privateJetRepository.save(privateJet);
    }

    public PrivateJet updatePrivateJet(PrivateJet privateJet, String id) {
        PrivateJet privateJet1=findById(id);
        if (privateJet1!=null){
            privateJet1.setPrivateJetCapacity(!(privateJet.getPrivateJetCapacity()==0)? privateJet.getPrivateJetCapacity() : privateJet1.getPrivateJetCapacity());
            privateJet1.setPrivateJetModel(privateJet.getPrivateJetModel()!=null&&!privateJet.getPrivateJetModel().equals("")?privateJet.getPrivateJetModel():privateJet1.getPrivateJetModel());
            privateJet1.setPrivateJetStatus(privateJet.isPrivateJetStatus()==privateJet1.isPrivateJetStatus()? privateJet1.isPrivateJetStatus() : privateJet.isPrivateJetStatus());
        }
        return savePrivateJet(privateJet1);
    }

    private PrivateJet findById(String id) {
        return privateJetRepository.findByid(id);
    }

    public void deletePrivateJetObject(String id) {
        privateJetRepository.deleteById(id);
    }

    public List<PrivateJet> findByPrivatejetPartner(String partnerId) {
        return privateJetRepository.findByprivateJetOwnerId(partnerId);
    }
}
