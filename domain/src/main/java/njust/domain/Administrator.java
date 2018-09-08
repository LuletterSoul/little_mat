package njust.domain;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Table(name = "admin",schema = "")
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
