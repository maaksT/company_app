package pl.makstokarz.company_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.makstokarz.company_app.model.Employee;

public interface EmployeeRepo extends JpaRepository<Employee,Long> {
}
