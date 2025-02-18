package mk.ukim.finki.wp.kol2023.g1.repository;

import mk.ukim.finki.wp.kol2023.g1.model.Player;
import mk.ukim.finki.wp.kol2023.g1.model.PlayerPosition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface PlayerRepository extends JpaSpecificationRepository<Player, Long>
{

    List<Player> findByPointsPerGameIsLessThan(Double playerPoints);
    List<Player> findByPosition(PlayerPosition playerPosition);
    List<Player> findByPositionAndPointsPerGameLessThan(PlayerPosition playerPosition, Double playerPoints);

}
