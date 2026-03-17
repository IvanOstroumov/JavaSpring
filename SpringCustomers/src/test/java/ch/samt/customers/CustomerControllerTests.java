package ch.samt.customers;

import ch.samt.customers.data.CustomerRepository;
import ch.samt.customers.domain.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
@Transactional
public class CustomerControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void testLoadCustomers() throws Exception {
        mockMvc.perform(get("/customers"))
                .andExpect(status().isOk())
                .andExpect(view().name("customerList"))  // Verifica che la vista sia "customerList"
                .andExpect(model().attribute("customers", customerRepository.findAll()))  // Verifica che i clienti contenuti in "customers" siano esattamente quelli della chiamata "customerRepository.findAll()"
                .andExpect(model().attribute("customers", hasSize(3)))  // Verifica il numero di clienti
                .andExpect(model().attribute("customers", hasItem(hasProperty("name", is("Quan")))));  // Verifica che ci sia un cliente di nome "Mario"
    }

    @Test
    public void testLoadInsertPage() throws Exception {
        mockMvc.perform(get("/customers/insert"))
                .andExpect(status().isOk())  // Verifica che lo stato sia OK (200)
                .andExpect(view().name("insertCustomer"))  // Verifica che la vista sia "insertCustomer"
                .andExpect(model().attributeExists("customer"));  // Verifica che il modello contenga un attributo "customer"
    }

    @Test
    public void testSaveCustomer_Success() throws Exception {
        // Testa la POST con dati validi
        mockMvc.perform(post("/customers/insert")
                        .param("name", "WIBIBI")
                        .param("surname", "Tranitas")
                        .param("age", "20")
                        .param("city", "Arbedo-Castione")
                        .param("ccnumber", "4293850656112576")
                        .param("ccexpiration", "05/30")
                        .param("cccvv", "232"))
                .andExpect(status().is3xxRedirection())  // Verifica che ci sia una redirezione come implementato nel metodo => return "redirect:/customers"
                .andExpect(redirectedUrl("/customers"));  // Verifica la redirezione a /customers

        // Verifica che Bartali sia ora presente in DB
        Customer savedCustomer = customerRepository.findBySurnameIgnoreCase("Tranitas").get(0);
        assert savedCustomer != null;
        assert savedCustomer.getName().equals("WIBIBI");
        assert savedCustomer.getAge().equals(20);
    }

    @Test
    public void testSaveCustomer_WithErrors() throws Exception {
        // Testa il funzionamento della validation (manca l'età del cliente e quindi il tentativo di save non andrà a buon fine)
        mockMvc.perform(post("/customers/insert")
                        .param("name", "Fausto")
                        .param("surname", "Coppi"))
                .andExpect(status().isOk())  // Lo stato è OK, ma non c'è redirezione
                .andExpect(view().name("insertCustomer"))  // La vista restituita è di nuovo il form
                .andExpect(model().attributeHasFieldErrors("customer", "age"));  // Verifica l'errore sul campo "age"

        // Verifica che Fausto Coppi NON sia stato inserito in DB
        assert customerRepository.findBySurnameIgnoreCase("Coppi").isEmpty();
        //  Provare a commentare la validation sul campo "age" e verificare che questo test fallisce
    }

    @Test
    public void testLoadCustomersBySurname() throws Exception {
        mockMvc.perform(get("/customers/Tran"))
                .andExpect(status().isOk())  // Verifica che lo stato sia OK (200)
                .andExpect(view().name("customerList"))  // Verifica che la vista sia "customerList"
                .andExpect(model().attributeExists("customers"))  // Verifica che il modello contenga "customers"
                .andExpect(model().attribute("customers", customerRepository.findBySurnameIgnoreCase("Tran")));  // Verifica che "customer" contenga il risultato della query
    }

    @Test
    public void testLoadCustomersByCity() throws Exception {
        mockMvc.perform(get("/customers/customersbycity?city=Arbedo-Castione"))
                .andExpect(status().isOk())
                .andExpect(view().name("customerList"))
                .andExpect(model().attributeExists("customers"))
                .andExpect(model().attribute("customers", customerRepository.findBySurnameIgnoreCase("Tran")));
    }
}