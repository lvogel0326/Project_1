package org.example.Model;

import java.util.Objects;

public class Product {
    public long productID;
    public String productName;
    public String sellerName;
    public double productPrice;

    // This is a no args constructor - because there are some tools we'll use later
    // that could have issues with classes that don't have a no args constructor
    public Product() {

    }

    public Product(long productID, String productName, String sellerName, double productPrice) {
        this.productID = productID;
        this.productName = productName;
        this.sellerName = sellerName;
        this.productPrice = productPrice;
    }

    public long getProductID() {
        return productID;
    }

    public void setProductID(long productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return getProductID() == product.getProductID() && Double.compare(getProductPrice(), product.getProductPrice()) == 0 && Objects.equals(getProductName(), product.getProductName()) && Objects.equals(getSellerName(), product.getSellerName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProductID(), getProductName(), getSellerName(), getProductPrice());
    }

    @Override
    public String toString() {
        return "Product{" +
                "productID=" + productID +
                ", productName='" + productName + '\'' +
                ", sellerName='" + sellerName + '\'' +
                ", productPrice=" + productPrice +
                '}';
    }
}
