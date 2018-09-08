package njust.dao;

import njust.domain.AuctionMsg;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuctionMsgJpaDao extends JpaRepository<AuctionMsg,Integer> {
}
