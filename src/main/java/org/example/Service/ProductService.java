package org.example.Service;

import org.example.Exception.ProductException;
import org.example.Model.Product;
import org.example.Model.Seller;

import java.util.ArrayList;
import java.util.List;

//below we are defining the ProductService class.
// Two fields are declared within the class:  SellerService and List<Product>
public class ProductService {
    //NOTE:  LK has these below the DI, do i need to move them??
    SellerService sellerService;  //an instance of the SellerService class used to perform CRUD operations on sellers
    List<Product> productList;  // a list of Product objects, used to store/manage products in ProductService
    //The type List<Product> indicates that this field holds a collection of Product objects. The list allows
    // for the storage and manipulation of product data, such as adding, removing, or accessing products.

    //Dependency injection - which injects the SellerService into the ProductService
    public ProductService(SellerService sellerService){
        this.sellerService = sellerService;
        productList = new ArrayList<>();
    }

    //This is the "getProductList() method that provides access to the list of products stored in the
    //productList variable.  It ENCAPSULATES the retrieval logic, allowing other parts of the code
    // to access the product list without directly accessing the underlying variable
    public List<Product> getProductList() {
        return productList;
    }

/*    //This method (sellerNameExists) takes a String parameter (sellerName) and returns a boolean value
    // indicating whether a seller with the specified name exists.
    public boolean sellerNameExists(Product p){
        List<Seller> sellerList = sellerService.getSellerList();
        for(int i = 0; i < sellerList.size(); i++){
            Seller currentSeller = sellerList.get(i);
            return currentSeller.getSellerList().equals(sellerName);
        }
        return false;
    }*/

    public boolean sellerNameExists(Product p)
            throws ProductException {
        if (p.getProductName() == null || p.getSellerName() == null || p.getProductPrice() == 0) {
            throw new ProductException("Product Name and Seller Name cannot be blank an Product Price must be > 0");
        }
        // sellerService = new SellerService();
        List<Seller> sellerList = sellerService.getSellerList();
        System.out.println("seller list" + sellerList.size());
        for (int i = 0; i < sellerList.size(); i++) {
            if (p.sellerName.equals(sellerList.get(i).getName())) {
                /*long id = (long) (Math.random() * Long.MAX_VALUE);
                p.setProductId(id);
                productList.add(p);
             */
                return true;
            }
        }
        return false;
    }


    //This method (addProduct) takes a Product object p as a parameter and returns a Product object.
    // It also declares that it may throw a ProductException.
    // This method performs validation checks on the product p before adding it to the system.
    // If any of the validation checks fail, it throws a ProductException with an error message.
    // Otherwise, it proceeds with adding the product.
    public Product addProduct(Product p) throws ProductException {
        boolean sellerExists = sellerNameExists(p);
        if (sellerExists){
            // here we wanted to use a long random number for the product ID, but Math.random returns a
            // double so we had to "cast" this to a "long".
            long id = (long) (Math.random() * Long.MAX_VALUE);
            p.setProductID(id);
            // adding the product to the productList.
            productList.add(p);
        }else{
            throw new ProductException("Seller name does not exist in list");
        }
        if(p.getProductName() == null || p.getProductName().isEmpty()
                || p.getSellerName() == null || p.getSellerName().isEmpty()
                || p.getProductPrice() == 0.0 ){
            throw new ProductException("Product name and Seller name cannot be empty. Price may not be zero");
        }
        return p;

    }
    /*
    This defines a method called getProductById that accepts a Long id parameter and returns a Product object.
    Inside the method, there is a List of Product objects called productList.  The for loop iterates thru the list,
    starting at 0 to the size of productList. On each iteration, it retrieves the Product object at index i from
    productList and then stores it in currentProduct. Then it gets the productID of currentProduct using the
    getProductID() method.
    This productID is compared to the id parameter passed to the method.  If the ids match, the currentProduct
    is immediately returned.  If the loop finishes without finding a match, null is returned.
     */

    public Product getProductById(Long id) {
        for(int i = 0; i < productList.size(); i++) {
            Product currentProduct = productList.get(i);
            if(currentProduct.getProductID() == id) {
                return currentProduct;
            }
        }
        return null;
    }

/*
LK's code for deleteProduct
 */
    public Product deleteProduct(long productId) {
        Product productToDelete = getProductById(productId);

        if (productToDelete != null) {
            productList.remove(productToDelete);

        }return productToDelete;
    }
/*
Tweaked LK's code for updateProduct
It calls the method (updateProduct) that takes a long id and a Product object as parameters
then it calls another method (getProductByID) passing it the id parameter just taken in.  The product returned
from getProductByID is stored in productToUpdate.  Then we check that product name and seller name are not null/empty
 and that price is > 0.  Then, if productToUpdate is not null we update the product name and/or product price passed in.
 */
    public Product updateProduct(long id, Product updatedProduct) throws ProductException {
        Product productToUpdate = getProductById(id);

        if(updatedProduct.getProductName().isEmpty() || updatedProduct.getProductName() == null
            || updatedProduct.getSellerName() == null || updatedProduct.getSellerName().isEmpty()
            || updatedProduct.getProductPrice() == 0.0 ){
            throw new ProductException("Product name and Seller name cannot be empty. Price may not be zero");
        }
        if (productToUpdate != null) {

            productToUpdate.setProductName(updatedProduct.getProductName());
            productToUpdate.setProductPrice(updatedProduct.getProductPrice());
        }
        return productToUpdate;

    }



}
