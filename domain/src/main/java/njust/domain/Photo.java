package njust.domain;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "photo")
@Data
public class Photo {

    @Id
    @GenericGenerator(name = "increment", strategy = "increment")
    @GeneratedValue(generator = "increment")
    private Integer photoId;
    private String photoPath;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="amsgId")
    private AuctionMsg auctionMsg;
}
