package spring.fx.entity;

import javax.persistence.*;

/**
 * Created by Rage on 19.05.2017.
 */
@Entity
@Table(name = "cottage", schema = "reservation")
public class CottageEntity {
    private int cottageId;
    private int cottageNumber;
    private int countOfBerths;
    private Integer parkingPlace;
    private Integer childPlace;
    private Integer summerHousePlace;
    private Integer allowAnimals;
    private int cost;

    @Id
    @Column(name = "cottage_id")
    public int getCottageId() {
        return cottageId;
    }

    public void setCottageId(int cottageId) {
        this.cottageId = cottageId;
    }

    @Basic
    @Column(name = "cottage_number")
    public int getCottageNumber() {
        return cottageNumber;
    }

    public void setCottageNumber(int cottageNumber) {
        this.cottageNumber = cottageNumber;
    }

    @Basic
    @Column(name = "count_of_berths")
    public int getCountOfBerths() {
        return countOfBerths;
    }

    public void setCountOfBerths(int countOfBerths) {
        this.countOfBerths = countOfBerths;
    }

    @Basic
    @Column(name = "parking_place")
    public Integer getParkingPlace() {
        return parkingPlace;
    }

    public void setParkingPlace(Integer parkingPlace) {
        this.parkingPlace = parkingPlace;
    }

    @Basic
    @Column(name = "child_place")
    public Integer getChildPlace() {
        return childPlace;
    }

    public void setChildPlace(Integer childPlace) {
        this.childPlace = childPlace;
    }

    @Basic
    @Column(name = "summer_house_place")
    public Integer getSummerHousePlace() {
        return summerHousePlace;
    }

    public void setSummerHousePlace(Integer summerHousePlace) {
        this.summerHousePlace = summerHousePlace;
    }

    @Basic
    @Column(name = "allow_animals")
    public Integer getAllowAnimals() {
        return allowAnimals;
    }

    public void setAllowAnimals(Integer allowAnimals) {
        this.allowAnimals = allowAnimals;
    }

    @Basic
    @Column(name = "cost")
    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CottageEntity that = (CottageEntity) o;

        if (cottageId != that.cottageId) return false;
        if (cottageNumber != that.cottageNumber) return false;
        if (countOfBerths != that.countOfBerths) return false;
        if (cost != that.cost) return false;
        if (parkingPlace != null ? !parkingPlace.equals(that.parkingPlace) : that.parkingPlace != null)
            return false;
        if (childPlace != null ? !childPlace.equals(that.childPlace) : that.childPlace != null) return false;
        if (summerHousePlace != null ? !summerHousePlace.equals(that.summerHousePlace) : that.summerHousePlace != null)
            return false;
        if (allowAnimals != null ? !allowAnimals.equals(that.allowAnimals) : that.allowAnimals != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = cottageId;
        result = 31 * result + cottageNumber;
        result = 31 * result + countOfBerths;
        result = 31 * result + (parkingPlace != null ? parkingPlace.hashCode() : 0);
        result = 31 * result + (childPlace != null ? childPlace.hashCode() : 0);
        result = 31 * result + (summerHousePlace != null ? summerHousePlace.hashCode() : 0);
        result = 31 * result + (allowAnimals != null ? allowAnimals.hashCode() : 0);
        result = 31 * result + cost;
        return result;
    }
}
