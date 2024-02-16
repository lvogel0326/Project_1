package org.example;

import Utility.ConnectionSingleton;
import io.javalin.Javalin;
import org.example.Controller.ProductController;
import org.example.DOA.ProductDAO;
import org.example.DOA.SellerDAO;
import org.example.Service.ProductService;
import org.example.Service.SellerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;

/*
We run the main method, which instantiates the ProductController, which in turn instantiates the
ProductService.  Then we start the api.

The dependant classes exist to provide a clean flow of where our programming logic goes.
Any incoming/outgoing information to the app goes thru the controller.  We won't access the
ProductService at any time in this Main Method.

This separation of concerns leads to cleaner code in the long run.  However, this does mean
that some of the operations that happen in different layers of the program will be invisible
to us when we're running the program - testing, exceptions and debugging are how we access those
inner layers.

The Controller class will be making all the method identifications belonging to the ProductService.

Here in Main we are instantiated the ProductController we created.  Because ProductController has the
ProductService instantiated there, main can pick up everything we've created.
 */
public class Main {

    public static Logger log = LoggerFactory.getLogger(Main.class);
    public static void main(String[] args) {
        Connection conn = ConnectionSingleton.getConnection();
        SellerDAO sellerDAO = new SellerDAO(conn);
        ProductDAO productDAO = new ProductDAO(conn);
        SellerService sellerService = new SellerService(sellerDAO);
        ProductService productService = new ProductService(productDAO);
        ProductController productController = new ProductController(productService, sellerService);

        Javalin api = productController.getAPI();

        api.start(9002);

        // **** TED GOT RID OF EVERYTHING IN HIS 2/13 DEMO
        // **** COMMENTING THIS OUR FOR NOW
      /*  //creating the ProductController
        SellerService sellerService = new SellerService();
        ProductService productService = new ProductService(sellerService);
        ProductController productController = new ProductController(productService, sellerService);

        //'ProductController(org.example.Service.ProductService, org.example.Service.SellerService)'
        // in 'org.example.Controller.ProductController' cannot be applied to
        // '(org.example.Service.SellerService, org.example.Service.ProductService)'


        //take in the Javalin object that's created when we configure the API
        Javalin api = productController.getAPI();
        api.start(9002);*/

    }
}