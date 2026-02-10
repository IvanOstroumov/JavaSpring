package ch.samt.esercizio2.controller;

import ch.samt.esercizio2.model.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class ProductController {
    private static List<Product> products = new ArrayList<Product>(
            Arrays.asList(
                    new Product(1, "Sidon", 7, LocalDate.now().plusDays(7)),
                    new Product(2, "mr Sigma", 67, LocalDate.now().plusDays(14)),
                    new Product(3, "LotM", 723, LocalDate.now().plusDays(198)),
                    new Product(4, "Zaratul Raul Genasci", 7, LocalDate.now().plusDays(72)),
                    new Product(5, "agent SkiBIdi", 72, LocalDate.now().plusDays(27)),
                    new Product(6, "Saint Skibidix", 31, LocalDate.now().plusDays(723))
            ));
    @GetMapping("/products")
    public String products(Model model){
        model.addAttribute("products", products);
        return "products";
    }

    @GetMapping("/newproduct")
    public String showNewProduct(Model model){
        model.addAttribute("product", new Product());
        return "newproduct";
    }

    @PostMapping("/newproduct")
    public String newProduct(Product product){
        products.add(product);
        return "redirect:/products";
    }
}