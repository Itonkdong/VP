package mk.ukim.finki.wpaud.repository;

import mk.ukim.finki.wpaud.model.User;

import mk.ukim.finki.wpaud.model.UserRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAdvancedSpringDataRepository extends JpaRepository<User, Long>
{

    Page<User> findAll(Specification<User> specification, Pageable pageable);
}
