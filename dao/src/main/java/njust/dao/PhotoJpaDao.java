package njust.dao;


import java.util.List;

import njust.domain.AuctionMsg;
import org.springframework.data.jpa.repository.JpaRepository;

import njust.domain.Photo;


public interface PhotoJpaDao extends JpaRepository<Photo, Integer>
{
    List<Photo> findPhotoByAuctionMsg(AuctionMsg msg);
}
