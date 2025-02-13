package LeonLederhos.improvements.model.mapper;


import LeonLederhos.improvements.model.entity.BasicEntity;
import org.mapstruct.MappingTarget;


public interface GMapper<E extends BasicEntity, Rq, Rs, CollRes> {
    E toEntity(Rq dto);

    Rs toDto(E entity);

    CollRes toCollectionResponse(E dto);

    void updateExistingEntity(Rq dto, @MappingTarget E entity);
}
