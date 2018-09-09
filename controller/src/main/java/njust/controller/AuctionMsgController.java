package njust.controller;

import io.swagger.annotations.Api;
import njust.service.AuctionMsgService;
import org.springframework.beans.factory.annotation.Autowired;

@Api(description = "交易业务")
public class AuctionMsgController {

    private AuctionMsgService auctionMsgService;

    @Autowired
    public void setAuctionMsgService(AuctionMsgService auctionMsgService) {
        this.auctionMsgService = auctionMsgService;
    }
}
