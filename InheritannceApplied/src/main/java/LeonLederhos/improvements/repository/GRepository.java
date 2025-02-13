package LeonLederhos.improvements.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface GRepository<E, ID> extends JpaRepository<E, ID> {
    Page<E> findAllByDeletedIsFalse(Pageable pageable);

}
