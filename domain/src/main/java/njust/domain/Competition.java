package njust.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.Objects;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "competition")
@Data
public class Competition {

    @Id
    @GenericGenerator(name = "increment", strategy = "increment")
    @GeneratedValue(generator = "increment")
    private Integer comId;
    private String comName;

    @JsonIgnore
    @OneToMany(mappedBy = "competition")
    private Set<Resource> resources;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Competition that = (Competition) o;
        return Objects.equal(comId, that.comId) &&
                Objects.equal(comName, that.comName);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(comId, comName);
    }
}
