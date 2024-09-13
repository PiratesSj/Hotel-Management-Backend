package  pirates.Hotel.Managment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import pirates.Hotel.Managment.entity.User;
import pirates.Hotel.Managment.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserControler {
    @Autowired
    private UserService userService;

    @PutMapping("/update-user")
    public User updateUser( @RequestBody User user){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String username=authentication.getName();
        return userService.updateUser(username,user);
    }
    @DeleteMapping("/delete-user/{username}")
    public void deleteUser(@PathVariable String username){
        userService.deleteUser(username);
    }

    @GetMapping("/get-detail/{username}")
    public User getRole(@PathVariable String username){
        return userService.findByUserName(username);
    }
    @GetMapping("/health-Check")
    public ResponseEntity<String> HealthCheck(){
        try {
            return new ResponseEntity<>("Ok", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("",HttpStatus.BAD_REQUEST);
        }


    }

}
