package spring.fx.service.interfaces;

import spring.fx.entity.ReservEntity;

import java.util.List;

/**
 * Created by Rage on 22.05.2017.
 */
public interface ReservationServiceInterface {

    List<ReservEntity> getAllReserv();
    ReservEntity getReservById(int id);
    List<ReservEntity> getReservByUserId(int userId);
    void addOrUpdateReserv(ReservEntity reservEntity);
    void removeReserv(ReservEntity reservEntity);
    void removeReservById(int id);

}
