package org.example.DOA;

import org.example.Model.Product;

import java.sql.Connection;
import java.util.List;

public class ProductDAO {
    Connection conn;
    public ProductDAO(Connection conn) {
        this.conn = conn;
    }
    public List<Product> getProductList() {
        return null;
    }

    public void addProduct() {

    }
}
