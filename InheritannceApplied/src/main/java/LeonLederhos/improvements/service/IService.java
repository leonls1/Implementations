package LeonLederhos.improvements.service;

import java.util.List;

public interface IService<T, ID, Rq, Rs> {

    void create(Rq entity);

    T findById(ID id);

    Rs findResponseDtoById(ID id);

    List<T> findALl();

    List<T> findAllNotDeleted();

    List<Rs> findAllResponse();

    List<Rs> findAllResponseNotDeleted();

    void update(Rq dto, ID id);

    void deleteById(ID id);

    void softDeleteById(ID id);

    boolean existById(ID id);

}
