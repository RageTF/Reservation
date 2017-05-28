package spring.fx.entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Rage on 19.05.2017.
 */
@Entity
@Table(name = "reserv", schema = "reservation")
public class ReservEntity {
    private int reservId;
    private Timestamp arrivalDate;
    private Timestamp departureDate;
    private int cottageId;
    private int userId;
    private String phone;
    private CottageEntity cottageEntity;
    private UserEntity userEntity;

    @Id
    @Column(name = "reserv_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getReservId() {
        return reservId;
    }

    public void setReservId(int reservId) {
        this.reservId = reservId;
    }

    @Basic
    @Column(name = "arrival_date")
    public Timestamp getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Timestamp arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    @Basic
    @Column(name = "departure_date")
    public Timestamp getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Timestamp departureDate) {
        this.departureDate = departureDate;
    }

    @Basic
    @Column(name = "cottage_id")
    public int getCottageId() {
        return cottageId;
    }

    public void setCottageId(int cottageId) {
        this.cottageId = cottageId;
    }

    @ManyToOne(optional = false)
    @JoinColumn(name = "cottage_id",nullable = false,insertable = false,updatable = false)
    public CottageEntity getCottageEntity(){
        return cottageEntity;
    }

    public void setCottageEntity(CottageEntity cottageEntity) {
        this.cottageEntity = cottageEntity;
    }

    @Basic
    @Column(name = "phone")
    public String getPhone(){
        return phone;
    }

    public void setPhone(String phone) {
        this.phone=phone;
    }

    @Basic
    @Column(name = "user_id")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id",nullable = false,insertable = false,updatable = false)
    public UserEntity getUserEntity(){
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReservEntity that = (ReservEntity) o;

        if (reservId != that.reservId) return false;
        if (cottageId != that.cottageId) return false;
        if (userId != that.userId) return false;
        if (arrivalDate != null ? !arrivalDate.equals(that.arrivalDate) : that.arrivalDate != null) return false;
        if (departureDate != null ? !departureDate.equals(that.departureDate) : that.departureDate != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = reservId;
        result = 31 * result + (arrivalDate != null ? arrivalDate.hashCode() : 0);
        result = 31 * result + (departureDate != null ? departureDate.hashCode() : 0);
        result = 31 * result + cottageId;
        result = 31 * result + userId;
        return result;
    }
}
