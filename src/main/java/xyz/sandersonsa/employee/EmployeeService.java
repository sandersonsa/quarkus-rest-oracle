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

    @Scheduled(every="60s")     
    void enviarRegistroTimer() {
        int contador = counter.incrementAndGet(); 
        for (int i=0; i < 1000; i++) {
            System.out.println("Enviando registro: " + contador);
            enviarRegistro(contador);
        }
    }

    private void enviarRegistro(Integer id) {  
        Employee c = new Employee();
        c.setEmployeeName("José Roberto");
        c.setCity("Madrid");
        c.setId(Long.valueOf(id));
        employeeRepository.save(c); 
    }

}
