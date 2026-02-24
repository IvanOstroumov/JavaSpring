package ch.samt.esercizio2.controller;

import ch.samt.esercizio2.model.Product;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ProductController {
    private static HashMap<Integer, Product> products;

    static {
        products = new HashMap<>();
        products.put(1, new Product(1, "Sidon", 7, LocalDate.now().plusDays(7), "Gioca a calcio da 12 anni, 10 anni di sci"));
        products.put(2, new Product(2, "mr Sigma", 67, LocalDate.now().plusDays(14), "Non ha bisogno di descrizione"));
        products.put(3, new Product(3, "LotM", 723, LocalDate.now().plusDays(198), "Figata assurda"));
        products.put(4, new Product(4, "Zheng Tran", 7, LocalDate.now().plusDays(72), "Un po skibidi"));
        products.put(5, new Product(5, "agent SkiBIdi", 72, LocalDate.now().plusDays(27), "Agente segreto"));
        products.put(6, new Product(6, "Saint Skibidix", 31, LocalDate.now().plusDays(723), "Il santo dei sigma"));


    }

    @GetMapping("/products")
    public String loadProducts(Model model,
                               @RequestParam(value = "name", required = false) String name,
                               @RequestParam(value = "pricelessthan", required = false) Double pricelessthan) {
        if (name == null || pricelessthan == null) {
            model.addAttribute("products", products.values());
        }
        if (name != null && pricelessthan == null) {
            List<Product> filtered = products.values().stream().filter(
                            product -> product.getName()
                                    .contains(name))
                    .collect(Collectors.toList());
            model.addAttribute("products", filtered);
        }
        if (name == null && pricelessthan != null) {
            List<Product> filtered = products.values().stream().filter(
                            product -> product.getPrice() < pricelessthan)
                    .collect(Collectors.toList());
            model.addAttribute("products", filtered);
        }
        return "productList";
    }

    @GetMapping("/newproduct")
    public String showNewProduct(Model model) {
        model.addAttribute("product", new Product());
        return "newproduct";
    }

    @PostMapping("/newproduct")
    public String newProduct(@Valid Product product) {
        products.put(product.getId(), product);
        return "redirect:/products";
    }

    @GetMapping("/products/show/{id}")
    public String showProduct(@PathVariable(value = "id") Long id, Model model) {
        if (id == null) {
            return "errorProduct";
        }
        Product product = products.values().stream().filter(p -> p.getId() == id).findFirst().orElse(null);
        if (product == null) {
            return "errorProduct";
        }
        model.addAttribute("products", product);
        return "productList";
    }

}