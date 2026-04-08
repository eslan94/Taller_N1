package com.desarrollo.videojuegos.controllers;

import com.desarrollo.videojuegos.models.Product;
import com.desarrollo.videojuegos.services.CategoryService;
import com.desarrollo.videojuegos.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.stream.Collectors;

@Controller
public class ProductController {

    @Autowired
    CategoryService categoryService;

    @Autowired
    ProductService productService;


    @GetMapping("/")
    public String home(Model model, Authentication authentication){
        String role = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .filter(auth -> auth.startsWith("ROLE_"))
                .collect(Collectors.joining(","));
        //System.out.println(role);
        model.addAttribute("role", role);
        model.addAttribute("products", productService.getAllProducts());
        return "product/home";
    }

    @GetMapping("/add-game")
    public String getFormProduct(Model model){
        model.addAttribute("product", new Product());
        model.addAttribute("categories", categoryService.getAllCategories());
        return "product/add-game";
    }

    @PostMapping("/add-game")
    public String addProduct(@ModelAttribute Product product){
        productService.saveProduct(product);
        return "redirect:/";
    }

    @GetMapping("/product/edit/{id}")
    public String getFormProductEdit(@PathVariable Long id, Model model){
        Product product = productService.getProductById(id).orElseThrow();
        model.addAttribute("product", product);
        model.addAttribute("categories", categoryService.getAllCategories());
        return "product/add-game";
    }

    @PostMapping("/product/edit/{id}")
    public String editProduct(@PathVariable Long id, @ModelAttribute Product product){
        product.setId(id);
        productService.saveProduct(product);
        return "redirect:/";
    }

    @PostMapping("/product/delete/{id}")
    public String deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
        return "redirect:/";
    }

}
