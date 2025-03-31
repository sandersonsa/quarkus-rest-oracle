package xyz.sandersonsa;

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

@Path("/customers")
public class CostumersResource {

    private final CustomersRepository customersRepository;

    public CostumersResource(CustomersRepository customersRepository) {
        this.customersRepository = customersRepository;
    }

    @GET
    @Produces("application/json")
    public Iterable<Customers> findAll() {
        return customersRepository.findAll();
    }

    @DELETE
    @Path("{id}")
    public void delete(long id) {
        customersRepository.deleteById(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces("application/json")
    @Transactional
    public Customers create(Customers customers) {
        return customersRepository.save(customers);
    }

    @PUT
    @Path("/{id}")
    @Produces("application/json")
    @Consumes(MediaType.APPLICATION_JSON)
    public Customers changeColor(@PathParam("id") Long id, Customers customers) {
        Optional<Customers> optional = customersRepository.findById(id);
        if (optional.isPresent()) {
            Customers c = optional.get();
            c.setEmail(customers.getEmail());
            c.setFirstName(customers.getFirstName());
            c.setLastName(customers.getLastName());
            return customersRepository.save(c);
        }
        throw new IllegalArgumentException("No Customers with id " + id + " exists");
    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Customers findByColor(@PathParam("id") Long id) {
        Optional<Customers> optional = customersRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        throw new IllegalArgumentException("No Customers with id " + id + " exists");
    }
}