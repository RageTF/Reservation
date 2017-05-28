package spring.fx.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import spring.fx.ConfigurationControllers;
import spring.fx.entity.UserEntity;
import spring.fx.service.UserService;
import spring.fx.util.Validator;

import javax.annotation.PostConstruct;

/**
 * Created by Rage on 28.05.2017.
 */
public class RegistrationController{

    @Autowired
    private UserService mUserService;

    @Autowired
    @Qualifier("login_view")
    private ConfigurationControllers.View loginView;

    @FXML
    private TextField login;

    @FXML
    private PasswordField password;

    @FXML
    private PasswordField confirmPassword;

    @FXML
    private TextField name;

    @FXML
    private TextField email;

    @FXML
    private Button signIn;

    @FXML
    private Label info;

    @FXML
    private Button signUp;

    @PostConstruct
    public void init(){

    }

    @FXML
    public void authorization(){
        login.clear();
        password.clear();
        confirmPassword.clear();
        name.clear();
        email.clear();
        signIn.getScene().setRoot(loginView.getView());
    }

    @FXML
    public void registration(){
        if(login.getText().equals("") || password.getText().equals("")
                ||confirmPassword.equals("") || name.equals("") || email.equals("")){
            Alert alert=new Alert(Alert.AlertType.WARNING,"Fill in all the fields");
            alert.show();
            return;
        }
        if(!password.getText().equals(confirmPassword.getText())){
            confirmPassword.setText("");
            Alert alert=new Alert(Alert.AlertType.WARNING,"Password don't match!");
            alert.show();
            return;
        }
        if(!Validator.validEmail(email.getText())){
            email.setText("");
            Alert alert=new Alert(Alert.AlertType.WARNING,"Incorrect email!");
            alert.show();
            return;
        }
        UserEntity userEntity=new UserEntity();
        userEntity.setUserLogin(login.getText());
        userEntity.setUserEmail(email.getText());
        userEntity.setUserPassword(password.getText());
        userEntity.setUserName(name.getText());
        if(!mUserService.addUser(userEntity)){
            Alert alert=new Alert(Alert.AlertType.WARNING,"User already exist!!");
            alert.show();
        }else{
            Alert alert=new Alert(Alert.AlertType.INFORMATION,"Success registration!");
            alert.show();
            authorization();

        }
    }

}
