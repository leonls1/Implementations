package LeonLederhos.improvements.service;

import LeonLederhos.improvements.model.dto.request.GFilterRequest;
import LeonLederhos.improvements.model.entity.BasicEntity;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IService<T extends BasicEntity,ID,Rq,FilRq extends GFilterRequest,Rs,CollRs> {

    void create(Rq entity);

    T findById(ID id);

    Rs findResponseDtoById(ID id);

    Page<T> findALl(int page, int size);

    Page<T> findAllNotDeleted(int page, int size);

    Page<CollRs> findAllResponse(int page, int size);

    Page<CollRs> findAllResponseNotDeleted(int page, int size);

    Page<CollRs> findAllByFilter(FilRq filterRequest, int page, int size);

    void update(Rq dto, ID id);

    void deleteById(ID id);

    void softDeleteById(ID id);

    boolean existById(ID id);

}
