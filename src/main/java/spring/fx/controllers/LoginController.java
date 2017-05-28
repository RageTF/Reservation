package spring.fx.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import spring.fx.ConfigurationControllers;
import spring.fx.security.AuthenticationProviderImpl;

/**
 * Created by Rage on 19.05.2017.
 */
public class LoginController {

    @Autowired
    @Qualifier("admin_view")
    private ConfigurationControllers.View adminView;

    @Autowired
    @Qualifier("main_view")
    private ConfigurationControllers.View mainView;

    @Autowired
    @Qualifier("registration_view")
    private ConfigurationControllers.View registrationView;

    @Autowired
    private AuthenticationProviderImpl mAuthenticationProvider;

    @FXML
    private Label info;

    @FXML
    private TextField login;

    @FXML
    private PasswordField password;

    @FXML
    private Button signIn;

    @FXML
    private Button signUp;

    @FXML
    public void authorization(){
        Authentication authentication=new UsernamePasswordAuthenticationToken(login.getText(),password.getText());
        try{
            authentication=mAuthenticationProvider.authenticate(authentication);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            if(authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))){
                ((AdminController)adminView.getController()).notifyDataSetChanged();
                signIn.getScene().setRoot(adminView.getView());
            }else{
                ((MainController) mainView.getController()).notifyDataSetChanged();
                signIn.getScene().setRoot(mainView.getView());
            }
        }catch (AuthenticationException e){
            password.clear();
            Alert alert=new Alert(Alert.AlertType.WARNING,"Incorrect password or login!");
            alert.show();
        }
    }

    @FXML
    public void registration(){
        login.clear();
        password.clear();
        info.setText("");
        signUp.getScene().setRoot(registrationView.getView());
    }

}
