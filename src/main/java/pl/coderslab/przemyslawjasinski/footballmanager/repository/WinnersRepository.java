package pl.coderslab.przemyslawjasinski.footballmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.przemyslawjasinski.footballmanager.model.Winners;

import java.util.List;

public interface WinnersRepository extends JpaRepository<Winners, Long> {
    @Override
    <S extends Winners> S save(S entity);

    List<Winners> findWinnersByOrderByCreatedAtDesc();
}
