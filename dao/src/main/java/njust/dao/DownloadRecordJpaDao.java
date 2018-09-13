package njust.dao;

import njust.domain.DownloadRecord;
import njust.domain.Resource;
import njust.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DownloadRecordJpaDao  extends JpaRepository<DownloadRecord,Integer> {
    Page<DownloadRecord> findDownloadRecordByDownloader(User downloader, Pageable pageable);
    Page<DownloadRecord> findDownloadRecordByResource(Resource resource,Pageable pageable);
}
