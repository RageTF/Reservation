package spring.fx.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import spring.fx.entity.ReservEntity;

import java.util.List;

/**
 * Created by Rage on 21.05.2017.
 */
@Repository
public interface ReservationRepository extends CrudRepository<ReservEntity,Integer> {

    List<ReservEntity> findAll();
    List<ReservEntity> findByUserId(int userId);

}
