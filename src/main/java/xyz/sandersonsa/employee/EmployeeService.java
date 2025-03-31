package xyz.sandersonsa.employee;

import java.util.concurrent.atomic.AtomicInteger;

import io.quarkus.scheduler.Scheduled;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EmployeeService {

    private AtomicInteger counter = new AtomicInteger();

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public int get() {  
        return counter.get();
    }

    @Scheduled(every="1s")     
    void increment() {
        int contador = counter.incrementAndGet(); 
        Employee c = new Employee();
        c.setEmployeeName("José Roberto");
        c.setCity("Madrid");
        c.setId(Long.valueOf(contador));
        employeeRepository.save(c);
    }

}
