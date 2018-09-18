package njust.domain;


import java.util.Date;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.Objects;
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


    @OneToMany(mappedBy = "auctionMsg")
    private Set<Photo> photos;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuctionMsg that = (AuctionMsg) o;
        return Objects.equal(amsgId, that.amsgId) &&
                Objects.equal(title, that.title) &&
                Objects.equal(publicDate, that.publicDate) &&
                Objects.equal(price, that.price) &&
                Objects.equal(content, that.content) &&
                Objects.equal(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(amsgId, title, publicDate, price, content, status);
    }
}
