package org.example.Exception;

public class ProductException extends Exception {
    public ProductException(String msg){
            super(msg);
        }
    }
//This creates a custom exception class called ProductException that extends the built-in Exception class.
//It defines a constructor that accepts a String parameter called msg.  Inside the constructor, it calls
// super(msg) which calls the parent Exception class constructor, passing the msg.
//This sets the exception message to the msg parameter.