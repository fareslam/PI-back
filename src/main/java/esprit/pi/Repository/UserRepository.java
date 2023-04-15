package esprit.pi.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import esprit.pi.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    
    boolean existsByCin(String cin);
    
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);

    boolean existsByTel(long tel);

    User findByCin(String cin);
    
    User findByUsername(String cin);

    
}
