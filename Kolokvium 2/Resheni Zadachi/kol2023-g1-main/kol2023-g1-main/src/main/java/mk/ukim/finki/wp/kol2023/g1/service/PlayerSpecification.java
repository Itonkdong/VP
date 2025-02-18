package mk.ukim.finki.wp.kol2023.g1.service;

import mk.ukim.finki.wp.kol2023.g1.model.Player;
import mk.ukim.finki.wp.kol2023.g1.model.PlayerPosition;
import org.springframework.data.jpa.domain.Specification;

public class PlayerSpecification
{
     public static Specification<Player> findByPointsPerGame(Double pointsPerGame)
    {
        if (pointsPerGame == null)
        {
            return null;
        }

        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.lessThan(root.get("pointsPerGame"), pointsPerGame);
    }


    public static Specification<Player> findByPosition(PlayerPosition playerPosition)
    {
        if (playerPosition == null)
        {
            return null;
        }

        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("position"), playerPosition);
    }
}
