package LeonLederhos.improvements.service.imp;


import LeonLederhos.improvements.model.entity.BasicEntity;
import LeonLederhos.improvements.model.mapper.GMapper;
import LeonLederhos.improvements.repository.GRepository;
import LeonLederhos.improvements.service.IService;
import lombok.Getter;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Getter
public abstract class GService<E extends BasicEntity, ID, Rq, Rs, CollRs> implements IService<E, ID, Rq, Rs> {

    private final GRepository<E, ID> repository;
    private final GMapper<E, Rq, Rs, CollRs> mapper;
    private final RuntimeException notFoundException;

    public GService(GRepository<E, ID> repository, GMapper<E, Rq, Rs, CollRs> mapper, RuntimeException notFoundException) {
        this.repository = repository;
        this.mapper = mapper;
        this.notFoundException = notFoundException;
    }




}
