package spring.fx.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import spring.fx.entity.CottageEntity;
import spring.fx.entity.ReservEntity;
import spring.fx.entity.UserEntity;
import spring.fx.service.CottageService;
import spring.fx.service.ReservationService;
import spring.fx.util.Validator;

import java.sql.Timestamp;
import java.time.LocalDate;

/**
 * Created by Rage on 28.05.2017.
 */
public class ReservationController {

    private CottageEntity mCottageEntity;
    private UserEntity mUserEntity;

    private CottageService mCottageService;
    private ReservationService mReservationService;

    @FXML private TextField phone;
    @FXML private Label nameUser;
    @FXML private Label number;
    @FXML private DatePicker arrival;
    @FXML private DatePicker departure;
    @FXML private Button book;
    @FXML private Button cancel;

    private int idCottage;

    public void invalidate(){
        nameUser.setText("");
        number.setText("");
        phone.setText("");
        arrival.setValue(null);
        departure.setValue(null);
        idCottage=-1;
    }

    public void setCottageService(CottageService cottageService) {
        mCottageService = cottageService;
    }

    public void setReservationService(ReservationService reservationService) {
        mReservationService = reservationService;
    }

    public void setIdCottage(int idCottage) {
        this.idCottage = idCottage;
        mCottageEntity=mCottageService.getCottageById(idCottage);
        number.setText(String.valueOf(mCottageEntity.getCottageNumber()));
    }

    public void setUserEntity(UserEntity userEntity) {
        mUserEntity = userEntity;
        nameUser.setText(userEntity.getUserName());
    }

    @FXML
    public void book(){
        if(arrival.getValue()==null||departure.getValue()==null||phone.getText().equals("")){
            Alert suc=new Alert(Alert.AlertType.ERROR,"Fill in all the field!");
            suc.show();
            return;
        }
        if(arrival.getValue().compareTo(LocalDate.now())<0
                ||departure.getValue().compareTo(LocalDate.now())<=0){
            Alert suc=new Alert(Alert.AlertType.ERROR,"Arrival and departure date can't be less than current!");
            suc.show();
            return;
        }
        if(arrival.getValue().compareTo(departure.getValue())>=0){
            Alert suc=new Alert(Alert.AlertType.ERROR,"Arrival date can't be more or equals departure date!");
            suc.show();
            return;
        }

        if(!Validator.validPhone(phone.getText())){
            Alert suc=new Alert(Alert.AlertType.ERROR,"Incorrect phone number!!");
            suc.show();
            return;
        }

        ReservEntity reservEntity=new ReservEntity();
        reservEntity.setPhone(phone.getText());
        reservEntity.setCottageId(mCottageEntity.getCottageId());
        reservEntity.setUserId(mUserEntity.getUserId());
        LocalDate arr=arrival.getValue();
        reservEntity.setArrivalDate(Timestamp.valueOf(arr.atStartOfDay()));
        LocalDate dep=departure.getValue();
        reservEntity.setDepartureDate(Timestamp.valueOf(dep.atStartOfDay()));
        mReservationService.addOrUpdateReserv(reservEntity);
        Stage stage=((Stage)book.getScene().getWindow());

        Alert suc=new Alert(Alert.AlertType.INFORMATION,"Booked success!");
        suc.show();

        stage.close();
    }

    @FXML
    public void cancel(){

        ((Stage)cancel.getScene().getWindow()).close();
    }
}
