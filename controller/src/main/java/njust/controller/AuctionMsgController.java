package njust.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import njust.domain.AuctionMsg;
import njust.service.AuctionMsgService;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;


@Api(description = "交易业务")
@RestController
@RequestMapping(value = "/auction_msgs")
public class AuctionMsgController
{

    private AuctionMsgService auctionMsgService;

    @Autowired
    public void setAuctionMsgService(AuctionMsgService auctionMsgService)
    {
        this.auctionMsgService = auctionMsgService;
    }

    @ApiOperation(value = "发布拍卖信息（测试通过）")
    @PostMapping(value = "/user/{userId}")
    public ResponseEntity<AuctionMsg> createAuctionMsg(@PathVariable("userId") Integer userId,
                                                       @RequestParam("title") String title,
                                                       @RequestParam("content") String content,
                                                       @RequestParam("price") Float price,
                                                       @RequestParam("photo")MultipartFile photo,
                                                       HttpServletRequest request){
        return new ResponseEntity<>(auctionMsgService.createAuctionMsg(userId,title,content,price,photo,request),HttpStatus.OK);
    }


    @ApiOperation(value = "上传拍卖的图片（测试通过）")
    @PostMapping(value = "/{amsgId}/photos",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Integer> uploadPhotos(@PathVariable("amsgId") Integer amsgId,
                                                   @ApiParam(value = "上传者的Id",required = true,defaultValue = "") @RequestParam("userId") Integer userId,
                                                       @RequestPart("photo")MultipartFile photo,
                                                       HttpServletRequest request){
        return new ResponseEntity<>(auctionMsgService.uploadAuctionPhoto(amsgId,photo,userId),HttpStatus.CREATED);
    }

    /**
     * @param pageable
     *            分页参数,默认20条,按asmgId排序
     * @param publisherId
     *            筛选publisherId发布的拍卖信息
     * @param status
     *            筛选状态为status的拍卖信息
     * @return
     */
    @ApiOperation(value = "获得拍卖信息列表（测试通过）")
    @GetMapping
    public ResponseEntity<Page<AuctionMsg>> findAllAuctionMsg(@PageableDefault(size = 20, sort = {
        "amsgId"}, direction = Sort.Direction.DESC) Pageable pageable,
                                                              @ApiParam(value = "发布者的id") @RequestParam(value = "publisherId", required = false) Integer publisherId,
                                                              @ApiParam(value = "消息的状态") @RequestParam(value = "status", required = false) Integer status)
    {
        return new ResponseEntity<>(auctionMsgService.findAll(pageable, publisherId, status),
            HttpStatus.OK);
    }

    @ApiOperation(value = "删除拍卖信息（测试通过）")
    @DeleteMapping(value = "/{amsgId}")
    public ResponseEntity<AuctionMsg> deleteAuctionMsg(@PathVariable("amsgId") Integer amsgId)
    {
        return new ResponseEntity<>(auctionMsgService.deleteAuctionMsg(amsgId),
            HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "更新拍卖信息（测试通过）")
    @PutMapping
    public ResponseEntity<AuctionMsg> updateAuctionMsg(@RequestParam("amsgId") Integer amsgId,
                                                       @RequestParam("title") String title,
                                                       @RequestParam("content") String content,
                                                       @RequestParam("price") Float price,
                                                       @RequestParam("photo")MultipartFile photo,
                                                       HttpServletRequest request)
    {
        return new ResponseEntity<>(auctionMsgService.updateAuctionMsg(amsgId,title,content,price,photo,request),HttpStatus.OK);
    }

    @ApiOperation(value = "标记已售出拍卖（测试通过）")
    @PatchMapping(value = "/{amsgId}")
    public ResponseEntity<AuctionMsg> markAuctionMsg(@PathVariable("amsgId") Integer amsgId)
    {
        return new ResponseEntity<>(auctionMsgService.markAuctionMsg(amsgId),HttpStatus.OK);
    }

    @ApiOperation(value = "获得某一拍卖信息（测试通过）")
    @GetMapping(value = "/{amsgId}")
    public ResponseEntity<AuctionMsg> getAuctionMsg(@PathVariable("amsgId") Integer amsgId)
    {
        return new ResponseEntity<>(auctionMsgService.findAuctionMsgById(amsgId), HttpStatus.OK);
    }
}
