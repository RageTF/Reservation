package spring.fx.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.fx.entity.UserEntity;
import spring.fx.repository.UserRepository;
import spring.fx.service.interfaces.UserServiceInterface;

/**
 * Created by Rage on 22.05.2017.
 */
@Service
public class UserService implements UserServiceInterface {

    @Autowired
    UserRepository mUserRepository;

    @Override
    public UserEntity getUserByLogin(String login) {
        return mUserRepository.findByUserLogin(login);
    }

    @Override
    public UserEntity getUserByLoginAndPassword(String login, String password) {
        return mUserRepository.findByUserLoginAndUserPassword(login,password);
    }

    @Override
    public boolean addUser(UserEntity userEntity) {
        if(!contain(userEntity.getUserLogin())){
            mUserRepository.save(userEntity);
            return true;
        }
        return false;
    }

    @Override
    public boolean contain(String login) {
        return mUserRepository.findByUserLogin(login)==null?false:true;
    }
}
