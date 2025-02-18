package mk.ukim.finki.wpaud.repository;

import mk.ukim.finki.wpaud.model.User;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserSpringDataRepository extends PagingAndSortingRepository<User, Long>
{


}
