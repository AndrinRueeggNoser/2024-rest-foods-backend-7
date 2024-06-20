package ch.noseryoung.backend_team7.domain.user;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByFirstName(String firstName); // Spring Boot (Hibernate) Magic: Based on the method name, a usable SQL query gets generated
    Optional<User> findByEmail(String email);
}