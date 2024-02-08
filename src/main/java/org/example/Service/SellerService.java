package org.example.Service;

import org.example.Model.Seller;

import java.util.ArrayList;
import java.util.List;

public class SellerService {
    List<Seller> sellerList;
    // this instance variable represents a list of "Seller" objects.
    // it is declared but not initialized in the class

    public SellerService() {
        this.sellerList = new ArrayList<>();
        // this is the constructor for the SellerService class
        // it initializes the sellersList instance variable by creating a new ArrayList
        // object.  This ensures that the sellersList is not null when an instance of
        // SellerService is created.
    }

    public List<Seller> getSellerList() {
        return sellerList;
        //getter method for accessing the sellersList variable, returns the list of sellers
    }

    public void addSeller(Seller s) {
        sellerList.add(s);
        //this method adds a new Seller object to the sellersList.  It takes a Seller
        //object as a parameter and appends it to the end of the list.
    }
}

//The SellerService class provides functionality to manage a list of sellers.
//It allows adding sellers to the list, retrieving the list of sellers and updating
//the list of sellers via setter methods.  This class follows the basic principles
//of ENCAPSULATION by providing methods to manipulate it's internal state (sellersList)
//while hiding the implementation details.

/*
Need to add a check in this SellerService that checks for duplicates
and returns an error if a duplicate seller is found.
 */