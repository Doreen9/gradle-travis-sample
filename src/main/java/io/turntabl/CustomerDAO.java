package io.turntabl;

import java.sql.*;
import java.util.Scanner;

public class CustomerDAO{

    private static Scanner scanner = new Scanner(System.in);

    public void getProductDetails() {
        String url = "jdbc:postgresql:northwind";
        try(Connection database = DriverManager.getConnection(url, "doreen-dodoo",  "turntabl")){
            // PreparedStatement query2 = database.prepareStatement("select products.product_name, products.unit_price from products inner join order_details on products.product_id = order_details.product_id inner join orders on order_details.order_id = orders.order_id inner join customers on orders.customer_id = customers.customer_id where customers.contact_name like ? ");
            System.out.println("Please enter customer name: ");
            String name = scanner.nextLine();
            PreparedStatement query = database.prepareStatement("select products.product_name, products.unit_price, order_details.quantity, order_details.discount, ((order_details.quantity * products.unit_price) - (order_details.quantity * order_details.discount * products.unit_price)) as Price_paid from products inner join order_details on products.product_id = order_details.product_id inner join orders on order_details.order_id = orders.order_id inner join customers on orders.customer_id = customers.customer_id where customers.contact_name = ?");
            query.clearParameters();
            query.setString(1, name);
            ResultSet resultsFromSearch = query.executeQuery();
            if (!resultsFromSearch.isBeforeFirst()) {
                System.out.println("No customer with such name exists!");
            } else {
                System.out.println("--------------------------------------------------------------------------------------------------------------------");
                System.out.println();
                System.out.printf("%-45s %-15s %-15s %-20s %-25s", "Product_Name  ", "Unit_Price", "Quantity", "Discount", "Price_Paid" + '\n');
                System.out.println();
                System.out.println("--------------------------------------------------------------------------------------------------------------------");
                while (resultsFromSearch.next()) {
                    System.out.format("%-45s %-15f %-15s %-20f %-25f", resultsFromSearch.getString("product_name"), resultsFromSearch.getFloat("unit_price"),
                            resultsFromSearch.getString("quantity"), resultsFromSearch.getFloat("discount"), resultsFromSearch.getFloat("Price_Paid"));
                    System.out.println();
                }
            }
        }
        catch(SQLException sqlE){
            System.err.println("Connection error: " + sqlE);
        }

    }


}

