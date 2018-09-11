package njust.service.impl;

import njust.dao.AuctionMsgJpaDao;
import njust.domain.AuctionMsg;
import njust.service.AuctionMsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
    public Page<AuctionMsg> findAll(Pageable pageable, Integer publisherId, Integer status) {
        if(publisherId == null){
            if(status == null)status = 0;
            return auctionMsgJpaDao.findAuctionMsgByStatus(status,pageable);
        }else
        return null;
    }
}
