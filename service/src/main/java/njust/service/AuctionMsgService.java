package njust.service;

import njust.domain.AuctionMsg;

import java.util.List;

public interface AuctionMsgService {
    AuctionMsg save(AuctionMsg auctionMsg);
    AuctionMsg deleteAuctionMsg(Integer auctionMsgId);
    AuctionMsg findAuctionMsgById(Integer auctionMsgId);
    List<AuctionMsg> findAll();
}
