package njust.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.Objects;
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

    /**
     * 存相对路径
     */
    private String relativePath;

    /**
     * 存绝对路径
     */
    private String photoPath;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="amsgId")
    private AuctionMsg auctionMsg;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Photo photo = (Photo) o;
        return Objects.equal(photoId, photo.photoId) &&
                Objects.equal(photoPath, photo.photoPath);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(photoId, photoPath);
    }
}
