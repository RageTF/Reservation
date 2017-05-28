package spring.fx.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import spring.fx.ConfigurationControllers;
import spring.fx.entity.CottageEntity;
import spring.fx.entity.ReservEntity;
import spring.fx.entity.UserEntity;
import spring.fx.service.CottageService;
import spring.fx.service.ReservationService;
import spring.fx.service.UserService;

/**
 * Created by Rage on 19.05.2017.
 */
public class MainController {

    private Stage dialogStage;

    @Autowired
    @Qualifier("login_view")
    private ConfigurationControllers.View loginView;

    @Autowired
    @Qualifier("reservation_view")
    private ConfigurationControllers.View reservationsView;

    @Autowired
    private CottageService mCottageService;

    @Autowired
    private ReservationService mReservationService;

    @Autowired
    private UserService mUserService;

    @FXML
    private ListView<CottageEntity> cottages;

    @FXML
    private ListView<ReservEntity> reservations;

    @FXML private Button logout;

    private UserEntity getUserEntity(){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        if(authentication.getPrincipal() instanceof UserEntity){
            return (UserEntity) authentication.getPrincipal();
        }else{
            return null;
        }
    }


    public void notifyDataSetChanged(){
        System.out.println("NOTIFY");
        cottages.setItems(null);
        ObservableList<CottageEntity> cottageEntityObservableList= FXCollections.observableList(mCottageService.getAllCottage());
        cottages.setCellFactory(param -> new CottageItemController(this));
        cottages.setItems(cottageEntityObservableList);

        UserEntity userEntity=getUserEntity();
        if(userEntity!=null) {
            ObservableList<ReservEntity> reservEntityObservableList = FXCollections.observableList(mReservationService.getReservByUserId(userEntity.getUserId()));
            reservations.setCellFactory(param -> new ReservationItemController(this));
            reservations.setItems(null);
            reservations.setItems(reservEntityObservableList);
        }
    }

    public void onCottageItemClicked(CottageEntity cottageEntity){
        ReservationController reservationController=(ReservationController)reservationsView.getController();
        reservationController.setCottageService(mCottageService);
        if(dialogStage==null){
            dialogStage=new Stage();
            dialogStage.setTitle("Reservation cottage");
            dialogStage.setScene(new Scene(reservationsView.getView()));
            reservationController.setReservationService(mReservationService);
            dialogStage.centerOnScreen();
            dialogStage.setOnHidden(event -> notifyDataSetChanged());
        }
        reservationController.invalidate();
        reservationController.setUserEntity(getUserEntity());
        reservationController.setIdCottage(cottageEntity.getCottageId());
        dialogStage.show();
    }

    @FXML
    public void refresh(){
        notifyDataSetChanged();
    }

    @FXML
    public void logout(){
        SecurityContextHolder.clearContext();
        logout.getScene().setRoot(loginView.getView());
    }

}
