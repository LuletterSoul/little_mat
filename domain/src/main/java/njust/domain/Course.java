package njust.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.Objects;
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

    @JsonIgnore
    @OneToMany(mappedBy = "course")
    private Set<Resource> resources;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return Objects.equal(courseId, course.courseId) &&
                Objects.equal(courseName, course.courseName) &&
                Objects.equal(credit, course.credit);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(courseId, courseName, credit);
    }
}
