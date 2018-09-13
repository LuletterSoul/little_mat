package njust.domain;


import com.google.common.base.Objects;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "download_record")
@Data
public class DownloadRecord {
    @Id
    @GenericGenerator(name = "increment", strategy = "increment")
    @GeneratedValue(generator = "increment")
    private Integer recordId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId")
    private User downloader;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "resId")
    private Resource resource;

    private Date downloadDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DownloadRecord that = (DownloadRecord) o;
        return Objects.equal(recordId, that.recordId) &&
                Objects.equal(downloadDate, that.downloadDate);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(recordId, downloadDate);
    }
}
