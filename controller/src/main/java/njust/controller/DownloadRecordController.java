package njust.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import njust.domain.DownloadRecord;
import njust.domain.Resource;
import njust.service.DownloadRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(description = "下载记录业务")
@RestController
@RequestMapping(value = "/download_records")
public class DownloadRecordController {
    private DownloadRecordService downloadRecordService;

    @Autowired
    public void setDownloadRecordService(DownloadRecordService downloadRecordService) {
        this.downloadRecordService = downloadRecordService;
    }

    @ApiOperation(value = "获取下载记录（测试通过）")
    @GetMapping
    public ResponseEntity<Page<DownloadRecord>> findDownloadedRecord(@PageableDefault(size = 20, sort = {"recordId"}, direction = Sort.Direction.DESC)Pageable pageable,
                                                                    @RequestParam(value = "userId",required = false) Integer userId,
                                                                    @RequestParam(value = "resourceId",required = false) Integer resourceId){
        return new ResponseEntity<>(downloadRecordService.findDownloadRecord(userId,resourceId,pageable),HttpStatus.OK);
    }
}
