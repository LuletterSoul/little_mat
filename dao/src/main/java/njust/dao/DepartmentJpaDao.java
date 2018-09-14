package njust.dao;

import njust.domain.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DepartmentJpaDao extends JpaRepository<Department,Integer> {

}
