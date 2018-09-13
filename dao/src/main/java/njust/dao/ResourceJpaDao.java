package njust.dao;

import njust.domain.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ResourceJpaDao extends JpaRepository<Resource,Integer> {

    Page<Resource> findResourceByStatus(Integer status, Pageable pageable);

    Page<Resource> findResourcesByUploader(User uploader,Pageable pageable);

    Page<Resource> findResourcesByChecker(Administrator checker,Pageable pageable);

    Page<Resource> findResourcesByCourse(Course course,Pageable pageable);

    Page<Resource> findResourcesByCompetition(Competition competition,Pageable pageable);

}
