package njust.service;


import njust.domain.AuctionMsg;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface AuctionMsgService
{
    AuctionMsg save(AuctionMsg auctionMsg);

    AuctionMsg deleteAuctionMsg(Integer auctionMsgId);

    AuctionMsg findAuctionMsgById(Integer auctionMsgId);

    Page<AuctionMsg> findAll(Pageable pageable, Integer publisherId, Integer status);
}
