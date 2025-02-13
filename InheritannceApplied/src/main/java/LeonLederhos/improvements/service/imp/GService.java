package LeonLederhos.improvements.service.imp;

import com.noCountry.artAuction.model.entity.BasicEntity;
import com.noCountry.artAuction.model.mapper.GMapper;
import com.noCountry.artAuction.repository.GRepository;
import com.noCountry.artAuction.service.IService;
import lombok.Getter;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Getter
public abstract class GService<E extends BasicEntity, ID, Rq, Rs> implements IService<E, ID, Rq, Rs> {

    private final GRepository<E, ID> repository;
    private final GMapper<E, Rq, Rs> mapper;
    private final RuntimeException notFoundException;

    public GService(GRepository<E, ID> repository, GMapper<E, Rq, Rs> mapper, RuntimeException notFoundException) {
        this.repository = repository;
        this.mapper = mapper;
        this.notFoundException = notFoundException;
    }

    @Override
    public void create(@NonNull Rq entity) {
        repository.save(mapper.toEntity(entity));
    }

    @Override
    public E findById(@NonNull() ID id) {
        return repository.findById(id).orElseThrow(() -> notFoundException);
    }

    /**
     * Find the entity for the given id
     *
     * @return a responseDto.
     */
    @Override
    public Rs findResponseDtoById(@NonNull ID id) {
        return mapper.toDto(findById(id));
    }

    @Override
    public List<E> findALl() {
        return repository.findAll();
    }

    @Override
    public List<Rs> findAllResponse() {
        return toResponseList(findALl());
    }

    /**
     * Find all entities marked as not deleted
     *
     * @return a List of entities T
     */
    @Override
    public List<E> findAllNotDeleted() {
        return repository.findAllByDeletedIsFalse();
    }

    /**
     * Find all entities response marked as not deleted
     *
     * @return a List of dto responses for entity T
     */
    @Override
    public List<Rs> findAllResponseNotDeleted() {
        return toResponseList(findAllNotDeleted());
    }

    @Override
    public void update(@NonNull Rq dto, @NonNull ID id) {
        E entity = findById(id);
        mapper.updateExistingEntity(dto, entity);
        repository.save(entity);
    }

    @Override
    public void deleteById(@NonNull ID id) {
        if (existById(id)) {
            repository.deleteById(id);
        } else {
            throw notFoundException;
        }
    }

    /**
     * Mark an entity as deleted to implement soft delete
     */
    @Override
    public void softDeleteById(@NonNull ID id) {
        E entity = findById(id);
        entity.setDeleted(true);
        repository.save(entity);
    }

    @Override
    public boolean existById(@NonNull ID id) {
        return repository.existsById(id);
    }

    protected List<Rs> toResponseList(List<E> list) {
        return list.stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }


}
