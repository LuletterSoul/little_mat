package njust.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Table(name = "account")
@Entity
@Data
public class Account {
    @Id
    @GenericGenerator(name = "increment", strategy = "increment")
    @GeneratedValue(generator = "increment")
    private Integer accountId;

    private String username;
    private String password;

    @JsonIgnore
    @OneToOne(mappedBy = "account")
    private User user;

    @JsonIgnore
    @OneToOne(mappedBy = "account")
    private Administrator administrator;

}
