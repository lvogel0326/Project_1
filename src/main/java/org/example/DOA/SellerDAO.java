package org.example.DOA;

import org.example.Model.Seller;

import java.sql.Connection;
import java.util.List;

public class SellerDAO {
    Connection conn;
    public SellerDAO(Connection conn) {
        this.conn = conn;
    }
    public List<Seller> getSellerList() {
        return null;
    }

    public void addSeller() {

    }
}
