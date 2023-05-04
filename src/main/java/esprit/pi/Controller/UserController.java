package esprit.pi.Controller;

import java.util.Date;

import javax.validation.Valid;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
 
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import esprit.pi.Repository.UserRepository;
import esprit.pi.entity.User;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "*")

public class UserController {

 

 
	
    @Autowired
    private UserRepository userRepository;
    
    // Signup method
    @PostMapping("/signup")
    public ResponseEntity<?> signup( @RequestBody User user) {


    	
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
    
	@GetMapping("/get/{username}")
public User getUser(@PathVariable(value = "username") String username)
{
        User user = userRepository.findByUsername(username);
		return user;
		}
	
    @PutMapping("/update/{username}")
    public ResponseEntity<?> update(@PathVariable(value = "username") String username, @RequestBody User user) {

        User existingUser = userRepository.findByUsername(username);

     
    	

    	
    	if (user.getPassword()==null)
    	{
        	existingUser.setPassword(existingUser.getPassword());

    	}
    	
    	if (user.getPassword()!=null)
    	{

   	 PasswordEncoder encoder = new BCryptPasswordEncoder();
   	existingUser.setPassword(encoder.encode(user.getPassword()));
    	}
    	existingUser.setCin(user.getCin());

    	existingUser.setDateBirth(user.getDateBirth());
    	existingUser.setName(user.getName());
    	existingUser.setSurname(user.getSurname());
    	existingUser.setTel(user.getTel());
    	existingUser.setUsername(user.getUsername());
    	existingUser.setEmail(user.getEmail());
        userRepository.save(existingUser);
        return new ResponseEntity<>(user, HttpStatus.OK); 
    }
    
    
	@GetMapping("/flam")
public int getUser()
{
		 
		return 10510;
		}
    
}
