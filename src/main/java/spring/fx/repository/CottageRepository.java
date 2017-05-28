package spring.fx.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import spring.fx.entity.CottageEntity;

import java.util.List;

/**
 * Created by Rage on 21.05.2017.
 */
@Repository
public interface CottageRepository extends CrudRepository<CottageEntity,Integer> {

    List<CottageEntity> findAll();

}
