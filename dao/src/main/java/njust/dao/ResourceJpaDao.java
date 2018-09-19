package njust.dao;

import njust.domain.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ResourceJpaDao extends JpaRepository<Resource,Integer> {

    Page<Resource> findResourceByStatus(Integer status, Pageable pageable);

    Page<Resource> findResourcesByUploader(User uploader,Pageable pageable);

    Page<Resource> findResourcesByChecker(Administrator checker,Pageable pageable);

    Page<Resource> findResourcesByCourse(Course course,Pageable pageable);

    Page<Resource> findResourcesByCompetition(Competition competition,Pageable pageable);

    Page<Resource> findResourcesByNameContains(String name,Pageable pageable);

    Page<Resource> findResourcesByNameContainsAndStatus(String name,Integer status,Pageable pageable);

    List<Resource> findResourcesByNameAndUploader(String name,User user);

    Page<Resource> findResourcesByTypeAndCourse(Integer type,Course course,Pageable pageable);

}
