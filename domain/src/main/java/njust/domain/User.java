package njust.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "download_res",
            joinColumns = {@JoinColumn(name = "downloaderId",referencedColumnName = "userId")},
            inverseJoinColumns = {@JoinColumn(name = "resId",referencedColumnName = "resId")})
    private Set<Resource> downloadRes;

    @JsonIgnore
    @OneToMany(mappedBy = "publisher")
    private Set<AuctionMsg> auctions;
}
