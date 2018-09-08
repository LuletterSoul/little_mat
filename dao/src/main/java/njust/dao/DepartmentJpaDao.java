package njust.dao;

import njust.domain.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentJpaDao extends JpaRepository<Department,Integer> {
}
