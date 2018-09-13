package njust.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Table(name = "admin")
@Entity
@Data
public class Administrator {

    @Id
    @GenericGenerator(name = "increment", strategy = "increment")
    @GeneratedValue(generator = "increment")
    private Integer adminId;

    @OneToOne
    @JoinColumn(name = "accountId")
    private Account account;
}
