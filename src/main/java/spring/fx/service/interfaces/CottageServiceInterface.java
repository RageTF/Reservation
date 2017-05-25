package spring.fx.service.interfaces;

import spring.fx.entity.CottageEntity;

import java.util.List;

/**
 * Created by Rage on 22.05.2017.
 */
public interface CottageServiceInterface {

    void addCottage(CottageEntity cottageEntity);
    void removeCottage(CottageEntity cottageEntity);
    void removeCottageById(int id);
    List<CottageEntity> getAllCottage();
    CottageEntity getCottageById(int id);

}
