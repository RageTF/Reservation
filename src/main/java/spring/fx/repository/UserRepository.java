package spring.fx.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import spring.fx.entity.UserEntity;

/**
 * Created by Rage on 21.05.2017.
 */
@Repository
public interface UserRepository extends CrudRepository<UserEntity,Integer> {

    UserEntity findByUserLoginAndUserPassword(String userLogin, String userPassword);
    UserEntity findByUserLoginAndUserEmail(String userLogin, String userEmail);
}
