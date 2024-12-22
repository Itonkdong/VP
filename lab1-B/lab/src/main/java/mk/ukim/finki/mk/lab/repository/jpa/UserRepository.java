package mk.ukim.finki.mk.lab.repository.jpa;

import mk.ukim.finki.mk.lab.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String>
{
    Optional<User> findByUsername(String username);
    Optional<User> findByUsernameAndPassword(String username, String password);
}
