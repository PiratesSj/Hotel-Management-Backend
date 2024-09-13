package pirates.Hotel.Managment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pirates.Hotel.Managment.entity.Otp;
import pirates.Hotel.Managment.entity.User;
import pirates.Hotel.Managment.repository.Authors.UserRepository;
import pirates.Hotel.Managment.repository.OtpRepository;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@Service
public class OtpService {

    @Autowired
    private OtpRepository otpRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserRepository userRepository;

    public Otp generateOTP(User user) {
        String otp = String.valueOf((int)(Math.random() * 9000) + 1000); // 4-digit OTP
        System.out.println(otp);
        Otp otpToken = new Otp();
        otpToken.setOtp(otp);
        otpToken.setUserId(user.getId());
        otpToken.setExpiryDate(calculateExpiryDate(10)); // OTP valid for 10 minutes
        otpRepository.save(otpToken);

        emailService.sendVerificationEmail(user.getEmail(), otp);

        return otpToken;
    }

    public boolean verifyOTP(String otp) {
        Otp otpToken = otpRepository.findByOtp(otp).orElse(null);

        if (otpToken == null || otpToken.getExpiryDate().before(new Date())) {
            return false;
        }

        User user = userRepository.findByid(otpToken.getUserId());

        if (user == null) {
            return false;
        }

        user.setEnabled(true);
        userRepository.save(user);
        otpRepository.delete(otpToken);

        return true;
    }

    private Date calculateExpiryDate(int expiryTimeInMinutes) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MINUTE, expiryTimeInMinutes);
        return calendar.getTime();
    }

    public void resend(String email) {

        User user=userRepository.findByuserName(email);
        if (user!=null) {
            generateOTP(user);
        }
    }
}

