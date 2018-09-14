package njust.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.Objects;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

@Table(name = "user")
@Entity
@Data
public class User{

    @Id
    @GenericGenerator(name = "increment", strategy = "increment")
    @GeneratedValue(generator = "increment")
    private Integer userId;

    private String name;
    private String gender;
    private String email;
    private String phone;
    private String grade;
    private String major;


    @OneToOne
    @JoinColumn(name = "accountId")
    private Account account;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="depId")
    private Department department;

    @JsonIgnore
    @OneToMany(mappedBy = "uploader")
    private Set<Resource> uploadRes;


    @JsonIgnore
    @OneToMany(mappedBy = "publisher")
    private Set<AuctionMsg> auctions;

    @JsonIgnore
    @OneToMany(mappedBy = "downloader")
    private Set<DownloadRecord> downloadRecords;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equal(userId, user.userId) &&
                Objects.equal(name, user.name) &&
                Objects.equal(gender, user.gender) &&
                Objects.equal(email, user.email) &&
                Objects.equal(phone, user.phone) &&
                Objects.equal(grade, user.grade) &&
                Objects.equal(major, user.major);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(userId, name, gender, email, phone, grade, major);
    }
}
