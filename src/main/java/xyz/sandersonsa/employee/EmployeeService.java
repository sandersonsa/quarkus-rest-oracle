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
        for (int i=0; i < 1000; i++) {            
            enviarRegistro();
        }
    }

    private void enviarRegistro() {  
        int contador = counter.incrementAndGet(); 
        System.out.println("Enviando registro: " + contador);
        Employee c = new Employee();
        c.setEmployeeName("José Roberto");
        c.setCity("Madrid");
        c.setId(Long.valueOf(contador));
        employeeRepository.save(c); 
    }

}
