package xyz.sandersonsa.employee;

import java.util.List;
import java.util.Optional;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/employees")
public class EmployeeResource {

    private final EmployeeRepository employeeRepository;

    public EmployeeResource(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GET
    @Produces("application/json")
    public Iterable<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @DELETE
    @Path("{id}")
    public void delete(long id) {
        employeeRepository.deleteById(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces("application/json")
    @Transactional
    public Employee create(Employee employee) {
        System.out.println("Creating: " + employee);
        return employeeRepository.save(employee);
    }

    @PUT
    @Path("/{id}")
    @Produces("application/json")
    @Consumes(MediaType.APPLICATION_JSON)
    public Employee changeColor(@PathParam("id") Long id, Employee employee) {
        Optional<Employee> optional = employeeRepository.findById(id);
        if (optional.isPresent()) {
            Employee c = optional.get();
            c.setEmployeeName(employee.getEmployeeName());
            c.setCity(employee.getCity());
            return employeeRepository.save(c);
        }
        throw new IllegalArgumentException("No Customers with id " + id + " exists");
    }

}