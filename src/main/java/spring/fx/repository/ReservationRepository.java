package spring.fx.repository;

import javafx.collections.ObservableList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import spring.fx.entity.ReservEntity;

import java.util.List;

/**
 * Created by Rage on 21.05.2017.
 */
@Repository
public interface ReservationRepository extends CrudRepository<ReservEntity,Integer> {

    ObservableList<ReservEntity> findAll();
    ObservableList<ReservEntity> findByUserId(int userId);

}
