package spring.fx.service;

import org.springframework.beans.factory.annotation.Autowired;
import spring.fx.entity.ReservEntity;
import spring.fx.repository.ReservationRepository;
import spring.fx.service.interfaces.ReservationServiceInterface;

import java.util.List;

/**
 * Created by Rage on 22.05.2017.
 */
public class ReservationService implements ReservationServiceInterface {

    @Autowired
    ReservationRepository mReservationRepository;

    @Override
    public List<ReservEntity> getAllReserv() {
        return mReservationRepository.findAll();
    }

    @Override
    public ReservEntity getReservById(int id) {
        return mReservationRepository.findOne(id);
    }

    @Override
    public List<ReservEntity> getReservByUserId(int userId) {
        return mReservationRepository.findByUserId(userId);
    }

    @Override
    public void addReserv(ReservEntity reservEntity) {
        mReservationRepository.save(reservEntity);
    }

    @Override
    public void removeReserv(ReservEntity reservEntity) {
        mReservationRepository.delete(reservEntity);
    }

    @Override
    public void removeReservById(int id) {
        mReservationRepository.delete(id);
    }
}
