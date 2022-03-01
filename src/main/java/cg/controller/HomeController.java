package cg.controller;

import cg.exception.DuplicateValueException;
import cg.model.Product;
import cg.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Controller
@RequestMapping("/product")
public class HomeController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ModelAndView getAll() throws Exception {
        ModelAndView modelAndView = new ModelAndView("create");
        productService.findAll();
        return modelAndView;
    }

    @GetMapping("/id")
    public ModelAndView getOne() throws Exception {
        ModelAndView modelAndView = new ModelAndView("create");
        productService.findOne(1);
        return modelAndView;
    }

    @GetMapping("/save")
    public ModelAndView save(Model model) throws DuplicateValueException {
        ModelAndView modelAndView = new ModelAndView("create");
        productService.save();
        return modelAndView;
    }


    @GetMapping("/create")
    public ModelAndView createProduct(Model model) {
        ModelAndView modelAndView = new ModelAndView("create");
        model.addAttribute("product", new Product());
        return modelAndView;
    }

    @PostMapping
    public ModelAndView create(@Validated @ModelAttribute("product") Product product, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView("create");
//        new Product().validate(product, bindingResult);
        if (bindingResult.hasFieldErrors()) {
            modelAndView.addObject("product", product);
            return modelAndView;
        } else {
//            try {
            productService.save(product);
//            } catch (DuplicateValueException e) {
//                return new ModelAndView("error");
//            }
        }
        modelAndView.addObject("message", "Create successfully!");
        return modelAndView;
    }

    @ExceptionHandler(DuplicateValueException.class)
    public ModelAndView showInputNotAcceptable() {
        return new ModelAndView("error");
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView catchException() {
        return new ModelAndView("error");
    }
}
