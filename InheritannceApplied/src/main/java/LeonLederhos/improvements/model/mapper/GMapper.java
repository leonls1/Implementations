package LeonLederhos.improvements.model.mapper;

import com.noCountry.artAuction.model.entity.BasicEntity;
import org.mapstruct.MappingTarget;


public interface GMapper<T extends BasicEntity, Rq, Rs> {
    T toEntity(Rq dto);

    Rs toDto(T entity);

    void updateExistingEntity(Rq dto, @MappingTarget T entity);
}
