package pl.coderslab.przemyslawjasinski.footballmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.przemyslawjasinski.footballmanager.model.Team;

import java.util.List;

public interface TeamRepository extends JpaRepository<Team, Long> {
    List<Team> findAllByOrderByPointsDesc();

    List<Team> findAllByOrderByNameAsc();

    Team findTeamById(Long id);

    @Override
    <S extends Team> S save(S entity);

    List<Team> findFirst3ByOrderByPointsDesc();

    Team findFirstByOrderByPointsDesc();

    List<Team> findTeamsByStadiumId(Long id);

    List<Team> findTeamsByStadiumIsNull();

}
