package API.Foodlivery.app.comon.repositories;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
@NoRepositoryBean
public interface FoodliveryRepository<T> extends JpaRepository<T, Long> {
    @Override
    <S extends T> S save(S entity);

    @Override
    T getById(Long aLong);

    @Override
    <S extends T> List<S> saveAll(Iterable<S> entities);
}
