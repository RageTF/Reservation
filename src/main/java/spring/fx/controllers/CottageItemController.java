package spring.fx.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import spring.fx.entity.CottageEntity;

import java.io.IOException;

/**
 * Created by Rage on 28.05.2017.
 */
public class CottageItemController extends ListCell<CottageEntity> {

    private AdminController mAdminController;
    private MainController mMainController;

    @FXML
    private TextField numberCottage;
    @FXML
    private TextField countOfBerths;
    @FXML
    private TextField cost;
    @FXML
    private CheckBox parking;
    @FXML
    private CheckBox children;
    @FXML
    private CheckBox summer;
    @FXML
    private CheckBox animals;
    @FXML
    private AnchorPane item;

    @FXML
    private Button update;
    @FXML
    private Button remove;

    private FXMLLoader mLLoader;

    public CottageItemController(AdminController adminController) {
        mAdminController = adminController;
    }

    public CottageItemController(MainController mainController) {
        mMainController=mainController;

    }

    public void update(CottageEntity cottageEntity) {
        if(mAdminController!=null) {
            int num;
            int price;
            int count;

            if (numberCottage.getText().equals("") || countOfBerths.getText().equals("") || cost.getText().equals("")) {
                return;
            } else {
                try {
                    num = Integer.parseInt(numberCottage.getText());
                    price = Integer.parseInt(cost.getText());
                    count = Integer.parseInt(countOfBerths.getText());
                } catch (NumberFormatException e) {
                    return;
                }
            }
            cottageEntity.setAllowAnimals(animals.isSelected() ? 1 : 0);
            cottageEntity.setChildPlace(children.isSelected() ? 1 : 0);
            cottageEntity.setCost(price);
            cottageEntity.setCottageNumber(num);
            cottageEntity.setCountOfBerths(count);
            cottageEntity.setParkingPlace(parking.isSelected() ? 1 : 0);
            cottageEntity.setSummerHousePlace(summer.isSelected() ? 1 : 0);
            mAdminController.updateCottage(cottageEntity);
        }
    }

    public void remove(CottageEntity cottageEntity) {
        if(mAdminController!=null)mAdminController.deleteCottage(cottageEntity);
    }

    private void change(CottageEntity item) {
        numberCottage.setText(item.getCottageNumber() + "");
        countOfBerths.setText(item.getCountOfBerths() + "");
        cost.setText(item.getCost() + "");
        if (item.getParkingPlace() == 1) {
            parking.setSelected(true);
        } else {
            parking.setSelected(false);
        }
        if (item.getAllowAnimals() == 1) {
            animals.setSelected(true);
        } else {
            animals.setSelected(false);
        }
        if (item.getSummerHousePlace() == 1) {
            summer.setSelected(true);
        } else {
            summer.setSelected(false);
        }
        if (item.getChildPlace() == 1) {
            children.setSelected(true);
        } else {
            children.setSelected(false);
        }
        if(mMainController!=null){
            update.setVisible(false);
            remove.setVisible(false);
            numberCottage.setDisable(true);
            cost.setDisable(true);
            countOfBerths.setDisable(true);
            parking.setDisable(true);
            children.setDisable(true);
            summer.setDisable(true);
            animals.setDisable(true);
        }
    }

    @Override
    protected void updateItem(CottageEntity cottageEntity, boolean empty) {
        super.updateItem(cottageEntity, empty);
        if (empty || cottageEntity == null) {

            setText(null);
            setGraphic(null);

        } else  {
            mLLoader = new FXMLLoader();
            try {
                mLLoader.setController(this);
                mLLoader.load(getClass().getResourceAsStream("/fxml/cottage_item.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            change(cottageEntity);
            if(mAdminController!=null) {
                update.setOnAction(event -> update(cottageEntity));
                remove.setOnAction(event -> remove(cottageEntity));
            }
            if(mMainController!=null){
                item.setOnMouseClicked(event -> {
                    mMainController.onCottageItemClicked(cottageEntity);
                });
            }
            setText(null);
            setGraphic(this.item);
        }
    }
}
