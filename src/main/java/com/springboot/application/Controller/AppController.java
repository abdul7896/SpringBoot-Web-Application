package com.springboot.application.Controller;

import com.springboot.application.Model.Product;
import com.springboot.application.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class AppController {

    @Autowired
    private ProductService service;

    @GetMapping("/")
    public String viewHomePage(Model model) {
        List<Product> listProducts = service.listAll();
        model.addAttribute("listProducts", listProducts);
        return "index";
    }

    @GetMapping("/new")
    public String showNewProductForm(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        return "new_product";
    }

    @PostMapping(value = "/save")
    public String saveProduct(@ModelAttribute("product") Product product) {
        service.save(product);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showEditProductForm(@PathVariable(name = "id") Long id) {
        ModelAndView modelAndView = new ModelAndView("edit_product");
        Product product = service.get(id);
        modelAndView.addObject("product", product);
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable(name = "id") Long id){
        service.delete(id);
        return "redirect:/";
    }

}
