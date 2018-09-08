package njust.domain;


import java.util.Date;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import lombok.Data;


@Entity
@Table(name = "auction_msg")
@Data
public class AuctionMsg
{

    @Id
    @GenericGenerator(name = "increment", strategy = "increment")
    @GeneratedValue(generator = "increment")
    private Integer amsgId;

    private String title;

    private Date publicDate;

    private Float price;

    private String content;

    // private Set<String> photos;
    private Integer status;

    /**
     * 信息发布者
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "publisherId")
    private User publisher;

    @JsonIgnore
    @OneToMany(mappedBy = "auctionMsg")
    private Set<Photo> photos;
}
