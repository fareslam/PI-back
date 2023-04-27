package esprit.pi.Controller;

import java.util.Date;

import javax.validation.Valid;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

 
import esprit.pi.Repository.UserRepository;
import esprit.pi.entity.User;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "http://localhost:4200")

public class UserController {

 

 
	
    @Autowired
    private UserRepository userRepository;
    
    // Signup method
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@Valid @RequestBody User user) {

    	if (userRepository.existsByCin(user.getCin())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("A user with the given CIN already exists");
        }
    	if (userRepository.existsByEmail(user.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("A user with the given Email already exists");
        }
    	
    	if (userRepository.existsByTel(user.getTel())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("A user with the given Telephone number already exists");
        }
    	
    	if (userRepository.existsByUsername(user.getUsername())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("A user with the given username number already exists");
        }
    	
   	 PasswordEncoder encoder = new BCryptPasswordEncoder();
          user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED); 
    }
    
    @PostMapping("/login")
    public ResponseEntity<?> login( @RequestBody User user) {
        User existingUser = userRepository.findByUsername(user.getUsername());
        if (existingUser == null) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);          }
   	 PasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(user.getPassword());
        if (!encoder.matches(user.getPassword(), existingUser.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect password");
        }
        
        return new ResponseEntity<>(existingUser, HttpStatus.OK);    }

    
}
