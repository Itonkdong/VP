package mk.ukim.finki.wp.kol2024g1.repository;

import jakarta.annotation.Resource;
import mk.ukim.finki.wp.kol2024g1.model.Reservation;

@Resource
public interface ReservationRepository extends JpaSpecificationRepository<Reservation, Long>{

}
