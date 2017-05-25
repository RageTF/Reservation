package spring.fx.service;

import org.springframework.beans.factory.annotation.Autowired;
import spring.fx.entity.UserEntity;
import spring.fx.repository.UserRepository;
import spring.fx.service.interfaces.UserServiceInterface;

/**
 * Created by Rage on 22.05.2017.
 */
public class UserService implements UserServiceInterface {

    @Autowired
    UserRepository mUserRepository;

    @Override
    public UserEntity getUserByLoginAndPassword(String login, String password) {
        return mUserRepository.findByUserLoginAndUserPassword(login,password);
    }

    @Override
    public boolean addUser(UserEntity userEntity) {
        if(!contain(userEntity.getUserLogin(),userEntity.getUserEmail())){
            mUserRepository.save(userEntity);
            return true;
        }
        return false;
    }

    @Override
    public boolean contain(String login, String email) {
        return mUserRepository.findByUserLoginAndUserEmail(login,email)==null?false:true;
    }
}
