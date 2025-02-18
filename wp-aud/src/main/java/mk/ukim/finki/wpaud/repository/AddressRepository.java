package mk.ukim.finki.wpaud.repository;

import mk.ukim.finki.wpaud.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AddressRepository extends JpaRepository<Address, Long>
{

    Address findByUser_Username(String a);
    Address findByUserIsNotNull();

//    @Query(value = "select a from Address a join User u on a.user.id = u.id where u.username = :username",nativeQuery = false)
    @Query(value = "select a from Address a join User u on a.user.id = u.id where u.username = ?1",nativeQuery = false)
    Address findAllUserByUsernameMy(String username);

    @Modifying
    @Query(value = "delete from Address a where a.city = ?1")
    void deleteAllByCityMy(String city);

}
