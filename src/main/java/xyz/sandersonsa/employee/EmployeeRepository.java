package xyz.sandersonsa.employee;


import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {

}
