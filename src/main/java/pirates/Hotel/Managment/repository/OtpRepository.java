package pirates.Hotel.Managment.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pirates.Hotel.Managment.entity.Otp;

import java.util.Optional;

@Repository
public interface OtpRepository extends MongoRepository<Otp, String> {
    Optional<Otp> findByOtp(String otp);
}
