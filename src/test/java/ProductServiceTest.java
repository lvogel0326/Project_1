import Utility.ConnectionSingleton;
import org.example.Exception.ProductException;
import org.example.Exception.SellerException;
import org.example.Model.Product;
import org.example.Model.Seller;
import org.example.Service.ProductService;
import org.example.Service.SellerService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ProductServiceTest {
    //before each test, reset the ProductService and SellerService to a newly instantiated objects
    ProductService productService;
    SellerService sellerService;


    Seller testSeller;


    @Before
    public void setUp() throws SellerException {
        sellerService = new SellerService();
        productService = new ProductService(sellerService); //needed sellerService because of DI in ProductService?

    }

    @Test
    public void productServiceEmptyAtStart() {
        //getting a List of Product objects from a ProductService
        List<Product> productList = productService.getProductList();
        assertEquals(0, productList.size()); //this is the same thing as:  Assert.assertTrue(productList.size() == 0);
    }
// Do I really need sellerService to be empty before each test?
     @Test
     public void sellerServiceEmptyAtStart() {
        List<Seller> sellerList = sellerService.getSellerList();
         assertEquals(0, sellerList.size());
    }

    @Test
    public void testAddProduct() throws ProductException, SellerException {
        String testProductName = "lego";
        String testSellerName = "walmart";
        double  testProductPrice = 33.99;
        String testSellerName2 = "walmart";

        Product product = new Product();
        product.setProductName(testProductName);
        product.setSellerName(testSellerName);
        product.setProductPrice(testProductPrice);

        Seller seller = new Seller();
        seller.setName(testSellerName2);

        sellerService.addSeller(seller);
        productService.addProduct(product);

        assertTrue(productService.getProductList().contains(product));
    }

    @Test
    public void testSellerExistsException() throws SellerException {
        String testProductName = "ball";
        String testSellerName = "random";
        double  testProductPrice = 5.99;
        String testSellerName2 = "walmart";

        Product product = new Product();
        product.setProductName(testProductName);
        product.setSellerName(testSellerName);
        product.setProductPrice(testProductPrice);

        Seller seller = new Seller();
        seller.setName(testSellerName2);

        sellerService.addSeller(seller);

        try{
            productService.addProduct(product);
            Assert.fail("Seller name does not exist in Seller database");
        }catch (Exception e) {

        }
    }
    @Test
    public void testDeleteProduct() throws Exception {
        List<Product> productList = productService.getProductList();
        String testProductName = "doll";
        String testSellerName = "walmart";
        double  testProductPrice = 14.99;
        String testSellerName2 = "walmart";

        Product product = new Product();
        product.setProductName(testProductName);
        product.setSellerName(testSellerName);
        product.setProductPrice(testProductPrice);

        Seller seller = new Seller();
        seller.setName(testSellerName2);

        sellerService.addSeller(seller);

        productService.addProduct(product);
        long productId = product.productID;

        productService.deleteProduct(product.productID);

        assertEquals(0, productList.size());
    }

    @After
    public void dbReset(){
        ConnectionSingleton.resetTestDatabase();
    }



    /*public void productServiceAddProduct() {
        // set up - enter junk data to test
        Product p = new Product();
        String testProductName = "product 1";
        String testSeller = "ABC";
        double testProductPrice = 12.34;
        // act
        try {
            productService.addProduct(p);

        } catch (ProductException e) {
            //if exception is thrown, fail the test
            e.printStackTrace();
            Assert.fail("Product exception incorrectly thrown");
        }
        // assert
        List<Product> productList = productService.getProductList();
        // expecting a single lego at index 0
        Product actual = productList.get(0);
        Assert.assertEquals(testProductName, actual.getProductName());
        Assert.assertEquals(testSeller, actual.getSellerName());
        Assert.assertEquals(testProductPrice, actual.getProductPrice());
    }*/


}

/*
long id = (long) (Math.random() * Long.MAX_VALUE);
            p.setProductID(id);*/
