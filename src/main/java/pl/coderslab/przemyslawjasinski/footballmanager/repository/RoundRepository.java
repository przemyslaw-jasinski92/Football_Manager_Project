package pl.coderslab.przemyslawjasinski.footballmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.coderslab.przemyslawjasinski.footballmanager.model.Round;


public interface RoundRepository extends JpaRepository<Round, Long> {

    @Override
    <S extends Round> S save(S entity);

    Round findRoundByRoundNumber(Integer numberRound);

    @Override
    void deleteAll();
}
