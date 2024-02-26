import Utility.ConnectionSingleton;
import org.example.DOA.ProductDAO;
import org.example.DOA.SellerDAO;
import org.example.Exception.ProductException;
import org.example.Exception.SellerException;
import org.example.Model.Product;
import org.example.Model.Seller;
import org.example.Service.ProductService;
import org.example.Service.SellerService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ProductServiceTest {
    //before each test, reset the ProductService and SellerService to a newly instantiated objects
    ProductService productService;
    SellerService sellerService;

    ProductDAO productDAO;
    SellerDAO sellerDAO;
    Connection conn = ConnectionSingleton.getConnection();


   // Seller testSeller;


    @Before
    public void setUp() throws SellerException, ProductException {
        sellerService = new SellerService(sellerDAO);
        productService = new ProductService(productDAO); //needed sellerService because of DI in ProductService?
        productDAO = new ProductDAO(conn);
        sellerDAO = new SellerDAO(conn);

    }

    @Before
    public void dbReset(){
        ConnectionSingleton.resetTestDatabase();
    }



    @Test
    public void productServiceEmptyAtStart() {
        //getting a List of Product objects from a ProductService
        List<Product> productList = productDAO.getProductList();
        System.out.println(productList);
        assertEquals(0, productList.size());
        //assertEquals(0, productList.size()); //this is the same thing as:  Assert.assertTrue(productList.size() == 0);
    }
// Do I really need sellerService to be empty before each test?
     @Test
     public void sellerServiceEmptyAtStart() {
         List<Seller> sellerList = sellerDAO.getSellerList();
         System.out.println(sellerList);
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

        sellerDAO.addSeller(seller);
        productDAO.addProduct(product);

        assertTrue(productDAO.getProductList().contains(product));
    }

// Below test fails because it can't find the seller "abc", but that's the entire reason for the test?
// "CONSTRAINT_18: PUBLIC.PRODUCT FOREIGN KEY(SELLERNAME) REFERENCES PUBLIC.SELLER(NAME) ('abc')"; SQL statement:
//insert into PRODUCT (productID, productName, sellerName, productPrice) values (?, ?, ?, ?) [23506-214]

    @Test
    public void testSellerExistsException() throws ProductException {
        String testProductName = "ball";
        String testSellerName = "abc";
        double  testProductPrice = 5.99;
        String testSellerName2 = "walmart";

        Product product = new Product();
        product.setProductName(testProductName);
        product.setSellerName(testSellerName);
        product.setProductPrice(testProductPrice);

        Seller seller = new Seller();
        seller.setName(testSellerName2);

        sellerDAO.addSeller(seller);
        productDAO.addProduct(product);?.

        try{
            productService.addProduct(product);
            Assert.fail("Seller name does not exist in Seller database");
        }catch (Exception e) {

        }
    }
    @Test
    public void testDeleteProduct() throws Exception {
        List<Product> productList = productDAO.getProductList();
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

        sellerDAO.addSeller(seller);

        productDAO.addProduct(product);
        long productId = product.productID;

        productDAO.deleteProduct(product);
     //   productDAO.getProductList();

       // assertEquals(0, productList.size());
        assertEquals(0, productList.size());
    }
//
//    @After
//    public void tearDown() {
//        // drop all objects and shutdown
//    }



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
