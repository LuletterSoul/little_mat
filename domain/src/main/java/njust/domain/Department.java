package njust.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.Objects;
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

    @JsonIgnore
    @OneToMany(mappedBy = "department")
    private Set<User> users;


    @JsonIgnore
    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinTable(name = "department_course",
            joinColumns = {@JoinColumn(name = "depId", referencedColumnName = "depId")},
            inverseJoinColumns = {@JoinColumn(name = "courseId", referencedColumnName ="courseId")})
    private Set<Course> courses;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Department that = (Department) o;
        return Objects.equal(depId, that.depId) &&
                Objects.equal(depName, that.depName);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(depId, depName);
    }
}
