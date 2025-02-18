package mk.ukim.finki.wp.kol2023.g1.service.impl;

import mk.ukim.finki.wp.kol2023.g1.model.Player;
import mk.ukim.finki.wp.kol2023.g1.model.PlayerPosition;
import mk.ukim.finki.wp.kol2023.g1.model.Team;
import mk.ukim.finki.wp.kol2023.g1.model.exceptions.InvalidPlayerIdException;
import mk.ukim.finki.wp.kol2023.g1.model.exceptions.InvalidTeamIdException;
import mk.ukim.finki.wp.kol2023.g1.repository.PlayerRepository;
import mk.ukim.finki.wp.kol2023.g1.repository.TeamRepository;
import mk.ukim.finki.wp.kol2023.g1.service.PlayerService;
import mk.ukim.finki.wp.kol2023.g1.service.PlayerSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerServiceImpl implements PlayerService
{

    private final PlayerRepository playerRepository;

    private final TeamRepository teamRepository;

    public PlayerServiceImpl(PlayerRepository playerRepository, TeamRepository teamRepository)
    {
        this.playerRepository = playerRepository;
        this.teamRepository = teamRepository;
    }

    @Override
    public List<Player> listAllPlayers()
    {
        return this.playerRepository
                .findAll();
    }

    @Override
    public Player findById(Long id)
    {
        return this
                .playerRepository
                .findById(id)
                .orElseThrow(InvalidPlayerIdException::new);
    }

    @Override
    public Player create(String name, String bio, Double pointsPerGame, PlayerPosition position, Long team)
    {
        Team team1 = this.teamRepository
                .findById(team)
                .orElseThrow(InvalidTeamIdException::new);

        Player player = new Player(name, bio, pointsPerGame, position, team1);

        this.playerRepository
                .save(player);

        return player;
    }

    @Override
    public Player update(Long id, String name, String bio, Double pointsPerGame, PlayerPosition position, Long team)
    {
        Team team1 = this.teamRepository
                .findById(team)
                .orElseThrow(InvalidTeamIdException::new);

        Player player = this.playerRepository
                .findById(id)
                .orElseThrow(InvalidPlayerIdException::new);

        player.setName(name);
        player.setBio(bio);
        player.setPointsPerGame(pointsPerGame);
        player.setPosition(position);
        player.setTeam(team1);

        this.playerRepository
                .save(player);


        return player;
    }

    @Override
    public Player delete(Long id)
    {
        Player player = this.playerRepository
                .findById(id)
                .orElseThrow(InvalidPlayerIdException::new);

        this.playerRepository
                .delete(player);

        return player;
    }

    @Override
    public Player vote(Long id)
    {
        Player player = this.playerRepository
                .findById(id)
                .orElseThrow(InvalidPlayerIdException::new);

        player.setVotes(player.getVotes() + 1);

        this.playerRepository
                .save(player);

        return player;
    }

    @Override
    public List<Player> listPlayersWithPointsLessThanAndPosition(Double pointsPerGame, PlayerPosition position)
    {

//        if (pointsPerGame == null && position == null)
//        {
//            this.playerRepository
//                    .findAll();
//        }
//
//        if (pointsPerGame != null && position == null)
//        {
//            this.playerRepository
//                    .findByPointsPerGameIsLessThan(pointsPerGame);
//        }
//
//        if (position != null)
//        {
//            this.playerRepository
//                    .findByPosition(position);
//        }
//
//        return this.playerRepository
//                .findByPositionAndPointsPerGameLessThan(position, pointsPerGame);



        Specification<Player> specification = Specification
                .where(PlayerSpecification.findByPointsPerGame(pointsPerGame))
                .and(PlayerSpecification.findByPosition(position));


////        //Vo novata verzija
//         Specification<Player> specification = Specification
//                .where(FieldFilterSpecification.lessThen(Player.class, "pointsPerGame", pointsPerGame))
//                .and(FieldFilterSpecification.filterEqualsV(Player.class, "playerPosition", position));


        List<Player> players = this.playerRepository
                .findAll(specification);


        return players;
    }


}
