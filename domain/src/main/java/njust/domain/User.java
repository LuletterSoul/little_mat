package njust.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
public class User extends Account{

    @Column(unique = true)
    private Integer userId;

    private String name;
    private String gender;
    private String email;
    private String phone;
    private String grade;
    private String major;

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
