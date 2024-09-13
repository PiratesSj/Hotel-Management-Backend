package pirates.Hotel.Managment.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import pirates.Hotel.Managment.entity.Partner;
import pirates.Hotel.Managment.entity.User;
import pirates.Hotel.Managment.repository.Authors.PartnerRepository;
import pirates.Hotel.Managment.repository.Authors.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PartnerRepository partnerRepository;
    @Autowired
    private OtpService otpService;
    private static final PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
    public void saveUser(User user){
        userRepository.save(user);
    }
    public User addUser(User user) {

        user.getRoles().add("USER");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setUserName(user.getEmail()!=null?user.getEmail():user.getPhone());
        if (!user.getLastName().equals("")&&user.getLastName()!=null) {
            user.setName(user.getFirstName() + " " + user.getLastName());
        }
        else {
            user.setName(user.getFirstName());
        }
        User save = userRepository.save(user);

        otpService.generateOTP(save);
        return userRepository.save(user);
    }
    public User findByUserName(String username){
        return userRepository.findByuserName(username);
    }
    public List<User> findAllUser(){
        return userRepository.findAll();
    }

    public void deleteUser(String username) {
        User user=findByUserName(username);
        String role= user.getRoles().get(0);
        if (role.equals("PARTNER")){
            deletePartnerbyUser(user);
            userRepository.delete(user);
        }
        else {
        userRepository.delete(user);
        }
    }
    public void deletePartnerbyUser(User user){
        Partner partner=partnerRepository.findByUserId(user.getId());
        partnerRepository.delete(partner);
    }
    public User updateUser(String username, User user){
        String last="";
        String first="";
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User user1 =findByUserName(username);
            if ((!user1.getLastName().equals(user.getLastName()))||(!user1.getFirstName().equals(user.getFirstName()))) {

                if ((!user1.getLastName().equals(user.getLastName()))){
                    user1.setLastName(user.getLastName()!=null&&!user.getLastName().equals("")?user.getLastName():user1.getLastName());
                     last=user1.getLastName();
                }
                if ((!user1.getFirstName().equals(user.getFirstName()))){
                    user1.setFirstName(user.getFirstName()!=null&&!user.getFirstName().equals("")?user.getFirstName():user1.getFirstName());
                     first=user1.getFirstName();
                }
                if (!last.equals("")&&!first.equals("")) {
                    user1.setName(user1.getFirstName() + " " + user1.getLastName());
                }
                if (last.equals("")&&!first.equals("")){
                    user1.setName(user1.getFirstName());
                }

            }
            if (!user1.getEmail().equals(user.getEmail())) {
                user1.setEmail(user.getEmail() != null && !user.getEmail().equals("") ? user.getEmail() : user1.getEmail());
                user1.setUserName(user1.getEmail());
            }
            if (!user1.getPhone().equals(user.getPhone())) {
                user1.setPhone(user.getPhone() != null && !user.getPhone().equals("") ? user.getPhone() : user1.getPhone());
            }
             if (!user1.getPassword().equals(user.getPassword())) {
                 user1.setPassword(user.getPassword() != null && !user.getPassword().equals("") ? user.getPassword() : user1.getPassword());
             }
            userRepository.save(user1);


        return user1;
    }


    public void deleteUserObject(User user) {

        userRepository.delete(user);

    }
    public User findById(String id){
        return userRepository.findByid(id);
    }
}
