package pl.coderslab.przemyslawjasinski.footballmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.przemyslawjasinski.footballmanager.model.Match;

import java.util.List;

public interface MatchRepository extends JpaRepository<Match, Long> {
    List<Match> findAll();

    @Query("select m from Match m where m.homeTeam.id = ?1 or m.awayTeam.id = ?1")
    List<Match> findAllMatchesByTeamId(Long id);

    @Override
    <S extends Match> S save(S entity);

    @Query("select m from Match m where m.round.id = ?1")
    List<Match>  findAllMatchesByRound(Long roundId);

    Match getMatchById(Long id);

    @Override
    void deleteAll();

    Integer countMatchesByUpdatedAtIsNotNull();

    Integer countMatchesByUpdatedAtIsNull();
}
