package org.example.Service;

import org.example.Exception.ProductException;
import org.example.Model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductService {
    SellerService sellerService;
    List<Product> productList;

    //Dependency injection - which injects the SellerService into the ProductService
    public ProductService(SellerService sellerService){
        this.sellerService = sellerService;
        productList = new ArrayList<>();
    }

    public List<Product> getProductList() {
        return productList;
    }

    public Product addProduct(Product p) throws ProductException {
        if(p.getProductName() == null || p.getSellerName() == null){
            throw new ProductException("Product and Seller name fields cannot be null");
        }

        // here we wanted to use a long random number for the product ID, but Math.random returns a
        // double so we had to "cast" this to a "long".
        long id = (long) (Math.random() * Long.MAX_VALUE);
        p.setProductID(id);
        productList.add(p);
        return p;

    }

    public Product getProductById(Long id) {
        for(int i = 0; i < productList.size(); i++) {
            Product currentProduct = productList.get(i);
            if(currentProduct.getProductID() == id) {
                return currentProduct;
            }
        }
        return null;
    }

}
