package spring.fx.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import spring.fx.entity.ReservEntity;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDate;

/**
 * Created by Rage on 28.05.2017.
 */
public class ReservationItemController extends ListCell<ReservEntity> {

    @FXML private TextField phone;
    @FXML private Label name;
    @FXML private Label number;
    @FXML private DatePicker arrival;
    @FXML private DatePicker departure;

    private FXMLLoader mLLoader;

    @FXML
    private Button update;

    @FXML
    private Button remove;

    @FXML
    private AnchorPane item;

    private AdminController mAdminController;
    private MainController mMainController;

    public ReservationItemController(AdminController adminController){
        mAdminController=adminController;
    }

    public ReservationItemController(MainController mainController) {
        mMainController = mainController;
    }

    @Override
    protected void updateItem(ReservEntity item, boolean empty) {
        super.updateItem(item, empty);
        if (empty || item == null) {

            setText(null);
            setGraphic(null);

        } else  {
            mLLoader = new FXMLLoader();
            try {
                mLLoader.setController(this);
                mLLoader.load(getClass().getResourceAsStream("/fxml/reservation_item.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            change(item);
            if(mAdminController!=null) {
                update.setOnAction(event -> update(item));
                remove.setOnAction(event -> remove(item));
            }
            setText(null);
            setGraphic(this.item);
        }
    }

    private void change(ReservEntity reservEntity){
        if(reservEntity!=null) {
            phone.setText(reservEntity.getPhone());
            name.setText(reservEntity.getUserEntity().getUserName());
            number.setText(String.valueOf(reservEntity.getCottageEntity().getCottageNumber()));
            Timestamp arr = reservEntity.getArrivalDate();
            Timestamp dep = reservEntity.getDepartureDate();
            arrival.setValue(arr.toLocalDateTime().toLocalDate());
            departure.setValue(dep.toLocalDateTime().toLocalDate());

            if (mMainController != null) {
                update.setVisible(false);
                remove.setVisible(false);
                phone.setDisable(true);
                arrival.setDisable(true);
                departure.setDisable(true);
            }
        }
    }

    private void update(ReservEntity reservEntity){
        if(phone.getText().equals("")){
            return;
        }
        reservEntity.setArrivalDate(Timestamp.valueOf(arrival.getValue().toString()));
        reservEntity.setDepartureDate(Timestamp.valueOf(arrival.getValue().toString()));
        reservEntity.setPhone(phone.getText());

    }

    private void remove(ReservEntity reservEntity){
        mAdminController.deleteReserv(reservEntity);
    }
}
