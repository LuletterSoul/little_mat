package njust.service.impl;

import njust.dao.DownloadRecordJpaDao;
import njust.dao.ResourceJpaDao;
import njust.dao.UserJpaDao;
import njust.domain.DownloadRecord;
import njust.service.DownloadRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DownloadRecordServiceImpl implements DownloadRecordService {
    private DownloadRecordJpaDao downloadRecordJpaDao;
    private UserJpaDao userJpaDao;
    private ResourceJpaDao resourceJpaDao;

    @Autowired
    public void setDownloadRecordJpaDao(DownloadRecordJpaDao downloadRecordJpaDao) {
        this.downloadRecordJpaDao = downloadRecordJpaDao;
    }

    @Autowired
    public void setUserJpaDao(UserJpaDao userJpaDao) {
        this.userJpaDao = userJpaDao;
    }

    @Autowired
    public void setResourceJpaDao(ResourceJpaDao resourceJpaDao) {
        this.resourceJpaDao = resourceJpaDao;
    }

    @Override
    public Page<DownloadRecord> findDownloadRecord(Integer userId, Integer resourceId, Pageable pageable) {
        if(userId!=null){
            return downloadRecordJpaDao.findDownloadRecordByDownloader(userJpaDao.findOne(userId),pageable);
        }else if(resourceId!=null){
            return downloadRecordJpaDao.findDownloadRecordByResource(resourceJpaDao.findOne(resourceId),pageable);
        }else{
            return downloadRecordJpaDao.findAll(pageable);
        }
    }

    @Override
    public Page<DownloadRecord> deleteDownloadRecord(Integer userId, Integer resourceId, Pageable pageable) {
        Page<DownloadRecord> downloadRecords;
        if(userId!=null){
            downloadRecords = downloadRecordJpaDao.findDownloadRecordByDownloader(userJpaDao.findOne(userId),pageable);
        } else if(resourceId!=null){
            downloadRecords = downloadRecordJpaDao.findDownloadRecordByResource(resourceJpaDao.findOne(resourceId),pageable);
        }else{
            downloadRecords = downloadRecordJpaDao.findAll(pageable);
        }
        downloadRecordJpaDao.delete(downloadRecords);
        return downloadRecords;
    }

    @Override
    public DownloadRecord deleteDownloadRecordById(Integer recordId) {
        DownloadRecord record = downloadRecordJpaDao.findOne(recordId);
        downloadRecordJpaDao.delete(recordId);
        return record;
    }
}
