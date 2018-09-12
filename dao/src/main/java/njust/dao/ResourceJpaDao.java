package njust.dao;

import njust.domain.Resource;
import njust.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ResourceJpaDao extends JpaRepository<Resource,Integer> {

    @Query("select r from Resource r where r.status =:status order by ?#{#pageable}")
    Page<Resource> findResourceByStatus(@Param("status") Integer status, Pageable pageable);

    Page<Resource> findResourcesByUploader(User uploader,Pageable pageable);

    @Query(value = "select * from resource where resId in (select resId from download_res where downloaderId =?1 order by #{#pageable})",nativeQuery = true)
    Page<Resource> findDownloadResource(Integer useId,Pageable pageable);
}
