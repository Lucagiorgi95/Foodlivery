package API.Foodlivery.app.comon.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.UUID;

@NoRepositoryBean
public interface FoodliveryRepository<T> extends JpaRepository<T, Long> {
    @Override
    <S extends T> S save(S entity);

    @Override
    T getById(Long aLong);
}
