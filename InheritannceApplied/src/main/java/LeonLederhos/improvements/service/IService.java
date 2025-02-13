package LeonLederhos.improvements.service;

import LeonLederhos.improvements.model.entity.BasicEntity;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IService<T extends BasicEntity, ID, Rq, FilRq, Rs, CollRs> {

    Rs create(Rq entity);

    T findById(ID id);

    Rs findResponseDtoById(ID id);

    Page<T> findAll(int page, int size);

    Page<T> findAllNotDeleted(int page, int size);

    Page<Rs> findAllRs(int page, int size);

    Page<Rs> findAllRsNotDeleted(int page, int size);

    Page<Rs> findRsByFilter(FilRq filterRequest, int page, int size);

    List<CollRs> findAllCollRs(int page, int size);

    List<CollRs> findAllCollRsNotDeleted(int page, int size);

    List<CollRs> findCollRsByFilter(FilRq filterRequest, int page, int size);

    Rs update(Rq dto, ID id);

    void deleteById(ID id);

    void softDeleteById(ID id);

    boolean existsById(ID id);

}
