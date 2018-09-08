package njust.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "course")
@Data
public class Course {

    @Id
    @GenericGenerator(name = "increment", strategy = "increment")
    @GeneratedValue(generator = "increment")
    private Integer courseId;
    private String courseName;
    private Float credit;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "courses")
    private Set<Department> departments;

    @OneToMany(mappedBy = "course")
    private Set<Resource> resources;
}
