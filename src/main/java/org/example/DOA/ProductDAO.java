package org.example.DOA;

import org.example.Model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    Connection conn;
    public ProductDAO(Connection conn) {
        this.conn = conn;
    }
    public List<Product> getProductList() {
        //establishing list of cars at the start of the method
        List<Product> productResult = new ArrayList<>();
        try{
            PreparedStatement ps = conn.prepareStatement("select * from seller");
            ResultSet rs = ps.executeQuery();
            //while there are still values in resultSet, load them in the set of values
            while(rs.next()){
                long productID = rs.getLong("productID");
                String productName = rs.getString("productName");
                String sellerName = rs.getString("sellerName");
                double productPrice = rs.getInt("productPrice");
                Product p = new Product(productID, productName, sellerName, productPrice);
                productResult.add(p);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return productResult;
    }

    public void addProduct(Product p) {
        try{
            PreparedStatement ps = conn.prepareStatement("insert into PRODUCT" +
                    " (productName, sellerName, productPrice) " +
                    "values (?, ?, ?)");
            /*long productId = p.getProductID();
            int productIdInt = (int) productId;*/
            ps.setLong(1, p.getProductID());
            ps.setString(2, p.getProductName());
            ps.setString(3, p.getSellerName());
            ps.setDouble(4, p.getProductPrice());
            ps.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public Product getProductByID(long productID){
        try{
            PreparedStatement ps = conn.prepareStatement("select * from Product where productID = ?");
            ps.setLong(1, productID);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                productID = rs.getLong("productID"); // didn't have to declare this as a "long" because declared in the method?
                String productName = rs.getString("productName");
                String sellerName = rs.getString("sellerName");
                double productPrice = rs.getDouble("productPrice");
                Product p = new Product(productID, productName, sellerName, productPrice);
                return p;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

}

/*
DOA methods will be a bit long - but they pretty much follow a similar format
- we can select multiple results by creating an arraylist and doing a query - like getProductList() method
which will send the result of the query back to an array list.
 - a method that performs an action and doesnt return anything, like addProduct(Product p), update or delete
 this will use the ps.executeUpdate() method.
 - or one that just gets a single record, like getProductByID(long productID), which also doesn't return a
 list - it just returns the single value we get back
 The only difference between these DAO methods is the query we write and the model (seller vs product)
 specific parameters.
 */