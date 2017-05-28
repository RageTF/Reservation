package spring.fx.service.interfaces;

import spring.fx.entity.UserEntity;

/**
 * Created by Rage on 22.05.2017.
 */
public interface UserServiceInterface {

    UserEntity getUserByLogin(String login);
    UserEntity getUserByLoginAndPassword(String login,String password);
    boolean addUser(UserEntity userEntity);
    boolean contain(String login);

}
