package cg.service;

import cg.exception.DuplicateValueException;
import cg.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ProductService {
    private static final ArrayList<Product> products;

    static {
        products = new ArrayList<>();
        products.add(new Product("A", 1000, "a"));
    }

    public void save(Product product){
        products.add(product);
    }

    public void save() throws DuplicateValueException{
        throw new DuplicateValueException();
    }

    public void findAll() throws Exception{
        if (true) throw new Exception("HelloWorld-C1021H1");
    }

    public void findOne(int id) throws Exception {
        if (true) throw new Exception("HelloWorld-C1021H1-FindOne");
    }

}
