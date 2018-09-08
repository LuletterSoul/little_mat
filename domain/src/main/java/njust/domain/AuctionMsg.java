package njust.domain;


import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "auction_msg")
@Data
public class AuctionMsg {

    @Id
    @GenericGenerator(name = "increment", strategy = "increment")
    @GeneratedValue(generator = "increment")
    private Integer amsgId;

    private String title;
    private Date publicDate;
    private Float price;
    private String content;
//    private Set<String> photos;
    private Integer statu;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId")
    private User user;

    @OneToMany(mappedBy = "auctionMsg")
    private Set<Photo> photos;
}
