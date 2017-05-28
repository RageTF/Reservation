package spring.fx.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import spring.fx.entity.UserEntity;
import spring.fx.service.UserService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rage on 28.05.2017.
 */
@Component
public class AuthenticationProviderImpl implements AuthenticationProvider{

    @Autowired
    private UserService mUserService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String login=authentication.getName();
        UserEntity userEntity=mUserService.getUserByLogin(login);
        if(userEntity==null){
            throw new UsernameNotFoundException("Incorrect login!");
        }else{

            String password=authentication.getCredentials().toString();

            if(!userEntity.getUserPassword().equals(password)){
                throw new BadCredentialsException("Incorrect password!");
            }

            List<GrantedAuthority> authorities=new ArrayList<>();

            if("admin".equals(login)){
                authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            }else{
                authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
            }

            return new UsernamePasswordAuthenticationToken(userEntity,null,authorities);
        }
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}
