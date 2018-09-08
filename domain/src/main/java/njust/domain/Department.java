package njust.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "department")
@Data
public class Department {

    @Id
    @GenericGenerator(name = "increment", strategy = "increment")
    @GeneratedValue(generator = "increment")
    private Integer depId;
    private String depName;

    @OneToMany(mappedBy = "department")
    private Set<User> users;

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinTable(name = "department_course",
            joinColumns = {@JoinColumn(name = "depId", referencedColumnName = "depId")},
            inverseJoinColumns = {@JoinColumn(name = "courseId", referencedColumnName ="courseId")})
    private Set<Course> courses;
}
