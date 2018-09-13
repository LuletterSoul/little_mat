package njust.service;


import njust.domain.AuctionMsg;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;


public interface AuctionMsgService
{
    AuctionMsg save(AuctionMsg auctionMsg);

    AuctionMsg deleteAuctionMsg(Integer auctionMsgId);

    AuctionMsg findAuctionMsgById(Integer auctionMsgId);

    Page<AuctionMsg> findAll(Pageable pageable, Integer publisherId, Integer status);

    AuctionMsg updateAuctionMsg(AuctionMsg auctionMsg);

    AuctionMsg updateAuctionMsg(Integer amsgId, String title, String content, Float price, MultipartFile photo, HttpServletRequest request);

    AuctionMsg createAuctionMsg(Integer userId, AuctionMsg auctionMsg, MultipartFile[] photos, HttpServletRequest request);

    AuctionMsg createAuctionMsg(Integer userId, String title, String content, Float price, MultipartFile photo, HttpServletRequest request);

    AuctionMsg markAuctionMsg(Integer amsgId);
}
