package LeonLederhos.improvements.service.imp;


import LeonLederhos.improvements.model.entity.BasicEntity;
import LeonLederhos.improvements.model.mapper.GMapper;
import LeonLederhos.improvements.repository.GRepository;
import LeonLederhos.improvements.service.IService;
import lombok.Getter;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Getter
public abstract class GService<E extends BasicEntity, ID, Rq, FilRq, Rs, CollRs> implements IService<E, ID, Rq, FilRq, Rs, CollRs> {

    private final GRepository<E, ID> repository;
    private final GMapper<E, Rq, Rs, CollRs> mapper;
    private final RuntimeException notFoundException;

    public GService(GRepository<E, ID> repository, GMapper<E, Rq, Rs, CollRs> mapper, RuntimeException notFoundException) {
        this.repository = repository;
        this.mapper = mapper;
        this.notFoundException = notFoundException;
    }

    @Override
    public Rs create(Rq entity) {
        return mapper.toDto(repository.save(mapper.toEntity(entity)));
    }

    @Override
    public E findById(ID id) {
        return repository.findById(id).orElseThrow(() -> notFoundException);
    }

    @Override
    public Rs findResponseDtoById(ID id) {
        return mapper.toDto(findById(id));
    }

    @Override
    public Page<E> findAll(int page, int size) {
        return repository.findAll((PageRequest.of(page, size)));
    }

    @Override
    public Page<E> findAllNotDeleted(int page, int size) {
        return repository.findAllByDeletedIsFalse(
                PageRequest.of(page, size));
    }

    @Override
    public Page<Rs> findAllRs(int page, int size) {
        return toResponseList(findAll(page, size));
    }

    @Override
    public Page<Rs> findAllRsNotDeleted(int page, int size) {
        return toResponseList(findAllNotDeleted(page, size));
    }

    @Override
    public abstract Page<Rs> findRsByFilter(FilRq filterRequest, int page, int size);

    @Override
    public List<CollRs> findAllCollRs(int page, int size) {
        return toCollectionResponseList(findAll(page, size));
    }

    @Override
    public List<CollRs> findAllCollRsNotDeleted(int page, int size) {
        return toCollectionResponseList(findAllNotDeleted(page, size));
    }

    @Override
    public abstract List<CollRs> findCollRsByFilter(FilRq filterRequest, int page, int size);

    @Override
    public Rs update(@NonNull Rq dto, @NonNull ID id) {
        E entity = findById(id);
        mapper.updateExistingEntity(dto, entity);
        return mapper.toDto(repository.save(entity));
    }

    @Override
    public void deleteById(ID id) {
        if (existsById(id)) {
            repository.deleteById(id);
        } else {
            throw notFoundException;
        }
    }

    @Override
    public void softDeleteById(ID id) {
        E entity = findById(id);
        entity.setDeleted(true);
        repository.save(entity);
    }

    @Override
    public boolean existsById(ID id) {
        return repository.existsById(id);
    }


    protected Page<Rs> toResponseList(Page<E> page) {
        List<Rs> rsList = page.stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());

        return new PageImpl<>(rsList, PageRequest.of(page.getNumber(), page.getSize()), page.getTotalElements());
    }

    protected List<CollRs> toCollectionResponseList(Page<E> page) {
        return page.stream()
                .map(mapper::toCollectionResponse)
                .collect(Collectors.toList());
    }
}
