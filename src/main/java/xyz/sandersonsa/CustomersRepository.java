package xyz.sandersonsa;


import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface CustomersRepository extends CrudRepository<Customers, Long> {

    List<Customers> findByEmail(String email);
}
