package njust.service;

import njust.domain.DownloadRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DownloadRecordService {
    Page<DownloadRecord> findDownloadRecord(Integer userId, Integer resourceId, Pageable pageable);
}
