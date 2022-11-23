package pl.coderslab.przemyslawjasinski.footballmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.przemyslawjasinski.footballmanager.model.Stadium;

import java.util.List;

public interface StadiumRepository extends JpaRepository<Stadium, Long> {
    @Override
    <S extends Stadium> S save(S entity);

    List<Stadium> findAllByOrderByCityAsc();

    Stadium findStadiumById(Long id);
}
