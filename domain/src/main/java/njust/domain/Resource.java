package njust.domain;


import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import lombok.Data;


@Entity
@Table(name = "resource")
@Data
public class Resource
{

    @Id
    @GenericGenerator(name = "increment", strategy = "increment")
    @GeneratedValue(generator = "increment")
    private Integer resId;

    private String name;

    private String format;

    private String path;

    private Integer type;

    private Long size;

    private Integer status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "courseId")
    private Course course;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "uploaderId")
    private User uploader;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "download_res",
            joinColumns = {@JoinColumn(name = "resId",referencedColumnName = "resId")},
            inverseJoinColumns = {@JoinColumn(name = "downloaderId",referencedColumnName = "userId")})
    private Set<User> downloaders;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "comId")
    private Competition competition;
}
