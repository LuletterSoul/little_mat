package njust.domain;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

@Table(name = "user",schema = "")
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

    @OneToMany(mappedBy = "uploader")
    private Set<Resource> uploadRes;

    @ManyToMany(cascade = CascadeType.PERSIST,fetch = FetchType.LAZY)
    @JoinTable(name = "download_res",
            joinColumns = {@JoinColumn(name = "userId",referencedColumnName = "userId")},
            inverseJoinColumns = {@JoinColumn(name = "resourceId",referencedColumnName = "resourceId")})
    private Set<Resource> downloadRes;

    @OneToMany(mappedBy = "user")
    private Set<AuctionMsg> auctions;
}
