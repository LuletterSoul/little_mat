package njust.service.impl;

import njust.dao.AuctionMsgJpaDao;
import njust.domain.AuctionMsg;
import njust.service.AuctionMsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuctionMsgServiceImpl implements AuctionMsgService {

    private AuctionMsgJpaDao auctionMsgJpaDao;

    @Autowired
    public void setAuctionMsgJpaDao(AuctionMsgJpaDao auctionMsgJpaDao) {
        this.auctionMsgJpaDao = auctionMsgJpaDao;
    }

    @Override
    public AuctionMsg save(AuctionMsg auctionMsg) {
        return auctionMsgJpaDao.save(auctionMsg);
    }

    @Override
    public AuctionMsg deleteAuctionMsg(Integer auctionMsgId) {
        AuctionMsg auctionMsg = auctionMsgJpaDao.findOne(auctionMsgId);
        auctionMsgJpaDao.delete(auctionMsg);
        return auctionMsg;
    }

    @Override
    public AuctionMsg findAuctionMsgById(Integer auctionMsgId) {
        return auctionMsgJpaDao.findOne(auctionMsgId);
    }

    @Override
    public List<AuctionMsg> findAll() {
        return auctionMsgJpaDao.findAll();
    }
}
