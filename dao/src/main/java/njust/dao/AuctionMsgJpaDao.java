package njust.dao;

import njust.domain.AuctionMsg;
import njust.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AuctionMsgJpaDao extends JpaRepository<AuctionMsg,Integer> {

//    @Query(value="select a from AuctionMsg a where a.status=:status order by ?#{pageable}")
    Page<AuctionMsg> findAuctionMsgByStatus(Integer status, Pageable pageable);

//    @Query(value="select a from AuctionMsg a where a.publisher=:publisher and a.status=:status order by ?#{pageable}")
    Page<AuctionMsg> findAuctionMsgByStatusAndPublisher(Integer status, User publisher, Pageable pageable);

//    @Query(value = "select a from AuctionMsg a where a.publisher =:publisher order by ?#{pageable}")
    Page<AuctionMsg> findAuctionMsgByPublisher(User publisher, Pageable pageable);
}
