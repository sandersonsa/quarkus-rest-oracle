package xyz.sandersonsa.employee;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

// CREATE TABLE sanderson.employee (
//     employee_id number(10) NOT NULL, 
//     employee_name varchar2(50) NOT NULL, 
//     city varchar2(50)
// );

@Entity
@Table(name="sanderson.employee")
public class Employee {

    @Id
    @Column(name="employee_id")
    private Long id;
    @Column(name="employee_name")
    private String employeeName;
    @Column(name="city")
    private String city;

    public Employee() {
    }

    public Employee(Long id, String employeeName, String city) {
        this.id = id;
        this.employeeName = employeeName;
        this.city = city;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
    
}
