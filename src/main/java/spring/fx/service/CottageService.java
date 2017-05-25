package spring.fx.service;

import org.springframework.beans.factory.annotation.Autowired;
import spring.fx.entity.CottageEntity;
import spring.fx.repository.CottageRepository;
import spring.fx.service.interfaces.CottageServiceInterface;

import java.util.List;

/**
 * Created by Rage on 22.05.2017.
 */
public class CottageService implements CottageServiceInterface {

    @Autowired
    CottageRepository mCottageRepository;

    @Override
    public void addCottage(CottageEntity cottageEntity) {
        mCottageRepository.save(cottageEntity);
    }

    @Override
    public void removeCottage(CottageEntity cottageEntity) {
        mCottageRepository.delete(cottageEntity);
    }

    @Override
    public void removeCottageById(int id) {
        mCottageRepository.delete(id);
    }

    @Override
    public List<CottageEntity> getAllCottage() {
        return mCottageRepository.findAll();
    }

    @Override
    public CottageEntity getCottageById(int id) {
        return mCottageRepository.findOne(id);
    }
}
