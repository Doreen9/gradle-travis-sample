package io.turntabl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO{

    public void getProductNames() {

        String url = "jdbc:postgresql:northwind";
         try(Connection database = DriverManager.getConnection(url, "doreen-dodoo",  "turntabl")){
            PreparedStatement query2 = database.prepareStatement("select count(order_details.product_id) as Order_count, products.product_name from products inner join order_details on products.product_id = order_details.product_id group by products.product_name order by Order_count desc limit 5");
            query2.clearParameters();
            ResultSet resultsFromSearch = query2.executeQuery();
            System.out.println("Popular products in Descending order");
            System.out.println("--------------------------------------------------------------------------------------------------------------------");
            System.out.println();
            System.out.printf("%-55s  %-25s", "Product_Name  ", "Order_count" + '\n');
            System.out.println();
            System.out.println("--------------------------------------------------------------------------------------------------------------------");
            while (resultsFromSearch.next()) {
                System.out.format("%-55s  %-25s", resultsFromSearch.getString("product_name"), resultsFromSearch.getString("Order_count"));
                System.out.println();

            }



        }catch(SQLException sqlE){
            System.err.println("Connection error: " + sqlE);
        }

    }

}
