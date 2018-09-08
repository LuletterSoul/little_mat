package njust.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Data
public class Administrator extends Account {

    @Column(unique = true)
    private Integer adminId;
}
