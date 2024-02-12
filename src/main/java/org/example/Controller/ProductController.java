package org.example.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.Javalin;
import org.example.Exception.ProductException;
import org.example.Model.Product;
import org.example.Model.Seller;
import org.example.Service.ProductService;
import org.example.Service.SellerService;

import java.util.List;

/*
every class has a default constructor UNTIL you write one yourself.
this Controller package is similar to the CLIParser in our first project.
 */
public class ProductController {

    /*
    this controller class sits on a higher level than the ProductService.  So ProductService will be a dependency
    that's instantiated within the constructor of the ProductController.
    we put this here because we want to use ProductService across this entire class, so we can use this in
    all the methods of this class
     */

    //lambda expressions for the endpoints
    ProductService productService;
    SellerService sellerService;


    //defining a constructor for a class called ProductController, which takes 2 parameters:
    //productService (a ProductService object) and sellerService (a SellerService object)
    //Inside the constructor, these passed in objects are assigned to fields in the ProductController
    // instance.  The constructor accepts a ProductService and SellerService
    //It assigns these to fields in the class
    //This allows the controller to use the passed in service objects
    public ProductController(ProductService productService, SellerService sellerService) {
        this.productService = productService;
        this.sellerService = sellerService;
    }

    /*
    we will use this method to create and configure a Javalin api.
    We will define endpoints pertaining to a specific HTTP verb (eg "get", "post") with app.get,
    app.post, etc.
    Every endpoint will contain a URI (Uniform Resource Identifier) - health in <app.get("health"...> below
    as well as a lambda expression informing Javalin how to interact with the Context.  The Context
    is a Javalin object that managers both the HTTP request and response.  For example if we need any information
    about what the incoming requests look like we can get that by Context.
     */

    public Javalin getAPI() {
        Javalin api = Javalin.create();

        //.get is a method - "health" is an endpoint that checks to see if the server is "up" or not
        api.get( "health", context -> {context.result("The server is UP");
        } );

        // we'll want a get and a post for both product and seller - plus exception handling
        api.get( "Seller", context -> {
            List<Seller> sellerList = sellerService.getSellerList();
            context.json(sellerList);
        } );

        api.get("Product", context -> {
            List<Product> productList = productService.getProductList();
            context.json(productList) ;
        } );

        api.post("Seller", context -> {
            try {
                ObjectMapper om = new ObjectMapper();
                Seller s = om.readValue(context.body(), Seller.class);
                sellerService.addSeller(s);
                context.status(201);  // resource created
            }catch (JsonProcessingException e) {
                e.printStackTrace();
                context.status(400);  // bad request
            }
        } );

        api.post("Product", context -> {
            try {
                ObjectMapper om = new ObjectMapper();
                Product p = om.readValue(context.body(), Product.class);
                Product newProduct = productService.addProduct(p);
                context.status(201);  // resource created
                context.json(newProduct);
            }catch (JsonProcessingException e) {
                context.result(e.getMessage());
                context.status(400);  // bad request
            }catch(ProductException e) {  // this piece of the catch did not work
                context.result(e.getMessage());
                context.status(400);
                // need to add a catch for the seller doesn't match an existing seller piece.
            }
        });

        api.get("Product/{id}", context -> {
            long id = Long.parseLong(context.pathParam("id"));
            Product p = productService.getProductById(id);
            if(p == null) {
                context.status(404); //if the product id was NOT found, return 404
                context.result("The product ID entered was not found.");
            }else {
                context.json(p);
                context.status(200); // if product id WAS found, return 200
            }
        });

//NOTE:  type delete code here
        api.delete("Product/{id}", context -> {
            long id = Long.parseLong(context.pathParam("id"));
            Product p = productService.getProductById(id);
            if(p == null) {
                context.status(404); //if the product id was NOT found, return 404
                context.result("The product ID entered was not found.");
            }else {
                productService.deleteProduct(id);
                context.json(p);
                context.status(200);
            }
        });

        return api;

    }
}
