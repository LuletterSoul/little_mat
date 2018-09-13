package njust.domain;


import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.Objects;
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

    /**
     * 审核改资料的管理员
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "checkerId")
    private Administrator checker;

/*    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "download_res",
            joinColumns = {@JoinColumn(name = "resId",referencedColumnName = "resId")},
            inverseJoinColumns = {@JoinColumn(name = "downloaderId",referencedColumnName = "userId")})
    private Set<User> downloaders;*/

    @JsonIgnore
    @OneToMany(mappedBy = "resource")
    private Set<DownloadRecord> downloadRecords;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "comId")
    private Competition competition;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Resource resource = (Resource) o;
        return Objects.equal(resId, resource.resId) &&
                Objects.equal(name, resource.name) &&
                Objects.equal(format, resource.format) &&
                Objects.equal(path, resource.path) &&
                Objects.equal(type, resource.type) &&
                Objects.equal(size, resource.size) &&
                Objects.equal(status, resource.status);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(resId, name, format, path, type, size, status);
    }
}
