package njust.domain;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "resource")
@Data
public class Resource {

    @Id
    @GenericGenerator(name = "increment", strategy = "increment")
    @GeneratedValue(generator = "increment")
    private Integer resourceId;
    private String name;
    private String format;
    private String path;
    private String type;
    private Integer size;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "courseId")
    private Course course;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "uploader")
    private User uploader;

    @ManyToMany(fetch = FetchType.LAZY,mappedBy = "downloadRes")
    private Set<User> downloaders;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "comId")
    private Competition competition;
}
