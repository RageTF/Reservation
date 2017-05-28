package spring.fx.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import spring.fx.ConfigurationControllers;
import spring.fx.entity.CottageEntity;
import spring.fx.entity.ReservEntity;
import spring.fx.service.CottageService;
import spring.fx.service.ReservationService;
import spring.fx.util.Validator;

import javax.annotation.PostConstruct;

/**
 * Created by Rage on 19.05.2017.
 */
public class AdminController {

    @Autowired
    @Qualifier("login_view")
    private ConfigurationControllers.View loginView;

    @Autowired
    private CottageService mCottageService;

    @Autowired
    private ReservationService mReservationService;

    @FXML private TextField cottageNumber;
    @FXML private TextField countOfBerth;
    @FXML private CheckBox parkingPlace;
    @FXML private CheckBox summerHouse;
    @FXML private CheckBox childPlace;
    @FXML private CheckBox allowAnimals;
    @FXML private TextField cost;
    @FXML private Label info;
    @FXML private Button logout;

    @FXML private ListView<CottageEntity> cottages;
    @FXML private ListView<ReservEntity> reservations;

    @PostConstruct
    public void init(){
        notifyDataSetChanged();
    }

    public void notifyDataSetChanged(){
        ObservableList<CottageEntity> cottageEntityObservableList= FXCollections.observableList(mCottageService.getAllCottage());
        cottages.setCellFactory(param -> new CottageItemController(this));
        cottages.setItems(null);
        cottages.setItems(cottageEntityObservableList);
        ObservableList<ReservEntity> reservEntityObservableList=FXCollections.observableList(mReservationService.getAllReserv());
        reservations.setCellFactory(param -> new ReservationItemController(this));
        reservations.setItems(null);
        reservations.setItems(reservEntityObservableList);
    }

    @FXML
    public void addCottage(){
        if(cottageNumber.getText().equals("")||countOfBerth.getText().equals("")||cost.getText().equals("")){
            Alert alert=new Alert(Alert.AlertType.ERROR,"Fill in all the fields");
            alert.show();
            return;
        }

        if(!Validator.validNumber(cost.getText())){
            cost.setText("");
            Alert alert=new Alert(Alert.AlertType.ERROR,"Cost can only be a number!!");
            alert.show();
            return;
        }

        if(!Validator.validNumber(cottageNumber.getText())){
            cottageNumber.setText("");
            Alert alert=new Alert(Alert.AlertType.ERROR,"Cottage number can only be a number!!");
            alert.show();
            return;
        }

        if(!Validator.validNumber(countOfBerth.getText())){
            countOfBerth.setText("");
            Alert alert=new Alert(Alert.AlertType.ERROR,"Count of berths can only be a number!!");
            alert.show();
            return;
        }

        int price=Integer.parseInt(cost.getText());
        int number=Integer.parseInt(cottageNumber.getText());
        int count=Integer.parseInt(countOfBerth.getText());

        CottageEntity cottageEntity=new CottageEntity();
        cottageEntity.setAllowAnimals(allowAnimals.isSelected()?1:0);
        cottageEntity.setChildPlace(childPlace.isSelected()?1:0);
        cottageEntity.setCost(price);
        cottageEntity.setCottageNumber(number);
        cottageEntity.setCountOfBerths(count);
        cottageEntity.setParkingPlace(parkingPlace.isSelected()?1:0);
        cottageEntity.setSummerHousePlace(summerHouse.isSelected()?1:0);

        mCottageService.addOrUpdateCottage(cottageEntity);
        notifyDataSetChanged();
        Alert alert=new Alert(Alert.AlertType.INFORMATION,"Cottage success added!");
        alert.show();
    }

    public void deleteCottage(CottageEntity cottageEntity){
        mCottageService.removeCottageById(cottageEntity.getCottageId());
        cottages.getItems().remove(cottageEntity);
    }

    public void updateCottage(CottageEntity cottageEntity){
        mCottageService.addOrUpdateCottage(cottageEntity);
    }

    public void deleteReserv(ReservEntity reservEntity){
        mReservationService.removeReserv(reservEntity);
        reservations.getItems().remove(reservEntity);
    }

    public void updateReserv(ReservEntity reservEntity){
        mReservationService.addOrUpdateReserv(reservEntity);
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
