package org.example.DOA;

import org.example.Model.Seller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SellerDAO {
    Connection conn;
    public SellerDAO(Connection conn) {
        this.conn = conn;
    }
    // The following is "boilerplate code" to get results from any query that returns multiple values
    public List<Seller> getSellerList() {
        //Need to convert the Seller DB records into a list, so creating an empty array list for the results
        List<Seller> sellerResults = new ArrayList<>();
        try {
            // SQL logic - selecting everything from Seller
            PreparedStatement ps = conn.prepareStatement("select * from SELLER");
            //results from above statement are returned in a resultSet
            ResultSet resultSet = ps.executeQuery();
            //looping thru resultSet to get all the records
            while(resultSet.next()){
                //extracting the values in "name" from all items in resultSet
                String name = resultSet.getString("name");
                // converting above to a Java object
                Seller s = new Seller(name);
                // adding to sellerResults list - which is returned at the end
                sellerResults.add(s);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return sellerResults;
    }

    //(Seller s) below is the Seller object being passed as a parameter
    /* "Seller s" will be something extracted from the request body in the ProductController
    It's passed down to SellerService, which then passes it down to SellerDAO to use in the
    insert statement below
     */
    public void addSeller(Seller s) {
        try {
            //This is the sql to add/POST a seller to the seller table with a value to be entered
            PreparedStatement ps = conn.prepareStatement("insert into " +
                    "SELLER (name) values (?)");
            ps.setString(1, s.getName());
            ps.executeUpdate();

        }catch (SQLException e) {
            e.printStackTrace();
        }

    }
//    public String getSellerByName(String name) {
//        try {
//            PreparedStatement ps = conn.prepareStatement(
//                    "select * from SELLER where name = ?");
//            ps.setString(1, name);
//            ResultSet rs = ps.executeQuery();
//            if(rs.next()){
//                name = rs.getString("name");
//                //Seller s = new Seller(name);
//                return name;
//            }else{
//                return null;
//            }
//        }catch (SQLException e){
//            e.printStackTrace();
//        }
//        return null;
//    }
}
