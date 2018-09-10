package njust.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import njust.domain.AuctionMsg;
import njust.service.AuctionMsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(description = "交易业务")
@RestController
@RequestMapping(value = "/auction_msg")
public class AuctionMsgController {

    private AuctionMsgService auctionMsgService;

    @Autowired
    public void setAuctionMsgService(AuctionMsgService auctionMsgService) {
        this.auctionMsgService = auctionMsgService;
    }

    @ApiOperation(value = "获得拍卖信息列表")
    @GetMapping
    public ResponseEntity<List<AuctionMsg>> findAllAuctionMsg(){
        return new ResponseEntity<>(auctionMsgService.findAll(),HttpStatus.OK);
    }

    @ApiOperation(value = "删除拍卖信息")
    @DeleteMapping(value = "/{amsgId}")
    public ResponseEntity<AuctionMsg> deleteAuctionMsg(@PathVariable("amsgId") Integer amsgId){
        return new ResponseEntity<>(auctionMsgService.deleteAuctionMsg(amsgId),HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "更新拍卖信息")
    @PatchMapping(value = "/{amsgId}")
    public ResponseEntity<AuctionMsg> updateAuctionMsg(@PathVariable("amsgId") Integer amsgId,
                                                       @RequestBody AuctionMsg auctionMsg){
        return null;
    }

    @ApiOperation(value = "获得某一拍卖信息")
    @DeleteMapping(value = "/{amsgId}")
    public ResponseEntity<AuctionMsg> getAuctionMsg(@PathVariable("amsgId") Integer amsgId){
        return new ResponseEntity<>(auctionMsgService.findAuctionMsgById(amsgId),HttpStatus.OK);
    }
}
